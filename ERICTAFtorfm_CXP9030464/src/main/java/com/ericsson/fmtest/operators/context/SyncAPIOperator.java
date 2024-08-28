/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.fmtest.operators.context;

import java.sql.*;

import org.apache.log4j.Logger;

import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.handlers.FMDatabaseHandler;
import com.ericsson.fmtest.getters.SynchAPIOperatorAPIGetter;
import com.ericsson.fmtest.operators.Utils;

public class SyncAPIOperator {

	ActionSender action = new ActionSender();
	NetsimOperations operations = new NetsimOperations();
	private static final Logger LOGGER = Logger
			.getLogger(SyncAPIOperator.class);

	/**
	 * sendSyncRequest
	 * @description Method to initiate a sync Request on a node
	 * @param fdn
	 *            - FDN of the node
	 * @return String - Result from the Database with values true/false
	 */
	public String sendSyncRequest(String fdn, Host host) throws Exception {
		action.setAlarmSupervision(fdn, 1, host);
		action.generateSyncRequest(fdn, host);
		LOGGER.info("Synch operation in progress...");
		Utils.sleep(30);
		return getDbAlarmResult(fdn).toString();
	}

	/**
	 * @description Method to Cease all Alarms on the Node and set
	 *              AlarmSupervision ON.
	 * 
	 */
	public String ceaseAllandSync(String testData, Host host) throws Exception {
		String[] parameters = testData.split("#");
		operations.ceaseAllAlarms(parameters[0], parameters[1]);
		action.setAlarmSupervision(parameters[2], 1, host);
		action.generateSyncRequest(parameters[2], host);
		Utils.sleep(5); // Waiting for the automatic Sync to be completed.
		action.alarmAck(parameters[2], host);
		action.setAlarmSupervision(parameters[2], 0, host);
		return getDbAlarms(parameters[2]).toString();

	}

	/**
	 * @description Method to Cease all Alarms on the Node,raise Alarms and set
	 *              AlarmSupervison ON.
	 * 
	 */
	public String ceaseAllandRaise(String testData, Host host) throws Exception {
		String[] parameters = testData.split("#");
		operations.ceaseAllAlarms(parameters[0], parameters[1]);

		for (int i = 0; i < 3; ++i)
			operations.raiseTestAlarm(parameters[0], parameters[1], parameters[3]);
		action.setAlarmSupervision(parameters[2], 1, host);
		action.generateSyncRequest(parameters[2], host);
		Utils.sleep(10);
		action.setAlarmSupervision(parameters[2], 0, host);
		return getDbAlarms(parameters[2]).toString();
	}

	/**
	 * Method to return the alarms present on the Node
	 */
	public String getDbAlarms(String fdn) throws Exception {
		String alarms = "true";
		try {
			Connection con = FMDatabaseHandler.getFMADBConnection();
			Statement stmt;
			ResultSet rs;
			String statusCheckSQLQuery = "";
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			statusCheckSQLQuery = SynchAPIOperatorAPIGetter.OORQUERY + fdn
					+ "%\'";
			rs = stmt.executeQuery(statusCheckSQLQuery);
			while (rs.next()) {
				alarms = "false";
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage().toString());
			throw e;
		}
		LOGGER.debug("Output from the Database is:" + alarms);
		return alarms;
	}

	/**
	 * @method getDbAlarmResult
	 * @description Method to retrieve the result from the Database in OSS.
	 * @param fdn
	 *            - FDN of the node
	 * @return String - Result from the Database with values true/false
	 */

	public String getDbAlarmResult(String fdn) throws Exception {

		String status = "true";
		try {
			Connection con = FMDatabaseHandler.getFMADBConnection();
			Statement stmt;
			ResultSet rs;

			String recordType = "";

			// Create a statement object, the container for the SQL statement.
			String statusCheckSQLQuery = "";
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			statusCheckSQLQuery = SynchAPIOperatorAPIGetter.DBQUERY;// "select TOP 1 Object_of_reference,Record_type from FMA_alarm_log where Object_of_reference like \'";
			statusCheckSQLQuery = statusCheckSQLQuery.concat(fdn);
			statusCheckSQLQuery = statusCheckSQLQuery
					.concat(SynchAPIOperatorAPIGetter.RECORD_TYPE_7);
			rs = stmt.executeQuery(statusCheckSQLQuery);
			while (rs.next()) {
				recordType = rs.getString("Record_type");
			}
			if (recordType.equals("7")) {
				statusCheckSQLQuery = SynchAPIOperatorAPIGetter.DBQUERY;
				statusCheckSQLQuery = statusCheckSQLQuery.concat(fdn);
				statusCheckSQLQuery = statusCheckSQLQuery
						.concat(SynchAPIOperatorAPIGetter.RECORD_TYPE_8);
				rs = stmt.executeQuery(statusCheckSQLQuery);
				while (rs.next()) {
					recordType = rs.getString("Record_type");
				}

				if (!recordType.equals("8"))
					status = "false";
			}
		} catch (Exception e) {
			LOGGER.error(e.getStackTrace().toString());
			status = "false";
			throw e;
		}
		return status;
	}

}
