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
package com.ericsson.fmtest.operators;

import java.util.*;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.ericsson.cifwk.taf.GenericOperator;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.handlers.netsim.implementation.NetsimNE;
import com.ericsson.fmtest.operators.context.SNMPNetsimAPIOperator;
import com.ericsson.fmtest.operators.context.SNMPNetsimOperations;
import com.ericsson.oss.fmservice.api.beans.Alarm;

public class SNMPNetsimOperator implements GenericOperator {

	SNMPNetsimAPIOperator netsimAPIOperator = new SNMPNetsimAPIOperator();
	SNMPNetsimOperations operations = new SNMPNetsimOperations();
	private static final Logger LOGGER = Logger.getLogger(SNMPNetsimOperator.class);

	/**
	 * Method to return a list of expected Alarm objects with FDN set and SP
	 * set.
	 * 
	 * @param testData
	 * @param attributes
	 * @param severity
	 * @return List of alarm objects.
	 */
	public List<String> expectedAlarmList(String testData,
			Map<String, String> attributes, String severity) {
		String[] data = testData.split("#");
		String sentAlarm = null;
		List<String> sentAlarmList = new ArrayList<String>();
		for (Map.Entry<String, String> entry : attributes.entrySet()) {
			String key = entry.getKey();
			if (key != null) {
				sentAlarm = createExpectedAlarmObject(data[1], data[0],
						data[2], data[3], key, severity).toString();
				sentAlarmList.add(sentAlarm);
			}

		}
		return sentAlarmList;

	}

	public List<String> expectedAlarmList(String testData,
			Map<String, String> spAttributesMap,
			Map<String, String> pcAttributesMap, String severity) {
		String[] data = testData.split("#");
		String sentAlarm = null;
		List<String> sentAlarmList = new ArrayList<String>();
		Iterator<Entry<String, String>> pcAttributesIterator = pcAttributesMap
				.entrySet().iterator();
		for (Map.Entry<String, String> entry : spAttributesMap.entrySet()) {
			String key = entry.getKey();
			String pcKey = null;
			if (key != null) {
				Map.Entry<String, String> pcentry = pcAttributesIterator.next();
				if (pcentry != null) {
					pcKey = pcentry.getKey();
				}
				sentAlarm = createExpectedAlarmObject(data[1], data[0],
						data[2], data[3], key, pcKey, severity).toString();
				sentAlarmList.add(sentAlarm);
			}

		}
		LOGGER.debug("EXpectedAlarmList is " + sentAlarmList.toString());
		return sentAlarmList;

	}

	/**
	 * Method to return a list of expected Alarm objects with FDN set and PC
	 * set.
	 * 
	 * @param testData
	 * @param attributes
	 * @param severity
	 * @return List of alarm objects.
	 */
	public List<String> expectedPCAlarmList(String testData,
			Map<String, String> attributes, String severity) {
		String[] data = testData.split("#");
		String sentAlarm = null;
		List<String> sentAlarmList = new ArrayList<String>();
		for (Map.Entry<String, String> entry : attributes.entrySet()) {
			String key = entry.getKey();
			if (key != null) {
				sentAlarm = createExpectedPCAlarmObject(data[1], data[0],
						data[2], data[3], key, severity).toString();
				sentAlarmList.add(sentAlarm);
			}

		}
		return sentAlarmList;

	}

	/**
	 * Method to create an Expected Alarm Object with Specific Problem and
	 * Perceived Severity set.
	 * 
	 * @param fdn
	 * @param neType
	 * @param simulationName
	 * @param neName
	 * @param specificProblem
	 * @param severity
	 * @return alarm object
	 */

	public Alarm createExpectedAlarmObject(String fdn, String neType,
			String simulationName, String neName, String specificProblem,
			String severity) {

		Alarm sentAlarm = new Alarm();

		sentAlarm.setFdn(fdn);
		String specificProblemValue = specificProblem.split("\\$")[1];
		sentAlarm.setSpecificProblem(specificProblemValue);
		sentAlarm.setSeverity(severity);

		LOGGER.debug("Sent Alarm is:" + sentAlarm.toString());
		return sentAlarm;
	}

	public Alarm createExpectedAlarmObject(String fdn, String neType,
			String simulationName, String neName, String specificProblem,
			String probableCause, String severity) {

		Alarm sentAlarm = new Alarm();
		String eventType = null;
		sentAlarm.setFdn(fdn);
		String specificProblemValue = specificProblem.split("\\$")[1];
		sentAlarm.setSpecificProblem(specificProblemValue);
		if (probableCause != null) {
			String probableCauseValue = probableCause.split("\\$")[1];
			sentAlarm.setProbableCause(probableCauseValue);
		}

		if (neType.equals("WPP")) {
			eventType = "0";
		}
		
		sentAlarm.setSeverity(severity);
		sentAlarm.setEventType(eventType);
		LOGGER.debug("Sent Alarm is:" + sentAlarm.toString());
		return sentAlarm;
	}

	/**
	 * Method to create an Expected Alarm Object with Specific Problem and
	 * Perceived Severity set.
	 * 
	 * @param fdn
	 * @param neType
	 * @param simulationName
	 * @param neName
	 * @param specificProblem
	 * @param severity
	 * @return alarm object
	 */
	public Alarm createExpectedPCAlarmObject(String fdn, String neType,
			String simulationName, String neName, String probableCause,
			String severity) {

		Alarm sentAlarm = new Alarm();

		sentAlarm.setFdn(fdn);
		String problemCauseValue = probableCause.split("\\$")[1];
		sentAlarm.setProbableCause(problemCauseValue);

		/*if (neType.equalsIgnoreCase("URAN")
				|| neType.equalsIgnoreCase("JUNIPER")
				|| neType.equalsIgnoreCase("LRAN")) {
			severity = "" + (Integer.parseInt(severity) - 1);
		}*/
		sentAlarm.setSeverity(severity);

		LOGGER.debug("Sent Alarm is:" + sentAlarm.toString());
		return sentAlarm;
	}

	public String raiseAlarm(String fdn, String neType, String simulationName,
			String neName, String spText, String severity,Host host) {

		String retResult = null;
		try {
			retResult = netsimAPIOperator.raiseAlarm(fdn, neType,
					simulationName, neName, spText, severity,host);
		} catch (Exception e) {
			LOGGER.error(e.getStackTrace().toString());
		}
		return retResult;
	}

	public String raiseAlarm(String fdn, String neType, String simulationName,
			String neName, String spText, String pcText, String severity,Host host) {

		String retResult = null;
		try {
			retResult = netsimAPIOperator.raiseAlarm(fdn, neType,
					simulationName, neName, spText, pcText, severity,host);
		} catch (Exception e) {
			LOGGER.error(e.getStackTrace().toString());
		}
		return retResult;
	}

	public String raiseAlarm_PC(String fdn, String neType,
			String simulationName, String neName, String pc, String severity) {

		String retResult = null;
		try {
			retResult = netsimAPIOperator.raiseAlarm_PC(fdn, neType,
					simulationName, neName, pc, severity);
		} catch (Exception e) {
			LOGGER.error(e.getStackTrace().toString());
		}
		return retResult;
	}

	public String[] raiseDefaultAlarm(String simulationName, String neName, String neType) {
		String[] retResult = null;
		try {
			retResult = netsimAPIOperator.raiseDefaultAlarm(simulationName,
					neName, neType);
		} catch (Exception e) {
			LOGGER.error(e.getStackTrace().toString());
		}
		return retResult;
	}

	public String raiseDefaultClearAlarm(String simulationName, String neName,
			String neType, String alarmId) {
		String retResult = null;
		try {
			retResult = netsimAPIOperator.raiseDefaultClearAlarm(
					simulationName, neName, neType, alarmId);
		} catch (Exception e) {
			LOGGER.error(e.getStackTrace().toString());
		}
		return retResult;
	}
	
	public String raiseDefaultSNMPClearAlarm(String simulationName, String neName,
			String neType, String alarmId) {
		String retResult = null;
		try {
			retResult = netsimAPIOperator.raiseDefaultSNMPClearAlarm(
					simulationName, neName, neType, alarmId);
		} catch (Exception e) {
			LOGGER.error(e.getStackTrace().toString());
		}
		return retResult;
	}

	public String raiseHBAlarm(NetsimNE node) {

		String retResult = null;
		try {
			retResult = netsimAPIOperator.raiseHBAlarm(node);
		} catch (Exception e) {
			LOGGER.error(e.getStackTrace().toString());
		}
		return retResult;
	}

	public String raiseHBClearAlarm(NetsimNE node) {
		String retResult = null;
		try {
			retResult = netsimAPIOperator.raiseHBClearAlarm(node);
		} catch (Exception e) {
			LOGGER.error(e.getStackTrace().toString());
		}
		return retResult;
	}

	public List<String> sendNetsimAlarms(String testData,
			Map<String, String> attributesMap, String severity,Host host) {
		String[] data = testData.split("#");
		List<String> outputAlarmList = null;
		int i = 0;
		String specificPrb = "(";
		for (Map.Entry<String, String> entry : attributesMap.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if (key != null) {
				if (i != 0)
					specificPrb = specificPrb.concat(",");
				specificPrb = specificPrb.concat(key.split("\\$")[1]);
				String sent = raiseAlarm(data[1], data[0], data[2], data[3],
						value, severity,host);
				if (sent.contains("ERROR")) {
					LOGGER.error("ERROR: Sending alarm through NETSIM FAILED for SPText : "
							+ value);
				}
			}
			++i;
		}
		specificPrb = specificPrb.concat(")");
		LOGGER.debug("Specific Problem String is :" + specificPrb);
		try {
			com.ericsson.fmtest.operators.Utils.sleep(10);
			if (data[0].equals("WPP") && severity.equals("6")) {
				netsimAPIOperator.ceaseAllAlarms(data[2],data[3]);
				com.ericsson.fmtest.operators.Utils.sleep(20);
			}
			outputAlarmList = SNMPNetsimAPIOperator.getBulkAlarmFromDB(data[1],
					specificPrb);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return outputAlarmList;
	}

	public List<String> sendNetsimAlarms(String testData,
			Map<String, String> spattributesMap,
			Map<String, String> pcattributesMap, String severity,Host host) {
		String[] data = testData.split("#");
		List<String> outputAlarmList = null;
		int i = 0;
		String specificPrb = "(";
		String probableCause = "(";
		Iterator<Entry<String, String>> pcAttributesIterator = pcattributesMap
				.entrySet().iterator();
		for (Map.Entry<String, String> entry : spattributesMap.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			String pcKey = null;
			Map.Entry<String, String> pcentry = pcAttributesIterator.next();
			if (key != null) {
				if (pcentry != null) {
					pcKey = pcentry.getKey();
				}
				if (i != 0) {
					specificPrb = specificPrb.concat(",");
					probableCause = probableCause.concat(",");
				}

				specificPrb = specificPrb.concat(key.split("\\$")[1]);
				probableCause = probableCause.concat(key.split("\\$")[1]);
				String sent = raiseAlarm(data[1], data[0], data[2], data[3],
						key.split("\\$")[1], pcKey.split("\\$")[1], severity,host);
				if (sent.contains("ERROR")) {
					LOGGER.error("ERROR: Sending alarm through NETSIM FAILED for SPText : "
							+ value);
				}
			}
			++i;
		}
		/*if (data[0].equalsIgnoreCase("URAN")
				|| data[0].equalsIgnoreCase("JUNIPER")
				|| data[0].equalsIgnoreCase("LRAN")) {
			severity = "" + (Integer.parseInt(severity) - 1);
		}*/
		specificPrb = specificPrb.concat(")");
		probableCause = probableCause.concat(")");
		LOGGER.debug("Specific Problem String is :" + specificPrb);
		LOGGER.debug("Probable Cause String is :" + probableCause);
		try {
			com.ericsson.fmtest.operators.Utils.sleep(10);
			if (data[0].equals("WPP") && severity.equals("5")) {
				netsimAPIOperator.ceaseAllAlarms(data[2],data[3]);
				com.ericsson.fmtest.operators.Utils.sleep(20);
			}
			outputAlarmList = SNMPNetsimAPIOperator.getAlarmFromDB(data[1],
					specificPrb, probableCause, severity);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return outputAlarmList;
	}

	public List<String> sendNetsimAlarms_PC(String testData,
			Map<String, String> attributesMap, String severity) {
		String[] data = testData.split("#");
		List<String> outputAlarmList = null;
		int i = 0;
		String probableCause = "(";
		for (Map.Entry<String, String> entry : attributesMap.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if (key != null) {
				if (i != 0)
					probableCause = probableCause.concat(",");
				probableCause = probableCause.concat(key.split("\\$")[1]);
				String sent = raiseAlarm_PC(data[1], data[0], data[2], data[3],
						key.split("\\$")[1], severity);
				if (sent.contains("ERROR")) {
					LOGGER.error("ERROR: Sending alarm through NETSIM FAILED for PCText : "
							+ value);
				}
			}
			++i;
		}
		probableCause = probableCause.concat(")");
		LOGGER.debug("Probable Cause String is : " + probableCause);
		try {
			com.ericsson.fmtest.operators.Utils.sleep(10);
			if (data[0].equals("WPP") && severity.equals("5")) {
				netsimAPIOperator.ceaseAllAlarms(data[2],data[3]);
				com.ericsson.fmtest.operators.Utils.sleep(20);
			}
			outputAlarmList = SNMPNetsimAPIOperator.getBulkPCAlarmFromDB(data[1],
					probableCause);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return outputAlarmList;
	}

	public String expectedAlarmResult(String fdn) {
		Alarm alarm = new Alarm();
		alarm.setFdn(fdn);
		alarm.setSpecificProblem("testAlarm");
		LOGGER.debug("The Expected result:" + alarm.toString());
		return alarm.toString();
	}

	public String expectedHBAlarmResult(String fdn, int state) {
		Alarm alarm = new Alarm();
		alarm.setFdn(fdn);
		String _state = "1";
		if (state == 1)
			_state = "5";
		alarm.setSeverity(_state);
		LOGGER.debug("The Expected result:" + alarm.toString());
		return alarm.toString();
	}

	public String startAlarmBurst(String simulationName, String neName,
			String activityName) {
		String retResult = null;
		try {
			retResult = netsimAPIOperator.startAlarmBurst(simulationName, neName,
					activityName);
		} catch (Exception e) {
			LOGGER.error(e.getStackTrace().toString());
		}
		return retResult;
	}

	public String stopAlarmBurst(String simulationName, String neName,
			String activityName) {
		String retResult = null;
		try {
			retResult = netsimAPIOperator.stopAlarmBurst(simulationName, neName,
					activityName);
		} catch (Exception e) {
			LOGGER.error(e.getStackTrace().toString());
		}
		return retResult;
	}
	
	public void startSupervision(String testData,Host host){
		netsimAPIOperator.startSupervision(testData.split("#")[1],host);
	}

}
