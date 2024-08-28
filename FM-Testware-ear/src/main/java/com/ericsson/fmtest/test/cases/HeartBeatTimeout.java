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
import com.ericsson.fmtest.operators.SupervisionOperator;
import com.ericsson.fmtest.test.data.AlarmSupervisionTestData;

/**
 * @author tcsbosr Class for Testing update HB TimeOut in DPS
 */
public class HeartBeatTimeout extends TorTestCaseHelper implements TestCase {
	SupervisionOperator supervisionOperator = new SupervisionOperator();

	@Test(dataProvider = "SupervisionData", dataProviderClass = AlarmSupervisionTestData.class)
	public void hbTimeoutAttr(String testData,Host host) {/*
		supervisionOperator.changeHBTimeout(testData, 1, "heartbeatTimeout", 300,host);
		assertEquals(Integer.parseInt(supervisionOperator.getHBTimeout(testData, 1,
				"heartBeatTimeout")), 300);
	*/}

}
