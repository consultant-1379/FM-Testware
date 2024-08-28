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
import com.ericsson.fmtest.operators.AlarmOperator;
import com.ericsson.fmtest.test.data.FMAlarmsTestData;

public class FMAlarms extends TorTestCaseHelper implements TestCase {

	AlarmOperator operator = new AlarmOperator();
	
	@Test(dataProvider = "AlarmTestData", dataProviderClass = FMAlarmsTestData.class, groups = { "FMService" })
	public void alarmsTest(String testData) {
		assertEquals(operator.sendAlarm(testData).toString(), operator
				.alarmExpected(testData).toString());
	}
}
