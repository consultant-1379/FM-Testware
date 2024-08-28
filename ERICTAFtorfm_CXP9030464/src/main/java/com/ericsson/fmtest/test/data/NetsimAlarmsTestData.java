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

import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.fmtest.data.*;

public class NetsimAlarmsTestData {

	@DataProvider(name = "NetsimTestData")
	public static Object[][] getalarmTestData() {
		List<String> testData = new ArrayList<String>();
		testData = NetsimAlarmsDataProvider.getNetsimData();
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

	@DataProvider(name = "NetsimAlarmsTestData")
	public static Object[][] getTestData() {

		List<String> testData = new ArrayList<String>();
		testData = NetsimAlarmsDataProvider.getNetsimData();
		Object[][] result = new Object[testData.size()][];
		int i = 0;
		try {
			for (String str : testData) {
				String[] dataSplit = str.split("#");
				AlarmAttributes attr = NetsimAlarmsDataProvider
						.provideAttributeData(dataSplit[0]);
				Map spAttributesMap = NetsimAlarmsDataProvider
						.provideSPData(attr.getAttributesMap());
				Object[] arrayData = new Object[2];
				arrayData[0] = str;
				arrayData[1] = spAttributesMap;
				result[i] = arrayData;
				++i;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@DataProvider(name = "NetsimSNMPAlarmsTestData")
	public static Object[][] getSNMPTestData() {

		List<String> testData = new ArrayList<String>();
		testData = NetsimAlarmsDataProvider.getNetsimData();
		Object[][] result = new Object[testData.size()][];
		int i = 0;
		try {
			for (String str : testData) {
				String[] dataSplit = str.split("#");
				AlarmAttributes attr = NetsimAlarmsDataProvider
						.provideAttributeData(dataSplit[0]);
				Map spAttributesMap = NetsimAlarmsDataProvider
						.provideSPData(attr.getAttributesMap());
				Object[] arrayData = new Object[3];
				arrayData[0] = str;
				arrayData[1] = spAttributesMap;
				arrayData[2] = NetsimAlarmsDataProvider.getOssHost();
				result[i] = arrayData;
				++i;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@DataProvider(name = "NetsimAlarmsPCTestData")
	public static Object[][] getPCTestData() {

		List<String> testData = new ArrayList<String>();
		testData = NetsimAlarmsDataProvider.getNetsimData();
		Object[][] result = new Object[testData.size()][];
		int i = 0;
		try {
			for (String str : testData) {
				String[] dataSplit = str.split("#");
				AlarmAttributes attr = NetsimAlarmsDataProvider
						.provideAttributeData(dataSplit[0]);
				Map<String,String> pcAttributesMap = NetsimAlarmsDataProvider
						.providePCData(attr);
				Object[] arrayData = new Object[2];
				arrayData[0] = str;
				arrayData[1] = pcAttributesMap;
				result[i] = arrayData;
				++i;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@DataProvider(name = "NetsimAlarmsData")
	public static Object[][] getAlarmsTestData(){
		
		List<String> testData = new ArrayList<String>();
		testData = NetsimAlarmsDataProvider.getNetsimData();
		Object[][] result = new Object[testData.size()][];
		int i=0;
		try{
			for(String str : testData){
				String[] dataSplit = str.split("#");
				AlarmAttributes attr =  NetsimAlarmsDataProvider
						.provideAttributeData(dataSplit[0]);
				Map<String,String> spAttributesMap =  NetsimAlarmsDataProvider
						.provideSPData(attr.getAttributesMap());
				Map<String,String> pcAttributesMap = NetsimAlarmsDataProvider
						.providePCData(attr);
				Object[] arrayData = new Object[4];
				arrayData[0] = str;
				arrayData[1] = spAttributesMap;
				arrayData[2] = pcAttributesMap;
				arrayData[3] = DataHandler.getHostByName("OSSServer");
				result[i] = arrayData;
				++i;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	
}
