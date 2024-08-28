package com.ericsson.fmtest.test.data;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.handlers.netsim.implementation.NetsimNE;
import com.ericsson.fmtest.data.NodeSuspendDataProvider;

public class NodeSuspendTestData {

	@DataProvider(name = "NodeSuspendTestData")
	public static Object[][] getNodeSuspendTestData() {
		List<String> testData = new ArrayList<String>();
		testData = NodeSuspendDataProvider.getNodeSuspendTestData();
		Object[][] result = new Object[testData.size()][];
		int i = 0;
		for (String str : testData) {
			String[] nodeData = str.split("#");
			NetsimNE node = new NetsimNE();
			node.setName(nodeData[1]); 
			node.setNetsimHost(DataHandler.getHostByName("netsim"));
			node.setSimulation(nodeData[0]); 
			Object[] arrayData = new Object[4];
			arrayData[0] = node;
			arrayData[1] = nodeData[3];
			arrayData[2] = nodeData[2];
			arrayData[3] = DataHandler.getHostByName("mars");
			result[i] = arrayData;
			++i;
		}
		return result;

	}
	
}
