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

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.handlers.netsim.implementation.NetsimNE;
import com.ericsson.fmtest.data.HeartBeatDataProvider;

public class HeartBeatTestData {

	@DataProvider(name = "NetsimHBTestData")
	public static Object[][] getHBTestData() {
		List<String> testData = new ArrayList<String>();
		testData = HeartBeatDataProvider.getHBTestData();
		Object[][] result = new Object[testData.size()][];
		int i = 0;
		for (String str : testData) {
			String[] nodeData = str.split("#");
			NetsimNE node = new NetsimNE();
			node.setIp(nodeData[0]); 
			node.setName(nodeData[1]); 
			node.setNetsimHost(DataHandler.getHostByName("netsim"));
			node.setSimulation(nodeData[2]); 
			node.setType(nodeData[3]); 
			Object[] arrayData = new Object[2];
			arrayData[0] = node;
			arrayData[1] = nodeData[4]; 
			result[i] = arrayData;
			++i;
		}
		return result;

	}

	@DataProvider(name = "HBTestData")
	public static Object[][] getBasicHBData() {
		List<String> testData = new ArrayList<String>();
		testData = HeartBeatDataProvider.getHBTestData();
		Object[][] result = new Object[testData.size()][];
		int i = 0;
		for (String str : testData) {
			String[] nodeData = str.split("#");
			NetsimNE node = new NetsimNE();
			node.setIp(nodeData[0]);
			node.setName(nodeData[1]);
			node.setNetsimHost(DataHandler.getHostByName("netsim"));
			node.setSimulation(nodeData[2]);
			node.setType(nodeData[3]);
			Object[] arrayData = new Object[3];
			arrayData[0] = node;
			arrayData[1] = nodeData[4];
			arrayData[2] = HeartBeatDataProvider.getOssHost();
			result[i] = arrayData;
			++i;
		}

		return result;
	}
	

}
