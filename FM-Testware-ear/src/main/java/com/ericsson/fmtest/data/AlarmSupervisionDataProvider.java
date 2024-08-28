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
package com.ericsson.fmtest.data;

import java.util.*;

import com.ericsson.cifwk.taf.DataProvider;
import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.utils.csv.CsvReader;
import com.ericsson.fmtest.getters.DPSAPIGetter;

public class AlarmSupervisionDataProvider implements DataProvider {

	public static final String DATA_FILE = "SupervisionData.txt";

	public static String getKeyValue(String key) {
		return (DPSAPIGetter.dataMap.get(key));
	}

	public static List<String> getSupervisionTestData() {
		List<String> testDataList = new ArrayList<String>();
		CsvReader testItems = DataHandler.readCsv(DATA_FILE);
		List<String> fileDataList = testItems.getFileContents();
		Iterator<String> iterator = fileDataList.iterator();
		while (iterator.hasNext()) {
			String testData = iterator.next();
			if (testData.contains(";")) {
				String[] temp = testData.split(";");
				testDataList.add(temp[0]);
			}
		}
		return testDataList;
	}

	public static Host getOssHost() {
		return DataHandler.getHostByName("mars");
	}

}
