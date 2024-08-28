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
import com.ericsson.fmtest.operators.context.SyncAPIOperator;

public class SyncOperator implements GenericOperator {
	
	private static final Logger LOGGER = Logger.getLogger(SyncOperator.class);
	
	public String sendSyncRequest(String fdn,Host host){
		String retResult = null;
		try {
			retResult = new SyncAPIOperator().sendSyncRequest(fdn,host);
		} catch (Exception e) {
			LOGGER.error(e.getStackTrace().toString());
		}
		LOGGER.debug("Return to TestCase,value is:"+retResult);
		return retResult;
	}
	
	public String ceaseAllandSync(String testData,Host host){
		String retResult = null;
		try {
			retResult = new SyncAPIOperator().ceaseAllandSync(testData,host);
		} catch (Exception e) {
			LOGGER.error(e.getStackTrace().toString());
		}
		LOGGER.debug("Return to TestCase,value is:"+retResult);
		return retResult;
	}
	
	public String ceaseAllandRaise(String testData,Host host){
		String retResult = null;
		try {
			retResult = new SyncAPIOperator().ceaseAllandRaise(testData,host);
		} catch (Exception e) {
			LOGGER.error(e.getStackTrace().toString());
		}
		return retResult;
	}
	
	public String getExpectedResult(int state){
		if(state==1)
			return "true";
		else 
			return "false";
	}

}
