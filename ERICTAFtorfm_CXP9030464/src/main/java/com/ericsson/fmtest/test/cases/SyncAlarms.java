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
import com.ericsson.fmtest.operators.SyncOperator;
import com.ericsson.fmtest.test.data.SyncAlarmsTestData;

public class SyncAlarms extends TorTestCaseHelper implements TestCase {
	
	SyncOperator syncOperator = new SyncOperator();
	
	@Test(dataProvider = "SyncData", dataProviderClass = SyncAlarmsTestData.class)
	public void zeroSyncAlarmsTest(String testData,Host host) {
		assertEquals(syncOperator.ceaseAllandSync(testData,host),syncOperator.getExpectedResult(1));
	}
	
	@Test(dataProvider = "SyncData", dataProviderClass = SyncAlarmsTestData.class,dependsOnMethods={"zeroSyncAlarmsTest"})
	public void syncAlarmsTest(String testData,Host host) {
		assertEquals(syncOperator.ceaseAllandRaise(testData,host),syncOperator.getExpectedResult(0));
	}

}
