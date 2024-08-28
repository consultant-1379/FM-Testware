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
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ericsson.cifwk.taf.ApiOperator;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.handlers.FMDatabaseHandler;
import com.ericsson.cifwk.taf.handlers.netsim.implementation.NetsimNE;
import com.ericsson.fmtest.getters.NetsimAPIOperatorAPIGetter;
import com.ericsson.fmtest.operators.Utils;
import com.ericsson.oss.fmservice.api.beans.Alarm;

public class NetsimAPIOperator implements ApiOperator {

	NetsimOperations netsimOperations = new NetsimOperations();
	NetsimNE node = new NetsimNE();

	private static final Logger LOGGER = Logger
			.getLogger(NetsimAPIOperator.class);
	
	public void startSupervision(String fdn,Host host){
		ActionSender actionSender = new ActionSender();
		try {
			actionSender.setAlarmSupervision(fdn,1, host);
		} catch (Exception e) {
			LOGGER.error("Exception in StartSupervision "+e.getMessage());
		}
		
		
	}

	/**
	 * raise Alarm
	 * 
	 * @description Method to send an Alarm on a node.
	 * @param fdn
	 *            -the fdn of the node as a String
	 * @param neType
	 *            - Type of the Network Element Eg.LRAN,URAN
	 * @param simulationName
	 *            - Name of the Simulation in which the node is present in
	 *            Netsim Server
	 * @param neName
	 *            - Name of the Network Element
	 * @param specificProblem
	 *            - SpecificProblem attribute of an alarm as an integer
	 * @param spText
	 *            SpecificProblemText as a String
	 * @param severity
	 *            - Severity of alarm
	 * 
	 * @return Alarm Object
	 */
	public String raiseAlarm(String fdn, String neType, String simulationName,
			String neName, String spText, String severity) throws Exception {
		return netsimOperations.sendAlarm(fdn, neType, simulationName, neName, spText,
				severity);
	}

	public String raiseAlarm(String fdn, String neType, String simulationName,
			String neName, String spText, String pcText, String severity)
			throws Exception {
		return netsimOperations.sendFullAlarm(fdn, neType, simulationName, neName,
				spText, pcText, severity);
	}

	/**
	 * raiseAlarm_PC
	 * 
	 * @description Method to send an Alarm with certain Probable Cause on a
	 *              node.
	 * @param fdn
	 *            -the fdn of the node as a String
	 * @param neType
	 *            - Type of the Network Element Eg.LRAN,URAN
	 * @param simulationName
	 *            - Name of the Simulation in which the node is present in
	 *            Netsim Server
	 * @param neName
	 *            - Name of the Network Element
	 * @param specificProblem
	 *            - SpecificProblem attribute of an alarm as an integer
	 * @param pc
	 *            Probable Cause as a String
	 * @param severity
	 *            - Severity of alarm
	 * 
	 * @return Alarm Object
	 */
	public String raiseAlarm_PC(String fdn, String neType,
			String simulationName, String neName, String pc, String severity)
			throws Exception {
		return netsimOperations.sendAlarm_PC(fdn, neType, simulationName, neName, pc,
				severity);
	}

	/**
	 * raise Default Alarm
	 * 
	 * @description Method to send a default Alarm on a node.
	 * @param simulationName
	 *            - Name of the Simulation in which the node is present in
	 *            Netsim Server
	 * @param neName
	 *            - Name of the Network Element
	 */

	public String raiseDefaultAlarm(String simulationName, String neName, String neType)
			throws Exception {
		netsimOperations.raiseTestAlarm(simulationName, neName, neType);
		Thread.sleep(2);
		return getDefaultAlarmResult(neName, 0).toString();

	}

	/**
	 * Method raise Default Clear Alarm
	 * 
	 * @description Method to send a default Clear alarm on a node
	 * @param simulationName
	 *            - Name of the Simulation in which the node is present in
	 *            Netsim Server
	 * @param neName
	 *            - Name of the Network Element
	 * @param neType
	 *            - Type of the Network Element
	 */

	public String raiseDefaultClearAlarm(String simulationName, String neName,
			String neType) throws Exception {
		netsimOperations.raiseTestClearAlarm(simulationName, neName, neType);
		Thread.sleep(2);
		return getDefaultAlarmResult(neName, 1).toString();
	}

	/**
	 * Method raiseHBAlarm
	 * 
	 * @description Method to raise an HeartBeat Alarm on a node
	 * @param node
	 *            as a NetsimNE
	 * @return Alarm Object as a String
	 */
	public String raiseHBAlarm(NetsimNE node) throws Exception {
		netsimOperations.stopNode(node.getSimulation(), node);
		LOGGER.info("Sleeping for 200 Seconds for a HB alarm to get raised on the Node");
		Utils.sleep(NetsimAPIOperatorAPIGetter.HEARTBEATSLEEPTIME);
		return getHeartBeatDbAlarm(node.getName(), 0).toString();
	}

	/**
	 * Method raiseHBClearAlarm
	 * 
	 * @description Method to raise Clear alarm for an HeartBeat Alarm on a node
	 * @param node
	 *            as a NetsimNE
	 * @return Alarm Object as a String
	 */
	public String raiseHBClearAlarm(NetsimNE node) throws Exception {
		netsimOperations.startNode(node.getSimulation(), node);
		Utils.sleep(NetsimAPIOperatorAPIGetter.HEARTBEATSLEEPTIME);
		return getHeartBeatDbAlarm(node.getName(), 1).toString();
	}

	public String startAlarmBurst(String simulationName, String neName,
			String activityName) throws Exception {
		return netsimOperations.startAlarmBurst(simulationName, neName, activityName);
	}

	public String stopAlarmBurst(String simulationName, String neName,
			String activityName) throws Exception {
		return netsimOperations.stopAlarmBurst(simulationName, neName, activityName);
	}

	/**
	 * Method to retrieve the default Alarm sent from Netsim server on a Node
	 * 
	 * @param neName
	 *            Node name in the Netsim server
	 * @param clearFlag
	 *            0 to get the Normal Alarm 1 for the Clear Alarm
	 * @return the Alarm Object as a String
	 */
	public String getDefaultAlarmResult(String neName, int clearFlag)
			throws Exception {
		Alarm dbAlarm = new Alarm();
		try {
			Connection con = FMDatabaseHandler.getFMADBConnection();
			Statement stmt;
			ResultSet rs;
			String statusCheckSQLQuery = "";
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			if (clearFlag == 0)
				statusCheckSQLQuery = NetsimAPIOperatorAPIGetter.OOR_SPTEXT
						+ neName + NetsimAPIOperatorAPIGetter.SP_TEXT_ORDERBY;
			else
				statusCheckSQLQuery = NetsimAPIOperatorAPIGetter.OOR_SPTEXT
						+ neName + NetsimAPIOperatorAPIGetter.SP_TEXT_PS_CLEAR;

			LOGGER.debug("Query is: " + statusCheckSQLQuery);
			rs = stmt.executeQuery(statusCheckSQLQuery);
			if (rs.next()) {
				dbAlarm.setFdn(rs
						.getString(NetsimAPIOperatorAPIGetter.OBJECT_OF_REFERENCE));
				dbAlarm.setSpecificProblem(rs
						.getString(NetsimAPIOperatorAPIGetter.SP_TEXT));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw e;
		}
		LOGGER.debug("Record Contents:" + dbAlarm.getFdn());
		LOGGER.debug("Perceived_Severity is:" + dbAlarm.getSeverity());
		LOGGER.debug("After getting the output from the DB:"
				+ dbAlarm.toString());
		return dbAlarm.toString();
	}

	/**
	 * Method getHeartBeatDbAlarm
	 * 
	 * @description Method to retrieve the HeartBeat alarm from the Database.
	 * @param testNEName
	 *            -NetworkElement name as a String
	 * @param State
	 *            - integer representing the State for HB Alarm. 0 - HB alarm 1-
	 *            HBClear alarm
	 * @return Object of type Alarm with the values set from the Database in
	 *         OSS.
	 */
	public Alarm getHeartBeatDbAlarm(String testNEName, int state)
			throws Exception {

		Alarm dbAlarm = new Alarm();
		int perceived_severity = 5;
		int record_type = 6; // For HeartBeat Alarm
		if (state == 0)
			perceived_severity = 1;
		try {
			Connection con = FMDatabaseHandler.getFMADBConnection();
			Statement stmt;
			ResultSet rs;
			// Create a statement object, the container for the SQL statement.
			String statusCheckSQLQuery = "";
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			statusCheckSQLQuery = NetsimAPIOperatorAPIGetter.QUERY + testNEName
					+ NetsimAPIOperatorAPIGetter.AND_PERCEIVED_SEVERITY
					+ perceived_severity
					+ NetsimAPIOperatorAPIGetter.AND_RECORD_TYPE + record_type
					+ NetsimAPIOperatorAPIGetter.ORDERBY_INSERT_TIME;

			LOGGER.debug("Query is: " + statusCheckSQLQuery);
			rs = stmt.executeQuery(statusCheckSQLQuery);
			while (rs.next()) {
				dbAlarm.setFdn(rs
						.getString(NetsimAPIOperatorAPIGetter.OBJECT_OF_REFERENCE));
				dbAlarm.setSeverity(rs
						.getString(NetsimAPIOperatorAPIGetter.PERCEIVED_SEVERITY));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw e;
		}
		LOGGER.debug("Record Contents:" + dbAlarm.getFdn());
		LOGGER.debug("Perceived_Severity is:" + dbAlarm.getSeverity());
		LOGGER.debug("After getting the output from the DB:"
				+ dbAlarm.toString());
		return dbAlarm;
	}

	/**
	 * Method getDbAlarm
	 * 
	 * @description Method to retrieve the result from the Database for the
	 *              action performed.
	 * @param fdn
	 *            -FDN of a node as a String attribute - attribute that is read
	 *            from the attributes file
	 * @param attribute
	 *            the attribute of an alarm.May be SP,PC,ET..
	 * @return Object of type Alarm with the values set from the Database in
	 *         OSS.
	 */
	public static Alarm getDbAlarm(String fdn, String[] attribute)
			throws Exception {
		Alarm dbAlarm = new Alarm();
		try {
			Connection con = FMDatabaseHandler.getFMADBConnection();
			Statement stmt;
			ResultSet rs;

			// Create a statement object, the container for the SQL statement.
			String statusCheckSQLQuery = "";
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			if (attribute[0].startsWith("Specific")) {
				String[] temp = attribute[0].split("\\$");
				String specificProblemValue = temp[1];
				statusCheckSQLQuery = NetsimAPIOperatorAPIGetter.DBQUERY_SP
						+ specificProblemValue
						+ NetsimAPIOperatorAPIGetter.AND_OOR_LIKE + fdn + "\'"
						+ NetsimAPIOperatorAPIGetter.ORDERBY_INSERT_TIME;

				LOGGER.info("Query is: " + statusCheckSQLQuery);
				rs = stmt.executeQuery(statusCheckSQLQuery);
				while (rs.next()) {
					dbAlarm.setFdn(rs
							.getString(NetsimAPIOperatorAPIGetter.OBJECT_OF_REFERENCE));
					dbAlarm.setSpecificProblem(rs
							.getString(NetsimAPIOperatorAPIGetter.SPECIFIC_PROBLEM));
					dbAlarm.setSeverity(rs
							.getString(NetsimAPIOperatorAPIGetter.PERCEIVED_SEVERITY));
				}

			} else if (attribute[0].startsWith("Probable")) {
				statusCheckSQLQuery = NetsimAPIOperatorAPIGetter.DBQUERY_PC
						+ attribute[1] + "\'"
						+ NetsimAPIOperatorAPIGetter.AND_OOR_LIKE + fdn + "\'";
				LOGGER.debug("Query is:" + statusCheckSQLQuery);
				rs = stmt.executeQuery(statusCheckSQLQuery);
				while (rs.next()) {
					dbAlarm.setFdn(rs
							.getString(NetsimAPIOperatorAPIGetter.OBJECT_OF_REFERENCE));
					dbAlarm.setProbableCause(rs
							.getString(NetsimAPIOperatorAPIGetter.PC_TEXT));
					dbAlarm.setSeverity(rs
							.getString(NetsimAPIOperatorAPIGetter.PERCEIVED_SEVERITY));
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw e;
		}
		LOGGER.debug("Output from the Database : " + dbAlarm.toString());
		return dbAlarm;
	}

	/**
	 * Method getBulkAlarmFromDB
	 * 
	 * @description Method to retrieve the result from the Database for the
	 *              action performed.
	 * @param fdn
	 *            -FDN of a node as a String attribute - attribute that is read
	 *            from the attributes file
	 * @return List of Alarm Objects with the values set from the Database in
	 *         OSS as a String.
	 */
	public static List<String> getBulkAlarmFromDB(String fdn, String attribute)
			throws Exception {

		List<String> alarmList = new ArrayList<String>();
		Alarm dbAlarm = null;
		try {
			Connection con = FMDatabaseHandler.getFMADBConnection();
			Statement stmt;
			ResultSet rs;

			// Create a statement object, the container for the SQL statement.
			String statusCheckSQLQuery = "";
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			statusCheckSQLQuery = NetsimAPIOperatorAPIGetter.DBQUERY_BULK_SP
					+ attribute + NetsimAPIOperatorAPIGetter.AND_OOR_LIKE + fdn
					+ "\'" + NetsimAPIOperatorAPIGetter.ORDERBY_INSERT_TIME;

			LOGGER.debug("Query is: " + statusCheckSQLQuery);
			rs = stmt.executeQuery(statusCheckSQLQuery);
			while (rs.next()) {
				dbAlarm = new Alarm();
				dbAlarm.setFdn(rs
						.getString(NetsimAPIOperatorAPIGetter.OBJECT_OF_REFERENCE));
				dbAlarm.setSpecificProblem(rs
						.getString(NetsimAPIOperatorAPIGetter.SPECIFIC_PROBLEM));
				dbAlarm.setSeverity(rs
						.getString(NetsimAPIOperatorAPIGetter.PERCEIVED_SEVERITY));
				alarmList.add(dbAlarm.toString());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw e;
		}
		LOGGER.debug("Total alarms from the Database : " + alarmList.size());
		return alarmList;
	}

	public static List<String> getAlarmFromDB(String fdn, String attribute,
			String cause, String severity) throws Exception {

		List<String> alarmList = new ArrayList<String>();
		Alarm dbAlarm = null;
		try {
			Connection con = FMDatabaseHandler.getFMADBConnection();
			Statement stmt;
			ResultSet rs;

			// Create a statement object, the container for the SQL statement.
			String statusCheckSQLQuery = "";
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			statusCheckSQLQuery = NetsimAPIOperatorAPIGetter.ALARMRECORDQUERY
					+ attribute + NetsimAPIOperatorAPIGetter.AND_OOR_LIKE + fdn
					+ "\'" + " and Perceived_severity = " + severity
					+ NetsimAPIOperatorAPIGetter.ORDERBY_INSERT_TIME;

			LOGGER.debug("Query is: " + statusCheckSQLQuery);
			rs = stmt.executeQuery(statusCheckSQLQuery);
			while (rs.next()) {
				dbAlarm = new Alarm();
				dbAlarm.setFdn(rs
						.getString(NetsimAPIOperatorAPIGetter.OBJECT_OF_REFERENCE));
				dbAlarm.setSpecificProblem(rs
						.getString(NetsimAPIOperatorAPIGetter.SPECIFIC_PROBLEM));
				dbAlarm.setProbableCause(rs
						.getString(NetsimAPIOperatorAPIGetter.PROBABLE_CAUSE));
				dbAlarm.setEventType(rs
						.getString(NetsimAPIOperatorAPIGetter.EVENT_TYPE));
				dbAlarm.setSeverity(rs
						.getString(NetsimAPIOperatorAPIGetter.PERCEIVED_SEVERITY));
				alarmList.add(dbAlarm.toString());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw e;
		}
		LOGGER.debug("Total alarms from the Database : " + alarmList.size());
		return alarmList;
	}

	/**
	 * Method getBulkPCAlarmFromDB
	 * 
	 * @description Method to retrieve the result from the Database for the
	 *              action performed.
	 * @param fdn
	 *            -FDN of a node as a String attribute - attribute that is read
	 *            from the attributes file
	 * @return List of Alarm Objects with the values set from the Database in
	 *         OSS as a String.
	 */
	public static List<String> getBulkPCAlarmFromDB(String fdn, String attribute)
			throws Exception {

		List<String> alarmList = new ArrayList<String>();
		Alarm dbAlarm = null;
		try {
			Connection con = FMDatabaseHandler.getFMADBConnection();
			Statement stmt;
			ResultSet rs;

			// Create a statement object, the container for the SQL statement.
			String statusCheckSQLQuery = "";
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			statusCheckSQLQuery = NetsimAPIOperatorAPIGetter.DBQUERY_BULK_PC
					+ attribute + NetsimAPIOperatorAPIGetter.AND_OOR_LIKE + fdn
					+ "\'" + NetsimAPIOperatorAPIGetter.ORDERBY_INSERT_TIME;

			LOGGER.debug("Query is: " + statusCheckSQLQuery);
			rs = stmt.executeQuery(statusCheckSQLQuery);
			while (rs.next()) {
				dbAlarm = new Alarm();
				dbAlarm.setFdn(rs
						.getString(NetsimAPIOperatorAPIGetter.OBJECT_OF_REFERENCE));
				dbAlarm.setProbableCause(rs
						.getString(NetsimAPIOperatorAPIGetter.PROBABLE_CAUSE));
				dbAlarm.setSeverity(rs
						.getString(NetsimAPIOperatorAPIGetter.PERCEIVED_SEVERITY));
				alarmList.add(dbAlarm.toString());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw e;
		}
		LOGGER.debug("Total alarms from the Database : " + alarmList.size());
		return alarmList;
	}
}
