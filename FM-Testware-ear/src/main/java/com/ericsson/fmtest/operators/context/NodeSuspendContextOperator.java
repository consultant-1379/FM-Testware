package com.ericsson.fmtest.operators.context;

import java.sql.*;

import org.apache.log4j.Logger;

import com.ericsson.cifwk.taf.handlers.FMDatabaseHandler;
import com.ericsson.cifwk.taf.handlers.netsim.implementation.NetsimNE;
import com.ericsson.fmtest.getters.NodeSuspendOperatorAPIGetter;
import com.ericsson.fmtest.operators.NetsimOperator;

public class NodeSuspendContextOperator {

	private static final Logger LOGGER = Logger
			.getLogger(NodeSuspendContextOperator.class);

	NetsimOperator netsimOperator = new NetsimOperator();

	public String startAlarmBurst(NetsimNE node, String fdn, String activityName)
			throws Exception {
		String result = null;
		try {

			result = netsimOperator.startAlarmBurst(node.getSimulation(),
					node.getName(), activityName);
			Thread.sleep(10000);

		} catch (Exception e) {
			LOGGER.error(e.getStackTrace().toString());
		}
		return result;

	}

	public String getNodeSuspendDbResult(String neName) throws Exception {
		String result = "false";
		try {
			Connection con = FMDatabaseHandler.getFMADBConnection();
			Statement stmt;
			ResultSet rs;
			String statusCheckSQLQuery = "";
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			statusCheckSQLQuery = NodeSuspendOperatorAPIGetter.NODE_SUSPEND_QUERY
					+ neName + "%\'";
			LOGGER.debug("The Query is :" + statusCheckSQLQuery);
			rs = stmt.executeQuery(statusCheckSQLQuery);
			while (rs.next()) {
				result = rs.getString(NodeSuspendOperatorAPIGetter.RECORD_TYPE);
			}

			if (result.equals("21"))
				result = "true";

		} catch (Exception e) {
			LOGGER.error("Exception in executing the query : ", e);
			throw e;
		}

		LOGGER.debug("result is :" + result);
		return result;
	}

	public String stopAlarmBurst(NetsimNE node, String fdn, String activityName)
			throws Exception {
		String result = null;
		try {

			result = netsimOperator.stopAlarmBurst(node.getSimulation(),
					node.getName(), activityName);

		} catch (Exception e) {
			LOGGER.error(e.getStackTrace().toString());
		}
		return result;

	}

}
