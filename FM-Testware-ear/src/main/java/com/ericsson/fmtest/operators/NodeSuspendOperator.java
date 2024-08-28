package com.ericsson.fmtest.operators;

import org.apache.log4j.Logger;

import com.ericsson.cifwk.taf.GenericOperator;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.handlers.netsim.implementation.NetsimNE;
import com.ericsson.fmtest.operators.context.ActionSender;
import com.ericsson.fmtest.operators.context.NodeSuspendContextOperator;

public class NodeSuspendOperator implements GenericOperator {

	NodeSuspendContextOperator nodeSuspendContextOperator = new NodeSuspendContextOperator();
	ActionSender actionSender = new ActionSender();

	private static final Logger LOGGER = Logger
			.getLogger(NodeSuspendOperator.class);

	public String nodeSuspend(NetsimNE node, String fdn, String activityName,Host host) {
		String retResult = null;
		try {
			actionSender.setAlarmSupervision(fdn,1, host); //To make sure AlarmSupervision is ON .
			nodeSuspendContextOperator.startAlarmBurst(node, fdn, activityName);

			retResult = nodeSuspendContextOperator.getNodeSuspendDbResult(node
					.getName());

			nodeSuspendContextOperator.stopAlarmBurst(node, fdn, activityName);

		} catch (Exception e) {
			LOGGER.error(e.getStackTrace().toString());
		}

		return retResult;
	}

	public String expectedResult() {
		return "true";
	}

}
