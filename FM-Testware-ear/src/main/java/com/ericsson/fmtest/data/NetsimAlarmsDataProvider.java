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
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.ericsson.cifwk.taf.DataProvider;
import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.utils.csv.CsvReader;

public class NetsimAlarmsDataProvider implements DataProvider {

	public static final String DATA_FILE = "NetsimSnmpAlarmData.txt";
	private static final Logger LOGGER = Logger
			.getLogger(NetsimAlarmsDataProvider.class);

	public static List<String> getNetsimData() {
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

	/**
	 * @method provideAttributeData
	 * @description Method to read all the attributes files containing
	 *              information like Specific Problem,Event type,PRA,Probable
	 *              Cause.
	 * @param the
	 *            sourceType representing the filename
	 * @return an Object of AlarmAttributes.
	 */
	public static AlarmAttributes provideAttributeData(String sourceType)
			throws Exception {
		String datafile = sourceType + ".attributes";
		AlarmAttributes alarmAttr = new AlarmAttributes();
		alarmAttr.setAAU(sourceType);
		CsvReader testItems = DataHandler.readCsv(datafile);
		List<String> fileDataList = testItems.getFileContents();
		Iterator<String> iterator = fileDataList.iterator();
		while (iterator.hasNext()) {
			String line = iterator.next();
			if (line != null && line.length() > 0) {
				String[] temp = line.split("=");
				if (temp.length > 1 && temp[0] != null && temp[1] != null) {
					alarmAttr.setAttributesMap(temp[0], temp[1]);
				}

			}
		}
		LOGGER.debug("Built Alarmatrributes Map ::" + alarmAttr.toString());
		return alarmAttr;

	}

	/**
	 * @method providePCData
	 * @description Method that reads attributes files and returns Specific
	 *              Problem values
	 * 
	 * @param attributes
	 *            a map
	 * 
	 * @return a map with the Specific problem data.
	 */
	public static Map<String, String> providePCData(AlarmAttributes attributes) {
		Map<String, String> pcDataMap = new HashMap<String, String>();
		Map<String, String> attributesMap = attributes.getAttributesMap();
		Iterator<Entry<String, String>> iterator = attributesMap.entrySet()
				.iterator();
		int i = 0;
		while (iterator.hasNext() && i < 5) {
			Map.Entry pairs = (Map.Entry) iterator.next();
			String key = (String) pairs.getKey();
			String value = (String) pairs.getValue();
			if (key.contains("ProbableCause")) {
				pcDataMap.put(key, value);
				++i;
			}
		}
		LOGGER.debug("Probable Cause attributes read are "
				+ pcDataMap.toString());
		return pcDataMap;
	}

	/**
	 * @method provideSPData
	 * @description Method that reads attributes files and returns Specific
	 *              Problem values
	 * 
	 * @param attributes
	 *            a map
	 * 
	 * @return a map with the Specific problem data.
	 */
	public static Map<String, String> provideSPData(
			Map<String, String> attributes) {
		Map<String, String> spDataMap = new HashMap<String, String>();
		Iterator<Entry<String, String>> iterator = attributes.entrySet()
				.iterator();
		int i = 0;
		while (iterator.hasNext() && i < 5) {
			Map.Entry pairs = (Map.Entry) iterator.next();
			String key = (String) pairs.getKey();
			String value = (String) pairs.getValue();
			if (key.contains("SpecificProblem")) {
				spDataMap.put(key, value);
				++i;
			}

		}
		return spDataMap;
	}
	
	public static Host getOssHost() {		
		return DataHandler.getHostByName("mars");
	}

}
