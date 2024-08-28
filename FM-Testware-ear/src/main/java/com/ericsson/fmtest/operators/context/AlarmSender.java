/**
 * 
 */
package com.ericsson.fmtest.operators.context;

/**
 * @author evinjay
 *
 */

import java.sql.*;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;
import org.jboss.ejb.client.*;
import org.jboss.ejb.client.remoting.ConfigBasedEJBClientContextSelector;

import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.handlers.FMDatabaseHandler;
import com.ericsson.fmtest.getters.AlarmAPIOperatorAPIGetter;
import com.ericsson.fmtest.operators.Utils;
import com.ericsson.nms.fm.fm_communicator.FMServiceRemote;
import com.ericsson.oss.fmservice.api.beans.Alarm;
import com.ericsson.oss.fmservice.api.beans.FMTestBeanRemote;

public class AlarmSender {

	private static final Logger LOGGER = Logger
			.getLogger(AlarmSender.class);
	javax.naming.Context context = null;

	FMTestBeanRemote bean = null;
	FMServiceRemote fmser_bean = null;

	/**
	 * @method sendModelEvent
	 * 
	 * @description Method to Send Modeled Alarm Object to OSS through
	 *              FMTestBean,FMService,FMCommunicator.
	 * 
	 * @param -toSend an Alarm to be sent to OSS.
	 * 
	 * @return - Alarm Object
	 */
	public Alarm sendModelEvent(Alarm toSend) throws Exception {
		sendUsingLookup(toSend);
		LOGGER.info("Sleeping for 5 seconds before getting output");
		Utils.sleep(5);
		return (getDbAlarm(toSend));
	}

	/**
	 * @method sendUsingLookup
	 * 
	 * @description Method to send alarm to FMTestBean
	 * 
	 * @param al
	 *            of type Alarm to be sent
	 */
	public void sendUsingLookup(Alarm al) throws Exception {
		FMTestBeanRemote bean = lookup();
		bean.sendAlarm(al);
	}

	/**
	 * @method getRemoteOutput
	 * 
	 * @description Method to retrieve the result of the Alarm sent to OSS from
	 *              the Database.
	 * 
	 * @param -toSend an Alarm sent to OSS.
	 * 
	 * @return - Alarm Object
	 */
	public Alarm getDbAlarm(Alarm toSend) throws Exception {
		Alarm dbAlarm = new Alarm();
		try {
			Connection con = FMDatabaseHandler.getFMADBConnection();
			Statement stmt;
			ResultSet rs;
			String statusCheckSQLQuery = "";
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			if (toSend.getSpecificProblem() != null) {
				statusCheckSQLQuery = AlarmAPIOperatorAPIGetter.DBQUERY_SP
						+ toSend.getSpecificProblem()
						+ AlarmAPIOperatorAPIGetter.ORDER_BY_CLAUSE;
				if (Integer.parseInt(toSend.getSeverity()) == 5) {
					statusCheckSQLQuery += AlarmAPIOperatorAPIGetter.PERCEIVED_SEVERITY_CLEAR;
				}
			} else {
				if (toSend.getNotification().equalsIgnoreCase(AlarmAPIOperatorAPIGetter.SYNC_START))
					statusCheckSQLQuery = AlarmAPIOperatorAPIGetter.DBQUERY_OOR
							+ toSend.getFdn() + "\'" + "and "
							+ AlarmAPIOperatorAPIGetter.RECORD_TYPE_7;
				else if (toSend.getNotification().equalsIgnoreCase(AlarmAPIOperatorAPIGetter.SYNC_END))
					statusCheckSQLQuery = AlarmAPIOperatorAPIGetter.DBQUERY_OOR
							+ toSend.getFdn() + "\'" + "and "
							+ AlarmAPIOperatorAPIGetter.RECORD_TYPE_8;
				else if (toSend.getNotification()
						.equalsIgnoreCase(AlarmAPIOperatorAPIGetter.SYNC_ABORT))
					statusCheckSQLQuery = AlarmAPIOperatorAPIGetter.DBQUERY_OOR
							+ toSend.getFdn() + "\'" + "and "
							+ AlarmAPIOperatorAPIGetter.RECORD_TYPE_2;
			}
			LOGGER.debug("Query is : " + statusCheckSQLQuery);
			rs = stmt.executeQuery(statusCheckSQLQuery);
			while (rs.next()) {
				dbAlarm.setFdn(rs.getString(AlarmAPIOperatorAPIGetter.OBJECT_OF_REFERENCE));
				dbAlarm.setSpecificProblem(rs.getString(AlarmAPIOperatorAPIGetter.SP_TEXT));
				dbAlarm.setProbableCause(rs.getString(AlarmAPIOperatorAPIGetter.PC_TEXT));
				dbAlarm.setSeverity(rs.getString(AlarmAPIOperatorAPIGetter.PERCEIVED_SEVERITY));
				dbAlarm.setEventType(rs.getString(AlarmAPIOperatorAPIGetter.ET_TEXT));

			}
		} catch (Exception e) {
			LOGGER.error("Error in executing the query",e);
			throw e;
		}

		dbAlarm.setEventTime(toSend.getEventTime());
		dbAlarm.setSourceType(toSend.getSourceType());
		return dbAlarm;
	}

	/**
	 * @method lookup
	 * 
	 * @description Method to lookup for the FMTest Bean
	 * 
	 * @return Object of type FMTestBeanRemote
	 */
	public FMTestBeanRemote lookup() throws Exception {
		String TOR_IP = null;
		String JNDI_PORT = null;

		TOR_IP = (String) DataHandler.getAttribute("DPS_TOR_IP");
		JNDI_PORT = (String) DataHandler.getAttribute("JNDI_PORT");

		final Properties properties = new Properties();
		properties.put(Context.URL_PKG_PREFIXES,
				AlarmAPIOperatorAPIGetter.NAMING);
		properties.put(AlarmAPIOperatorAPIGetter.SSL_ENABLED, "false");
		properties.put(AlarmAPIOperatorAPIGetter.CONNECTIONS, "default");

		properties.put(AlarmAPIOperatorAPIGetter.HOST, TOR_IP);
		properties.put(AlarmAPIOperatorAPIGetter.PORT, JNDI_PORT);
		properties.put(AlarmAPIOperatorAPIGetter.SASL_POLICY, "false");
		properties.put(AlarmAPIOperatorAPIGetter.USERNAME, "guest");
		properties.put(AlarmAPIOperatorAPIGetter.PASSWORD, "guestp");
		properties.put(Context.SECURITY_PRINCIPAL, "guest");
		properties.put(Context.SECURITY_CREDENTIALS, "guestp");

		try {
			final EJBClientConfiguration clientConfiguration = new PropertiesBasedEJBClientConfiguration(
					properties);
			final ContextSelector<EJBClientContext> contextSelector = new ConfigBasedEJBClientContextSelector(
					clientConfiguration);

			// set the selector for use
			EJBClientContext.setSelector(contextSelector);

			// 1. Obtaining Context
			context = new InitialContext(properties);
			String lookupName = AlarmAPIOperatorAPIGetter.FMTestLookUpName;// "ejb:FMTestBean-ear-0.0.1-SNAPSHOT/FMTestBean-ejb-0.0.1-SNAPSHOT/FMTestBean!com.ericsson.oss.fmservice.api.beans.FMTestBeanRemote";
			bean = (FMTestBeanRemote) context.lookup(lookupName);

		} catch (Exception e) {
			LOGGER.error("Error in Lookup of the ear",e);
			throw e;
		}
		return bean;
	}

}
