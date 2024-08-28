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

import com.ericsson.cifwk.taf.TestCase;
import com.ericsson.cifwk.taf.TorTestCaseHelper;
import com.ericsson.fmtest.operators.SupervisionOperator;
import com.ericsson.fmtest.test.data.AlarmSupervisionTestData;

public class AlarmSupervision extends TorTestCaseHelper implements TestCase {
	SupervisionOperator supervisionOperator = new SupervisionOperator();

	final int SUPERVISION_ON = Integer.parseInt(AlarmSupervisionTestData.getKeyValue("SUPERVISION_ON"));
	final int SUPERVISION_OFF = Integer.parseInt(AlarmSupervisionTestData.getKeyValue("SUPERVISION_OFF"));
	final String ALARM_SUPERVISION = AlarmSupervisionTestData.getKeyValue("ALARM_SUPERVISION");
	final String AUTOMATIC_SYNC =  AlarmSupervisionTestData.getKeyValue("AUTOMATIC_SYNC");
	final String HB_SUPERVISION =  AlarmSupervisionTestData.getKeyValue("HB_SUPERVISION");
	final String HB_TIMEOUT =  AlarmSupervisionTestData.getKeyValue("HB_TIMEOUT");
	final String IS_NODE_SUSPENDED =  AlarmSupervisionTestData.getKeyValue("IS_NODE_SUSPENDED");
	final String DELTA_SYNC_SUPPORTED =  AlarmSupervisionTestData.getKeyValue("DELTA_SYNC_SUPPORTED");
	final String SOURCE_SYNC_SUPPORTED = AlarmSupervisionTestData.getKeyValue("SOURCE_SYNC_SUPPORTED");
	final String SYNCH_ON_COMMIT_FAILURE_CLEAR = AlarmSupervisionTestData.getKeyValue("SYNCH_ON_COMMIT_FAILURE_CLEAR");
	final String ACKNOWLEDGE_SUPPORTED = AlarmSupervisionTestData.getKeyValue("ACKNOWLEDGE_SUPPORTED");
	final String CLOSED_SUPPORTED = AlarmSupervisionTestData.getKeyValue("CLOSED_SUPPORTED");
	final String COMMUNICATION_TIMEOUT = AlarmSupervisionTestData.getKeyValue("COMMUNICATION_TIMEOUT");
	final String SUBORDINATE_OBJECT_SYNCSUPPORTED = AlarmSupervisionTestData.getKeyValue("SUBORDINATE_OBJECT_SYNCSUPPORTED");
	

	/*@Test(dataProvider = "SupervisionData", dataProviderClass = AlarmSupervisionTestData.class)
	public void startsupervision(String testData, Host host) {
		assertEquals(supervisionOperator.toggleSupervision(testData,
				SUPERVISION_ON, ALARM_SUPERVISION, host),
				supervisionOperator.startexpected());
	}

	@Test(dataProvider = "SupervisionData", dataProviderClass = AlarmSupervisionTestData.class)
	public void autoSyncAttr(String testData, Host host) {
		assertEquals(supervisionOperator.toggleSupervision(testData,
				SUPERVISION_ON, AUTOMATIC_SYNC, host),
				supervisionOperator.startexpected());
	}

	@Test(dataProvider = "SupervisionData", dataProviderClass = AlarmSupervisionTestData.class)
	public void hbsupervisionAttr(String testData, Host host) {
		supervisionOperator.toggleSupervision(testData, SUPERVISION_ON,
				ALARM_SUPERVISION, host);
		assertEquals(supervisionOperator.getHB(testData, SUPERVISION_ON,
				HB_SUPERVISION), supervisionOperator.startexpected());
	}

	@Test(dataProvider = "SupervisionData", dataProviderClass = AlarmSupervisionTestData.class)
	public void hbTimeOutDPSAttr(String testData, Host host) {
		supervisionOperator.toggleSupervision(testData, SUPERVISION_ON,
				ALARM_SUPERVISION, host);
		assertEquals(Integer.parseInt(supervisionOperator.getHB(testData,
				SUPERVISION_ON, HB_TIMEOUT)), 300);
	}

	@Test(dataProvider = "SupervisionData", dataProviderClass = AlarmSupervisionTestData.class)
	public void nodeSuspendedAttr(final String testData, Host host) {
		assertEquals(supervisionOperator.toggleSupervision(testData,
				SUPERVISION_ON, IS_NODE_SUSPENDED, host),
				supervisionOperator.stopexpected());
	}

	@Test(dataProvider = "SupervisionData", dataProviderClass = AlarmSupervisionTestData.class)
	public void deltaSyncAttr(final String testData, Host host) {
		supervisionOperator.toggleSupervision(testData, SUPERVISION_ON,
				ALARM_SUPERVISION, host);
		assertEquals(supervisionOperator.getEANodeInfo(testData,
				SUPERVISION_ON, DELTA_SYNC_SUPPORTED),
				supervisionOperator.stopexpected());
	}

	@Test(dataProvider = "SupervisionData", dataProviderClass = AlarmSupervisionTestData.class)
	public void sourceSyncAttr(final String testData, Host host) {
		supervisionOperator.toggleSupervision(testData, SUPERVISION_ON,
				ALARM_SUPERVISION, host);
		assertEquals(supervisionOperator.getEANodeInfo(testData,
				SUPERVISION_ON, SOURCE_SYNC_SUPPORTED),
				supervisionOperator.stopexpected());
	}

	@Test(dataProvider = "SupervisionData", dataProviderClass = AlarmSupervisionTestData.class)
	public void syncOnCommitAttr(final String testData, Host host) {
		supervisionOperator.toggleSupervision(testData, SUPERVISION_ON,
				ALARM_SUPERVISION, host);
		assertEquals(supervisionOperator.getEANodeInfo(testData,
				SUPERVISION_ON, SYNCH_ON_COMMIT_FAILURE_CLEAR),
				supervisionOperator.stopexpected());
	}

	@Test(dataProvider = "SupervisionData", dataProviderClass = AlarmSupervisionTestData.class)
	public void ackSupportAttr(final String testData, Host host) {
		supervisionOperator.toggleSupervision(testData, SUPERVISION_ON,
				ALARM_SUPERVISION, host);
		assertEquals(
				supervisionOperator.getEANodeInfo(testData,
						SUPERVISION_ON,
						ACKNOWLEDGE_SUPPORTED),
				supervisionOperator.stopexpected());
	}

	@Test(dataProvider = "SupervisionData", dataProviderClass = AlarmSupervisionTestData.class)
	public void closedSupportAttr(final String testData, Host host) {
		supervisionOperator.toggleSupervision(testData, 1, ALARM_SUPERVISION,
				host);
		assertEquals(supervisionOperator.getEANodeInfo(testData,
				SUPERVISION_ON, CLOSED_SUPPORTED),
				supervisionOperator.stopexpected());
	}

	@Test(dataProvider = "SupervisionData", dataProviderClass = AlarmSupervisionTestData.class)
	public void commTimeoutAttr(final String testData, Host host) {
		supervisionOperator.toggleSupervision(testData, 1, ALARM_SUPERVISION,
				host);
		assertEquals(
				supervisionOperator.getEANodeInfo(testData,
						SUPERVISION_ON,
						COMMUNICATION_TIMEOUT), "300");
	}

	@Test(dataProvider = "SupervisionData", dataProviderClass = AlarmSupervisionTestData.class)
	public void subOrdinateAttr(final String testData, Host host) {
		supervisionOperator.toggleSupervision(testData, 1, ALARM_SUPERVISION,
				host);
		assertEquals(supervisionOperator.getEANodeInfo(testData,
				SUPERVISION_ON,
				SUBORDINATE_OBJECT_SYNCSUPPORTED),
				supervisionOperator.stopexpected());
	}

	@Test(dataProvider = "SupervisionData", dataProviderClass = AlarmSupervisionTestData.class,dependsOnMethods = { "startsupervision" })
	public void stopsupervision(final String testData, Host host) {
		assertEquals(supervisionOperator.toggleSupervision(testData,
				SUPERVISION_OFF, ALARM_SUPERVISION, host),
				supervisionOperator.stopexpected());
	}*/
}
