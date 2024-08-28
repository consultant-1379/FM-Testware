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
import com.ericsson.cifwk.taf.handlers.netsim.implementation.NetsimNE;
import com.ericsson.fmtest.operators.NetsimOperator;
import com.ericsson.fmtest.test.data.HeartBeatTestData;

public class HeartBeat extends TorTestCaseHelper implements TestCase {

	NetsimOperator hbobj = new NetsimOperator();

	@Test(dataProvider = "NetsimHBTestData", dataProviderClass = HeartBeatTestData.class)
	public void checkHBTest(NetsimNE node, String fdn) {
		assertEquals(hbobj.raiseHBAlarm(node),
				hbobj.expectedHBAlarmResult(fdn, 0));
	}

	@Test(dataProvider = "NetsimHBTestData", dataProviderClass = HeartBeatTestData.class, dependsOnMethods = { "checkHBTest" })
	public void checkHBClearTest(NetsimNE node, String fdn) {
		assertEquals(hbobj.raiseHBClearAlarm(node),
				hbobj.expectedHBAlarmResult(fdn, 1));
	}
}
