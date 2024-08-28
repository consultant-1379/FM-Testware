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

import org.apache.log4j.Logger;

import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.handlers.netsim.implementation.NetsimNE;
import com.ericsson.cifwk.taf.handlers.netsim.implementation.SshNetsimHandler;
import com.ericsson.fmtest.getters.NetsimAPIOperatorAPIGetter;

public class NetsimOperations {

	private static final Logger LOGGER = Logger
			.getLogger(NetsimOperations.class);

	static SshNetsimHandler handler = new SshNetsimHandler(
			DataHandler.getHostByName(NetsimAPIOperatorAPIGetter.NETSIM));

	/**
	 * @method stopNode
	 * @description Method to stop a node
	 * @param simulation
	 *            - as a String,name of the simulation in Netsim Server
	 * @param node
	 *            - the Node which is to be stopped
	 */
	public void stopNode(String simulation, NetsimNE node) throws Exception {
		handler.stopNE(simulation, node);
	}

	/**
	 * @method startNode
	 * @description Method to stop a node
	 * @param simulation
	 *            - as a String,name of the simulation in Netsim Server
	 * @param node
	 *            - the Node which is to be started
	 */
	public void startNode(String simulation, NetsimNE node) throws Exception {
		handler.startNE(simulation, node);
	}

	/**
	 * @method sendAlarm
	 * 
	 * @description Method to send an Alarm on a node.
	 * @param fdn
	 *            -the FDN of the node as a String
	 * @param neType
	 *            - Type of the Network Element Eg.LRAN,URAN
	 * @param simulationName
	 *            - Name of the Simulation in which the node is present in
	 *            Netsim Server
	 * @param neName
	 *            - Node Name in Netsim Server
	 * @param spText
	 *            - Specific Problem Text as String
	 * @param severity
	 *            - Severity of alarm
	 * @return the value of executeCommandWithSimulation method as a String
	 */
	public String sendAlarm(String fdn, String neType, String simulationName,
			String neName, String spText, String severity) throws Exception {

		String selectCmd = NetsimAPIOperatorAPIGetter.SELECT;
		selectCmd = selectCmd.concat(neName);
		String startNECmd = NetsimAPIOperatorAPIGetter.START;

		String alarmSendCmd = NetsimCommandBuilder.buildProblem(neType, spText,
				severity);
		String result = handler.executeCommandWithSimulation(simulationName,
				selectCmd, startNECmd, alarmSendCmd);

		LOGGER.debug("Netsim Command is:" + alarmSendCmd + "\n result = "
				+ result);

		return result;
	}

	public String sendFullAlarm(String fdn, String neType,
			String simulationName, String neName, String spText, String pcText,
			String severity) throws Exception {

		String selectCmd = NetsimAPIOperatorAPIGetter.SELECT;
		selectCmd = selectCmd.concat(neName);
		String startNECmd = NetsimAPIOperatorAPIGetter.START;

		String alarmSendCmd = NetsimCommandBuilder.buildCommand(neType, spText,
				pcText, severity);
		String result = handler.executeCommandWithSimulation(simulationName,
				selectCmd, startNECmd, alarmSendCmd);

		LOGGER.debug("Netsim Command is:" + alarmSendCmd + "\n result = "
				+ result);

		return result;
	}

	/**
	 * @method sendAlarm_PC
	 * 
	 * @description Method to send an Alarm on a node.
	 * @param fdn
	 *            -the FDN of the node as a String
	 * @param neType
	 *            - Type of the Network Element Eg.LRAN,URAN
	 * @param simulationName
	 *            - Name of the Simulation in which the node is present in
	 *            Netsim Server
	 * @param neName
	 *            - Node Name in Netsim Server
	 * @param probableCause
	 *            - Specific Problem Text as String
	 * @param severity
	 *            - Severity of alarm
	 * @return the value of executeCommandWithSimulation method as a String
	 */
	public String sendAlarm_PC(String fdn, String neType,
			String simulationName, String neName, String probableCause,
			String severity) throws Exception {
		System.out.println("Received parameter:" + probableCause);

		String selectCmd = NetsimAPIOperatorAPIGetter.SELECT;
		selectCmd = selectCmd.concat(neName);
		String startNECmd = NetsimAPIOperatorAPIGetter.START;

		String alarmSendCmd = NetsimCommandBuilder.buildCause(neType,
				probableCause, severity);
		String result = handler.executeCommandWithSimulation(simulationName,
				selectCmd, startNECmd, alarmSendCmd);

		LOGGER.debug("Netsim Command is:" + alarmSendCmd + "\n result = "
				+ result);

		return result;
	}

	/**
	 * Method to cease all alarms present on a Node
	 * 
	 * @param simulationName
	 * @param neName
	 *            Node name in the simulation of a Netsim Server
	 */
	public void ceaseAllAlarms(String simulationName, String neName) {
		String selectCmd = NetsimAPIOperatorAPIGetter.SELECT;
		selectCmd = selectCmd.concat(neName);
		String startNECmd = NetsimAPIOperatorAPIGetter.START;
		String ceaseAlarmsCmd = NetsimAPIOperatorAPIGetter.CEASEALL;
		String result = handler.executeCommandWithSimulation(simulationName,
				selectCmd, startNECmd, ceaseAlarmsCmd);
		LOGGER.debug("Result of the ceaseAllAlarms command execution is:"
				+ result);
	}

	// TODO FIX SourceType
	/**
	 * Method to raise an alarm with default Parameters.
	 * 
	 * @param simulationName
	 * @param neName
	 *            Node name in the simulation of a Netsim Server
	 */
	public void raiseTestAlarm(String simulationName, String neName, String neType) {
		String selectCmd = NetsimAPIOperatorAPIGetter.SELECT;
		selectCmd = selectCmd.concat(neName);
		String startNECmd = NetsimAPIOperatorAPIGetter.START;
		String sendAlarmCmd = "sendalarm:";
		sendAlarmCmd = sendAlarmCmd.concat(NetsimCommandBuilder.spAttribute
				.get(neType));
		sendAlarmCmd = sendAlarmCmd.concat("=\"testAlarm\";");
		LOGGER.debug("sendAlarmCommand built is:" + sendAlarmCmd);
		String result = handler.executeCommandWithSimulation(simulationName,
				selectCmd, startNECmd, sendAlarmCmd);
		LOGGER.debug("Result of the raiseTestAlarm command execution is:"
				+ result);
	}

	public void raiseTestClearAlarm(String simulationName, String neName,
			String neType) {
		String selectCmd = NetsimAPIOperatorAPIGetter.SELECT;
		selectCmd = selectCmd.concat(neName);
		String startNECmd = NetsimAPIOperatorAPIGetter.START;
		String sendAlarmCmd = NetsimAPIOperatorAPIGetter.SENDALARM;
		sendAlarmCmd = sendAlarmCmd.concat(NetsimCommandBuilder.spAttribute
				.get(neType));
		sendAlarmCmd = sendAlarmCmd.concat("=\"testAlarm\",");
		String defaultClearAlarmCmd = sendAlarmCmd
				.concat(NetsimCommandBuilder.perceivedSeverity.get(neType));
		String result = handler.executeCommandWithSimulation(simulationName,
				selectCmd, startNECmd, defaultClearAlarmCmd);
		LOGGER.debug("Result of the command execution is:" + result);
	}

	public String showAlarm(String simulationName, String neName) {
		String selectCmd = NetsimAPIOperatorAPIGetter.SELECT;
		selectCmd = selectCmd.concat(neName);
		String startNECmd = NetsimAPIOperatorAPIGetter.START;
		String showAlarmCmd = NetsimAPIOperatorAPIGetter.SHOWALARM;
		String result = handler.executeCommandWithSimulation(simulationName,
				selectCmd, startNECmd, showAlarmCmd);
		LOGGER.debug("Result of the  showAlarm command execution is:" + result);
		return result;
	}

	public String startAlarmBurst(String simulationName, String neName,
			String activityName) throws Exception {

		String result = null;

		String select = NetsimAPIOperatorAPIGetter.SELECT;
		select = select.concat(neName);

		String setactivity = NetsimAPIOperatorAPIGetter.SETACTIVITY;
		setactivity = setactivity.concat(" +");
		setactivity = setactivity.concat(activityName);

		result = handler.executeCommandWithSimulation(simulationName,
				select, NetsimAPIOperatorAPIGetter.START, setactivity,
				NetsimAPIOperatorAPIGetter.STARTACTIVITY);

		LOGGER.debug("Result of Script : " + result);

		return result;
	}

	public String stopAlarmBurst(String simulationName, String neName,
			String activityName) throws Exception {

		String result = null;

		String select = NetsimAPIOperatorAPIGetter.SELECT;
		select = select.concat(neName);

		String setactivity = NetsimAPIOperatorAPIGetter.SETACTIVITY;
		setactivity = setactivity.concat(" -");
		setactivity = setactivity.concat(activityName);

		result = handler.executeCommandWithSimulation(simulationName,
				select, NetsimAPIOperatorAPIGetter.START, setactivity,
				NetsimAPIOperatorAPIGetter.STARTACTIVITY);

		LOGGER.debug("Result of Script : " + result);

		return result;
	}

}
