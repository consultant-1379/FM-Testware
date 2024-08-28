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
import com.ericsson.fmtest.test.data.AlarmSynchronizationTestData;

public class AlarmSynchronization extends TorTestCaseHelper implements TestCase {

	SyncOperator syncobj = new SyncOperator();

	@Test(dataProvider = "AlarmSyncTestData", dataProviderClass = AlarmSynchronizationTestData.class)
	public void alarmSyncTest(String testData, Host host) {
		assertEquals(syncobj.sendSyncRequest(testData, host),
				syncobj.getExpectedResult(1));
	}

}
