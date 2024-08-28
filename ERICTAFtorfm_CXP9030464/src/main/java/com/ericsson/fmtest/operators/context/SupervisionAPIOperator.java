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
import com.ericsson.fmtest.getters.SupervisionAPIOperatorAPIGetter;


public class SupervisionAPIOperator {

	ActionSender action = new ActionSender();
	private static final Logger LOGGER = Logger
			.getLogger(SupervisionAPIOperator.class);

	/**
	 * @method setAlarmSupervison
	 */
	public String setAlarmSupervision(String neName, String fdn, int state,Host host)
			throws Exception {
		action.setAlarmSupervision(fdn, state,host);
		return getSupervisionDbResult(neName);
	}

	/**
	 * @method toggleSupervision
	 * @description - Method to toggle AlarmSupervision for a node in OSS.
	 * @param fdn
	 *            - as a String
	 * @param state
	 *            - as an integer - 0 for AlarmSupervison OFF,1 for ON attribute
	 * @attribute - as a String to be searched in the DPS
	 * @return String a value retrieved from the DPS in TOR for the specified
	 *         attribute
	 */
	/*public String toggleSupervision(final String fdn, final int state,
			final String attribute,Host host) throws Exception {
		// action.createMOs(fdn);

		int _state = 1;
		if (state == 1) {
			_state = 0;
		}
		action.setAlarmSupervision(fdn, _state,host);

		// Waiting for the FMCommunicator-FMService Exchanges
		Utils.sleep(15);
		action.setAlarmSupervision(fdn, state,host);

		// Waiting for the FMCommunicator-FMService Exchanges
		Utils.sleep(15);
		return action.getAlarmSupervisionDPSResult(fdn, attribute);
	}*/

	/**
	 * @method changeHBTimeout
	 * @description - Method to change HeartBeatTimeout for a node in OSS.
	 * @param fdn
	 *            - as a String
	 * @param state
	 *            - as an integer - 0 for AlarmSupervison OFF,1 for ON
	 * @param attribute
	 *            -Attribute to be fetched from the DPS.
	 * @param hbtimeout
	 *            - the timeout value as an integer to be updated in OSS DB for
	 *            the node.
	 * @return String a value retrieved from the DPS in TOR for the specified
	 *         attribute
	 * 
	 */
	/*public String changeHBTimeout(final String fdn, final int state,
			final String attribute, final int hbtimeout,Host host) throws Exception {

		// Comment this line once submit to TOR
		// action.createMOs(fdn);

		int _state = 1;
		if (state == 1)
			_state = 0;

		action.setAlarmSupervision(fdn, _state,host);

		// Waiting for the FMCommunicator-FMService Exchanges
		Utils.sleep(10);
		action.setAlarmSupervison(fdn, state, hbtimeout,host);

		// Waiting for the FMCommunicator-FMService Exchanges
		Utils.sleep(10);
		return action.getHeartBeatSupervisionDPSResult(fdn, attribute);
	}*/

	/**
	 * @method getHB
	 * @description Method to retrieve the HeartBeatSupervision
	 * @param fdn
	 *            -FDN of the Node
	 * @param state
	 *            - as an integer - 0 for AlarmSupervison OFF,1 for ON
	 * @param attribute
	 *            - Attribute to be fetched from the DPS.
	 * @return String a value retrieved from the DPS in TOR for the specified
	 *         attribute
	 */
	/*public String getHB(final String fdn, final int state,
			final String attribute) throws Exception {
		return action.getHeartBeatSupervisionDPSResult(fdn, attribute);
	}*/

	/**
	 * @method getEANodeInfo
	 * @description Method to retrieve the value from EANodeInfo PO.
	 * @param fdn
	 *            -FDN of the Node
	 * @param state
	 *            - as an integer - 0 for AlarmSupervison OFF,1 for ON
	 * @param attribute
	 *            - Attribute to be fetched from the DPS.
	 * @return String a value retrieved from the DPS in TOR for the specified
	 *         attribute
	 */
	/*public String getEANodeInfo(final String fdn, final int state,
			final String attribute) throws Exception {
		return action.getEANodeDPSResult(fdn, attribute);
	}*/

	/**
	 * @method getHBTimeout
	 * @description Method to retrieve the HeartBeatSupervision
	 * @param fdn
	 *            -FDN of the Node
	 * @param state
	 *            - as an integer - 0 for AlarmSupervison OFF,1 for ON
	 * @param attribute
	 *            - Attribute to be fetched from the DPS.
	 * @return String a value retrieved from the DPS in TOR for the specified
	 *         attribute
	 */
	/*public String getHBTimeout(final String fdn, final int state,
			final String attribute) throws Exception {
		return action.getHeartBeatSupervisionDPSResult(fdn, attribute);
	}*/

	public String getSupervisionDbResult(String neName) throws Exception {
		String result = "";
		try {
			Connection con = FMDatabaseHandler.getIMHDBConnection();
			Statement stmt;
			ResultSet rs;
			String statusCheckSQLQuery = "";
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			statusCheckSQLQuery = SupervisionAPIOperatorAPIGetter.ALARM_SUPERVISION_QUERY
					+ neName + "%\' )";
			LOGGER.debug("The Query is :" + statusCheckSQLQuery);
			rs = stmt.executeQuery(statusCheckSQLQuery);
			while (rs.next()) {
				result = rs.getString(SupervisionAPIOperatorAPIGetter.ALARM_SUPERVISION);
			}
		} catch (Exception e) {
			LOGGER.error("Exception in executing the query : ", e);
			throw e;
		}
		return result;
	}

	public String stopexpected() {
		// For Stop Supervision alarmSupervision is false.
		return "false";
	}

	public String startexpected() {
		// For Start Supervision alarmSupervision is true.
		return "true";
	}
}
