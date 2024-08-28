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
package com.ericsson.fmtest.test.data;

import java.util.*;

import org.testng.annotations.DataProvider;

import com.ericsson.cifwk.taf.TestData;
import com.ericsson.fmtest.data.AlarmSynchronizationDataProvider;

public class AlarmSynchronizationTestData implements TestData {

	@DataProvider(name = "AlarmSyncTestData")
	public static Object[][] getSyncTestData() {
		List<String> testData = new ArrayList<String>();
		testData = AlarmSynchronizationDataProvider.getSyncTestData();
		Object[][] result = new Object[testData.size()][];
		int i = 0;
		for (String str : testData) {
			Object[] arrayData = new Object[2];
			arrayData[0] = str;
			arrayData[1] = AlarmSynchronizationDataProvider.getOssHost();
			result[i] = arrayData;
			++i;
		}
		return result;
	}

}
