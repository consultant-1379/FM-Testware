package com.ericsson.fmtest.getters;

import com.ericsson.cifwk.taf.ApiGetter;

public class NodeSuspendOperatorAPIGetter implements ApiGetter {


	public static final  String NODE_SUSPEND_QUERY = "select TOP 1 Object_of_reference,Record_type from FMA_alarm_list where Object_of_reference like \'%";
	
	public static final  String RECORD_TYPE = "Record_type";

	
}
