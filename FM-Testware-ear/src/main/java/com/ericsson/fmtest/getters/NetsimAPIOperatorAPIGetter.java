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
package com.ericsson.fmtest.getters;

import com.ericsson.cifwk.taf.ApiGetter;

public class NetsimAPIOperatorAPIGetter implements ApiGetter {

	final static public int HEARTBEATSLEEPTIME = 200; // 200 seconds
	final static public int SLEEPTIME = 20;
	final static public String jdbcTds = "jdbc:sybase:Tds:";
	final static public String QUERY = "select TOP 1 Object_of_reference,Perceived_severity from FMA_alarm_list where Object_of_reference like \'%";
	final static public String DBQUERY_SP ="select TOP 1 Object_of_reference, Specific_problem, SP_text, PC_text, Perceived_severity, ET_text from FMA_alarm_list where Specific_problem = ";
	final static public String DBQUERY_BULK_SP ="select Object_of_reference, Specific_problem, SP_text, PC_text, Perceived_severity, ET_text from FMA_alarm_list where Specific_problem in ";
	
	final static public String DBQUERY_BULK_PC ="select Object_of_reference, Probable_cause, SP_text, PC_text, Perceived_severity, ET_text from FMA_alarm_list where Probable_cause in ";
	
	final static public String DBQUERY_PC = "select Object_of_reference, SP_text, PC_text, Perceived_severity, ET_text from FMA_alarm_list where PC_text like \'";
	
	final static public String ALARMRECORDQUERY = "select TOP 50 Object_of_reference, Specific_problem, Probable_cause, SP_text, PC_text, Perceived_severity, ET_text,Event_type from FMA_alarm_list where Specific_problem in ";
	final static public String OOR_SPTEXT = "select TOP 1 Object_of_reference,SP_text from FMA_alarm_list where Object_of_reference like \'%";
	final static public String SP_TEXT_ORDERBY = "%\' and SP_text like \'testAlarm\' order by insert_time desc";
	final static public String SP_TEXT_PS_CLEAR = "%\' and SP_text like \'testAlarm\' and Perceived_severity=5 order by insert_time desc";
	
	public static final String OBJECT_OF_REFERENCE="Object_of_reference";
	public static final String SP_TEXT = "SP_text";
	public static final String PC_TEXT = "PC_text";
	public static final String EVENT_TYPE = "Event_type";
	public static final String PERCEIVED_SEVERITY = "Perceived_severity";
	public static final String ET_TEXT = "ET_text";
	public static final String AND_PERCEIVED_SEVERITY = "%\' and Perceived_severity=";
	public static final String AND_RECORD_TYPE = " and Record_type=";
	public static final String ORDERBY_INSERT_TIME = " order by insert_time desc";
	public static final String SPECIFIC_PROBLEM = "Specific_problem";
	public static final String PROBABLE_CAUSE = "Probable_cause";
	public static final String AND_OOR_LIKE = " and Object_of_reference like \'";
	
	public static final String SELECT = ".select ";
	public static final String START = " .start ";
	public static final String  CEASEALL = "ceasealarm:all;";
	public static final String  CEASEID = "ceasealarm:id=";
	public static final String SENDALARM = "sendalarm:";
	public static final String SHOWALARM = "showalarm;";
	
	public static final String NETSIM = "netsim";
	public static final String SETACTIVITY = ".setactivity";
	public static final String STARTACTIVITY = ".startactivity";
	
}