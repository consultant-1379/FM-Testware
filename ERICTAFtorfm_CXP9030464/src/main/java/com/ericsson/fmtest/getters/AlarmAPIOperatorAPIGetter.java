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

public class AlarmAPIOperatorAPIGetter implements ApiGetter {

	public static final String FMTestLookUpName = "ejb:FMTestBean-ear-0.0.1-SNAPSHOT/FMTestBean-ejb-0.0.1-SNAPSHOT/FMTestBean!com.ericsson.oss.fmservice.api.beans.FMTestBeanRemote";
	public static final String DBQUERY_SP = "select TOP 1 Object_of_reference, SP_text, PC_text, Perceived_severity, ET_text from FMA_alarm_log where SP_text like \'";
	public static final String DBQUERY_OOR = "select TOP 1 Object_of_reference, SP_text, PC_text, Perceived_severity, ET_text from FMA_alarm_log where Object_of_reference like \'";
	public static final String SSL_ENABLED = "remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED";
	public static final String CONNECTIONS = "remote.connections";
	public static final String HOST = "remote.connection.default.host";
	public static final String PORT = "remote.connection.default.port";
	public static final String SASL_POLICY = "remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS";
	public static final String USERNAME = "remote.connection.default.username";
	public static final String PASSWORD = "remote.connection.default.password";
	public static final String NAMING = "org.jboss.ejb.client.naming";
	public static final String RECORD_TYPE_7 = "Record_type=7 order by insert_time desc";
	public static final String RECORD_TYPE_8 = "Record_type=8 order by insert_time desc";
	public static final String RECORD_TYPE_2 = "Record_type=2 order by insert_time desc";
	public static final String PERCEIVED_SEVERITY_CLEAR = "and Perceived_severity=5 order by insert_time desc";
	public static final String SYNC_START = "Sync_Start";
	public static final String SYNC_END = "Sync_End";
	public static final String SYNC_ABORT = "Sync_Abort";
	public static final String OBJECT_OF_REFERENCE="Object_of_reference";
	public static final String SP_TEXT = "SP_text";
	public static final String PC_TEXT = "PC_text";
	public static final String PERCEIVED_SEVERITY = "Perceived_severity";
	public static final String ET_TEXT = "ET_text";
	public static final String ORDER_BY_CLAUSE ="\' order by insert_time desc";
	
	
	
}