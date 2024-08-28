package com.ericsson.fmtest.test.cases;


import org.testng.annotations.Test;

import com.ericsson.cifwk.taf.TestCase;
import com.ericsson.cifwk.taf.TorTestCaseHelper;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.handlers.netsim.implementation.NetsimNE;
import com.ericsson.fmtest.operators.NodeSuspendOperator;
import com.ericsson.fmtest.test.data.NodeSuspendTestData;

public class NodeSuspend  extends TorTestCaseHelper implements TestCase {
	
	NodeSuspendOperator nodeSuspendOperator = new NodeSuspendOperator();
	
	@Test(dataProvider = "NodeSuspendTestData", dataProviderClass = NodeSuspendTestData.class)
	public void nodeSuspendTest(NetsimNE node, String fdn,String activityName,Host host) {
		assertEquals(nodeSuspendOperator.nodeSuspend(node,fdn,activityName,host), nodeSuspendOperator.expectedResult());
	}

}
