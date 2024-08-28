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
package com.ericsson.fmtest.test.cases;

import org.testng.annotations.Test;

import com.ericsson.cifwk.taf.TestCase;
import com.ericsson.cifwk.taf.TorTestCaseHelper;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.handlers.netsim.implementation.NetsimNE;
import com.ericsson.fmtest.operators.AlarmAckOperator;
import com.ericsson.fmtest.test.data.HeartBeatTestData;

public class AlarmAck extends TorTestCaseHelper implements TestCase {

	AlarmAckOperator alarmAckOperator = new AlarmAckOperator();

	@Test(dataProvider = "HBTestData", dataProviderClass = HeartBeatTestData.class)
	public void alarmAckTest(NetsimNE node, String fdn, Host host) {
		assertEquals(alarmAckOperator.alarmAck(node,fdn,host), alarmAckOperator.expectedResult());
	}
	
	@Test(dataProvider = "HBTestData", dataProviderClass = HeartBeatTestData.class)
	public void alarmUnAckTest(NetsimNE node, String fdn, Host host) {
		assertEquals(alarmAckOperator.alarmUnAck(node,fdn,host),alarmAckOperator.expectedResult());
	}

}
