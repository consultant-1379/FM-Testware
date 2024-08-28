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
import com.ericsson.fmtest.operators.NetsimOperator;
import com.ericsson.fmtest.operators.SupervisionOperator;
import com.ericsson.fmtest.test.data.HeartBeatTestData;

public class FMSupervision extends TorTestCaseHelper implements TestCase {

	NetsimOperator hbobj = new NetsimOperator();
	SupervisionOperator supervisionOperator = new SupervisionOperator();

	@Test(dataProvider = "HBTestData", dataProviderClass = HeartBeatTestData.class)
	public void supervision(NetsimNE node, String fdn, Host host) {

		// start supervision
		assertEquals(supervisionOperator.setAlarmSupervision(node.getName(),
				fdn, 1, host), supervisionOperator.alarmSupervisionONExpected());

		// raiseAlarm,verify
		assertEquals(
				hbobj.raiseDefaultAlarm(node.getSimulation(), node.getName(), node.getType()),
				hbobj.expectedAlarmResult(fdn));

		// raiseClearAlarm,verify
		assertEquals(
				hbobj.raiseDefaultClearAlarm(node.getSimulation(),
						node.getName(), node.getType()),
				hbobj.expectedAlarmResult(fdn));

		// raise HB alarm stop Node
		assertEquals(hbobj.raiseHBAlarm(node),
				hbobj.expectedHBAlarmResult(fdn, 0));

		// HB clear alarm start Node
		assertEquals(hbobj.raiseHBClearAlarm(node),
				hbobj.expectedHBAlarmResult(fdn, 1));

		// raiseAlarm,verify
		assertEquals(
				hbobj.raiseDefaultAlarm(node.getSimulation(), node.getName(), node.getType()),
				hbobj.expectedAlarmResult(fdn));

		// raiseClearAlarm,verify
		assertEquals(
				hbobj.raiseDefaultClearAlarm(node.getSimulation(),
						node.getName(), node.getType()),
				hbobj.expectedAlarmResult(fdn));

		// stopSupervision
		assertEquals(supervisionOperator.setAlarmSupervision(node.getName(),
				fdn, 0, host),
				supervisionOperator.alarmSupervisionOFFExpected());

	}
}
