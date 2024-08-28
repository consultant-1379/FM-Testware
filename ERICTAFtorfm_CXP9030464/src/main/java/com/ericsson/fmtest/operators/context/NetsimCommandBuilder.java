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

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class NetsimCommandBuilder {

	public static String defaultSendCommand = "sendalarm;";
	public static String sendCommand = "sendalarm:";
	public static Map<String,String> attributes = new HashMap<String,String>();
	public static Map<String, String> spAttribute = new HashMap<String, String>();
	public static Map<String, String> pcAttribute = new HashMap<String, String>();
	public static Map<String, String> perceivedSeverity = new HashMap<String, String>();
	private static final Logger LOGGER = Logger
			.getLogger(NetsimCommandBuilder.class);
	
	static {
		attributes.put("WPP","problem#cause#type");
		attributes.put("LRAN","problem#cause#type");
		attributes.put("URAN","problem#cause#type");
	}
	static {
		pcAttribute.put("WPP","cause");
		pcAttribute.put("LRAN","cause");
		pcAttribute.put("URAN","cause");
	}
	static {
		spAttribute.put("URAN", "problem");
		spAttribute.put("SDN", "problem");
		spAttribute.put("WPP", "problem");
		spAttribute.put("STN", "problem");
		spAttribute.put("SAN", "problem");
		spAttribute.put("MLTN", "problem");
		spAttribute.put("JUNIPER","srcid");
		spAttribute.put("OMP", "specific_problem");
		spAttribute.put("DMX", "specific_problem");
		spAttribute.put("MMC", "specificproblem");
		spAttribute.put("LRAN", "problem");

		perceivedSeverity.put("RNC", "severity=6;");
	}

	/**
	 * @method buildProblem
	 * @description This Method is for building sendalarm Command with
	 *              attributes problem and severity
	 *              eg:sendalarm:problem="123",severity=1;
	 */
	public static String buildProblem(String neType, String spText,
			String severity) {
		String retCommand = defaultSendCommand;

		String cmd = attributes.get(neType);
		if (cmd != null) {
			String[] temp = cmd.split("#");
			String command = sendCommand;
			command = command.concat(temp[0]);
			command = command.concat("=\"");
			command = command.concat(spText);
			command = command.concat("\"");
			command = command.concat(",severity=").concat(severity);
			command = command.concat(";");
			retCommand = command;
		}
		LOGGER.debug("The Netsim Command to be executed is : " + retCommand);
		return retCommand;
	}
	
	/**
	 * @method buildCause
	 * @description This Method is for building sendalarm Command with
	 *              attributes cause and severity
	 *              eg:sendalarm:cause="123",severity=1;
	 */
	public static String buildCause(String neType, String spText,
			String severity) {
		String retCommand = defaultSendCommand;

		String cmd = attributes.get(neType);
		if (cmd != null) {
			String[] temp = cmd.split("#");
			String command = sendCommand;
			command = command.concat(temp[1]);
			command = command.concat("=\"");
			command = command.concat(spText);
			command = command.concat("\"");
			command = command.concat(",severity=").concat(severity);
			command = command.concat(";");
			retCommand = command;
		}
		LOGGER.debug("The Netsim Command to be executed is : " + retCommand);
		return retCommand;
	}
	
	public static String buildCommand(String neType, String spText,String pcText,
			String severity) {
		String retCommand = defaultSendCommand;
		String eventType = null;
		String cmd = attributes.get(neType);
		if(neType.equals("LRAN")||neType.equals("URAN"))
			eventType = "ET_COMMUNICATIONS_ALARM";
		else if(neType.equals("WPP"))
			eventType = "0";
		if (cmd != null) {
			String[] temp = cmd.split("#");
			String command = sendCommand;
			command = command.concat(temp[0]);
			command = command.concat("=\"");
			command = command.concat(spText);
			command = command.concat("\",");
			command = command.concat(temp[1]);
			command = command.concat("=\"");
			command = command.concat(pcText);
			command = command.concat("\",");
			command = command.concat(temp[2]);
			command = command.concat("=\"");
			command = command.concat(eventType);
			command = command.concat("\",");
			command = command.concat("severity=").concat(severity);
			command = command.concat(";");
			retCommand = command;
		}
		LOGGER.debug("The Netsim Command to be executed is : " + retCommand);
		return retCommand;
	}
	
	

}
