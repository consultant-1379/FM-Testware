package com.ericsson.fmtest.test.data;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.ericsson.fmtest.data.FMAlarmsDataProvider;

public class FMAlarmsTestData {

	@DataProvider(name = "AlarmTestData")
	public static Object[][] getalarmTestData() {
		List<String> testData = new ArrayList<String>();
		testData = FMAlarmsDataProvider.getalarmTestData();
		Object[][] result = new Object[testData.size()][];
		int i = 0;
		for (String str : testData) {
			Object[] arrayData = new Object[1];
			arrayData[0] = str;
			result[i] = arrayData;
			++i;
		}
		return result;
	}
}
