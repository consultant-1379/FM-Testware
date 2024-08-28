package com.ericsson.fmtest.data;


import java.util.*;

import com.ericsson.cifwk.taf.DataProvider;
import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.utils.csv.CsvReader;

public class FMAlarmsDataProvider implements DataProvider {
	
	public static final String DATA_FILE = "AlarmsTest.txt";

	public static List<String> getalarmTestData() {
		List<String> testDataList = new ArrayList<String>();
		CsvReader testItems = DataHandler.readCsv(DATA_FILE);
		List<String> fileDataList = testItems.getFileContents();
		Iterator<String> iterator = fileDataList.iterator();
		while (iterator.hasNext()) {
			String testData = iterator.next();
			if (testData.contains(";")){
				String[] temp = testData.split(";");
				testDataList.add(temp[0]);
			}
		}
		return testDataList;
	}
}
