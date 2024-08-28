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

import org.apache.log4j.Logger;

import com.ericsson.cifwk.taf.GenericOperator;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.handlers.netsim.implementation.NetsimNE;
import com.ericsson.fmtest.operators.context.AlarmAckAPIOperator;

public class AlarmAckOperator implements GenericOperator {

	AlarmAckAPIOperator alarmAckAPIOperator = new AlarmAckAPIOperator();
	private static final Logger LOGGER = Logger
			.getLogger(AlarmAckOperator.class);

	public String alarmAck(NetsimNE node,String fdn,Host host) {
		String retResult = null;
		try {
			retResult = alarmAckAPIOperator.alarmAck(node,fdn,host);
		} catch (Exception e) {
			LOGGER.error(e.getStackTrace().toString());
		}
		LOGGER.debug("AlarmAck return result to TC is:"+retResult);
		return retResult;
	}
	
	public String alarmUnAck(NetsimNE node,String fdn,Host host) {
		String retResult = null;
		try {
			retResult = alarmAckAPIOperator.alarmUnAck(node,fdn,host);
		} catch (Exception e) {
			LOGGER.error(e.getStackTrace().toString());
		}
		return retResult;
	}
	
	public String expectedResult(){
		return "true";
	}


}
