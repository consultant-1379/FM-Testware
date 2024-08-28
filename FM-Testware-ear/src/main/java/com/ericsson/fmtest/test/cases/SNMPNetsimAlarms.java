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

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.ericsson.cifwk.taf.TestCase;
import com.ericsson.cifwk.taf.TorTestCaseHelper;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.fmtest.operators.SNMPNetsimOperator;
import com.ericsson.fmtest.test.data.NetsimAlarmsTestData;

public class SNMPNetsimAlarms extends TorTestCaseHelper implements TestCase {

	SNMPNetsimOperator netsimOperator = new SNMPNetsimOperator();

	@Test(dataProvider = "NetsimSNMPAlarmsTestData", dataProviderClass = NetsimAlarmsTestData.class)
	public void sendAlarmTest(String testData, Map<String, String> attributes, Host host) {

		List<String> sentAlarmList = netsimOperator.expectedAlarmList(testData,
				attributes, "1");
		List<String> dbAlarmList = netsimOperator.sendNetsimAlarms(testData,
				attributes, "1", host);
		// Verify if test case pass
		for (String inAlarm : sentAlarmList) {
			int index = dbAlarmList.indexOf(inAlarm);
			if (index != -1) {
				assertEquals(inAlarm, dbAlarmList.get(index));
			} else {
				assertEquals(inAlarm, null);
			}
		}

		// raise clear Alarm and verify
		List<String> sentClearAlarmList = netsimOperator.expectedAlarmList(
				testData, attributes, "6");
		List<String> dbClearAlarmList = netsimOperator.sendNetsimAlarms(
				testData, attributes, "6", host);
		for (String inAlarm : sentClearAlarmList) {
			int index = dbClearAlarmList.indexOf(inAlarm);
			if (index != -1) {
				assertEquals(inAlarm, dbClearAlarmList.get(index));
			} else {
				assertEquals(inAlarm, null);
			}
		}

	}

	//@Test(dataProvider = "NetsimAlarmsPCTestData", dataProviderClass = NetsimAlarmsTestData.class)
	public void sendAlarmPCTest(String testData, Map<String, String> attributes) {

		List<String> sentAlarmList = netsimOperator.expectedPCAlarmList(
				testData, attributes, "1");
		List<String> dbAlarmList = netsimOperator.sendNetsimAlarms_PC(testData,
				attributes, "1");
		// Verify if test case pass
		for (String inAlarm : sentAlarmList) {
			int index = dbAlarmList.indexOf(inAlarm);
			if (index != -1) {
				assertEquals(inAlarm, dbAlarmList.get(index));
			} else {
				assertEquals(inAlarm, null);
			}
		}

		// raise clear Alarm and verify
		List<String> sentClearAlarmList = netsimOperator.expectedPCAlarmList(
				testData, attributes, "6");
		List<String> dbClearAlarmList = netsimOperator.sendNetsimAlarms_PC(
				testData, attributes, "6");
		for (String inAlarm : sentClearAlarmList) {
			int index = dbClearAlarmList.indexOf(inAlarm);
			if (index != -1) {
				assertEquals(inAlarm, dbClearAlarmList.get(index));
			} else {
				assertEquals(inAlarm, null);
			}
		}

	}

	//@Test(dataProvider = "NetsimAlarmsData", dataProviderClass = NetsimAlarmsTestData.class)
	public void sendNetsimAlarm(String testData,
			Map<String, String> spAttributes, Map<String, String> pcAttributes,Host host) {
		List<String> sentAlarmList = netsimOperator.expectedAlarmList(testData,
				spAttributes, pcAttributes, "1");
		netsimOperator.startSupervision(testData,host);
		List<String> dbAlarmList = netsimOperator.sendNetsimAlarms(testData,
				spAttributes, pcAttributes, "1",host);
		// Verify if test case pass
		for (String inAlarm : sentAlarmList) {
			int index = dbAlarmList.indexOf(inAlarm);
			if (index != -1) {
				assertEquals(inAlarm, dbAlarmList.get(index));
			} else {
				assertEquals(inAlarm, null);
			}
		}

		// raise clear Alarm and verify
		List<String> sentClearAlarmList = netsimOperator.expectedAlarmList(
				testData, spAttributes,pcAttributes, "6");
		List<String> dbClearAlarmList = netsimOperator.sendNetsimAlarms(
				testData, spAttributes, pcAttributes, "6",host);
		for (String inAlarm : sentClearAlarmList) {
			int index = dbClearAlarmList.indexOf(inAlarm);
			if (index != -1) {
				assertEquals(inAlarm, dbClearAlarmList.get(index));
			} else {
				assertEquals(inAlarm, null);
			}
		}

	}

}
