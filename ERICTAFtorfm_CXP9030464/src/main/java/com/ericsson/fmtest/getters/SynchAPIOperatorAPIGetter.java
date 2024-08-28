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

public class SynchAPIOperatorAPIGetter implements ApiGetter{
	
	public static final String DBQUERY = "select TOP 1 Object_of_reference,Record_type from FMA_alarm_log where Object_of_reference like \'";
	public static final String RECORD_TYPE_7 = "\' and Record_type = 7 order by insert_time desc";
	public static final String RECORD_TYPE_8 = "\' and Record_type = 8 order by insert_time desc";
	public static final String OORQUERY = "select Object_of_reference,Perceived_severity from FMA_alarm_list where Object_of_reference like \'%";
}
