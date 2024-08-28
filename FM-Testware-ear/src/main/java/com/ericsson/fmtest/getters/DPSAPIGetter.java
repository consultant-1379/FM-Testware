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

import java.util.HashMap;
import java.util.Map;

import com.ericsson.cifwk.taf.ApiGetter;

public class DPSAPIGetter implements ApiGetter {

	public static final String HEARTBEAT_SUPERVISION_TYPE = "HeartbeatSupervision";
	public static final String ALARM_SUPERVISION_TYPE = "AlarmSupervision";
	public static final String NODE_INFO_TYPE = "NodeInfo";

	// Alarm Supervision Attrs
	public static final String CLEARALL_BEHAVIOUR = "clearAllBehaviour";
	public static final String FILTER_INFO = "filterInfo";
	public static final String ENTITY_ADDRES_TO_PROTOCOL = "EntityAddresToProtocol";
	public static final String PROTOCOL_INFO_ASSOCIATION_NAME = "protocolInfoAssociationName";
	public static final int HEARTBEAT_TIMEOUT_VALUE = 300;
	public static final String SOURCE_TYPE = "sourceType";
	
	
	public static Map<String,String> dataMap = null;
	static {
		dataMap = new HashMap<String,String>();
		dataMap.put("ALARM_SUPERVISION","alarmSupervision");
		dataMap.put("AUTOMATIC_SYNC","automaticSynchronization");
		dataMap.put("HB_SUPERVISION","heartbeatSupervison");
		dataMap.put("HB_TIMEOUT","heartbeatTimeout");
		dataMap.put("IS_NODE_SUSPENDED","isNodeSuspended");
		dataMap.put("DELTA_SYNC_SUPPORTED","deltaSynchSupported");
		dataMap.put("SOURCE_SYNC_SUPPORTED", "sourceSynchSupported");
		dataMap.put("SYNCH_ON_COMMIT_FAILURE_CLEAR", "syncOnCommitFailureClear");
		dataMap.put("ACKNOWLEDGE_SUPPORTED","acknowledgeSupported");
		dataMap.put("CLOSED_SUPPORTED", "closedSupported");
		dataMap.put("COMMUNICATION_TIMEOUT", "communicationTimeout");
		dataMap.put("SUBORDINATE_OBJECT_SYNCSUPPORTED", "subordinateObjectSyncSupported");
		dataMap.put("SUPERVISION_ON", "1");
		dataMap.put("SUPERVISION_OFF", "0");
	}
}
