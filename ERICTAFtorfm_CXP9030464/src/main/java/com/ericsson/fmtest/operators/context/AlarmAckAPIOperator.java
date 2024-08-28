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

import com.ericsson.cifwk.taf.ApiOperator;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.handlers.netsim.implementation.NetsimNE;
import com.ericsson.fmtest.getters.AlarmAckAPIOperatorAPIGetter;

public class AlarmAckAPIOperator  implements ApiOperator{

	NetsimOperations netsimOperations = new NetsimOperations();
	ActionSender actionSender = new ActionSender();

	public String alarmAck(NetsimNE node, String fdn, Host host)
			throws Exception {
		

		// cease all alarms;
		netsimOperations.ceaseAllAlarms(node.getSimulation(), node.getName());
		
		// start Supervision
		actionSender.setAlarmSupervision(fdn, 1, host);
		
		actionSender.generateSyncRequest(fdn, host);
		
		// raise Alarm
		netsimOperations.raiseTestAlarm(node.getSimulation(), node.getName(), node.getType());
		
		// ack Alarm
		actionSender.alarmAck(fdn, host);
		
		// unack alarm
		//actionSender.alarmUnAck(fdn, host);

		// verify at Netsim
		String result = netsimOperations.showAlarm(node.getSimulation(),
				node.getName());
		
		// Stop Supervision
		actionSender.setAlarmSupervision(fdn, 0, host);

		if(result.contains(AlarmAckAPIOperatorAPIGetter.ACK_STATE))
			return "true";
		else 
			return "false";
		
	}
	
	public String alarmUnAck(NetsimNE node, String fdn, Host host)
			throws Exception {
		

		// cease all alarms;
		netsimOperations.ceaseAllAlarms(node.getSimulation(), node.getName());
		
		// start Supervision
		actionSender.setAlarmSupervision(fdn, 1, host);
		
		// raise Alarm
		netsimOperations.raiseTestAlarm(node.getSimulation(), node.getName(), node.getType());
		
		actionSender.generateSyncRequest(fdn, host);
		
		// ack Alarm
		actionSender.alarmAck(fdn, host);
		
		// unack alarm
		actionSender.alarmUnAck(fdn, host);

		// verify at Netsim
		String result = netsimOperations.showAlarm(node.getSimulation(),
				node.getName());
		
		// Stop Supervision
		actionSender.setAlarmSupervision(fdn, 0, host);

		if(result.contains(AlarmAckAPIOperatorAPIGetter.UNACK_STATE))
			return "true";
		else 
			return "false";
		
	}
	

}
