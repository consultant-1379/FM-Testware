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
package com.ericsson.cifwk.taf.handlers;

import java.sql.*;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.cifwk.taf.Handler;
import com.ericsson.cifwk.taf.data.DataHandler;

public class FMDatabaseHandler implements Handler {
	
	@SuppressWarnings("unchecked")
	private static Map<Object,Object> attributes = DataHandler.getAttributes();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FMDatabaseHandler.class);
	
	
	public static Connection getDBConnection(String dBName) throws Exception {
		Connection connection = null;
		try {
			String OSS_IP = (String) attributes.get("OSS_IP");
			String FM_PORT_NO = (String) attributes.get("FM_PORT_NO");
			String DBName = (String) attributes.get(dBName);
			String USERNAME = (String) attributes.get("USERNAME");
			String PASSWORD = (String) attributes.get("PASSWORD");
			String DRIVER = (String) attributes.get("DRIVER");

			String url = "jdbc:sybase:Tds:" + OSS_IP + ":" + FM_PORT_NO + "/"
					+ DBName;

			// Open the connection.
			DriverManager.registerDriver((Driver) Class.forName(DRIVER)
					.newInstance());

			connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
		} catch (Exception ex) {
			LOGGER.error(ex.getStackTrace().toString());
			throw ex;
		}
		return connection;
	}
	
	
	/**
	 * Method to get a connection to the  Alarm Database in OSS.
	 * 
	 * @return Connection object.
	 * @throws Exception
	 */
	public static Connection getFMADBConnection() throws Exception {
		return getDBConnection("FMADBName");
	}

	/**
	 * Method to get a connection to the Model(Node) Database in OSS.
	 * 
	 * @return Connection object.
	 * @throws Exception
	 */
	public static Connection getIMHDBConnection() throws Exception {
			return getDBConnection("IMHDBName");
	}

}
