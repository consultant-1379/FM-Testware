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
package com.ericsson.fmtest.operators.context;

import org.testng.log4testng.Logger;

import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.handlers.implementation.SshRemoteCommandExecutor;
import com.ericsson.fmtest.getters.SupervisionAPIOperatorAPIGetter;

public class ActionSender {

	javax.naming.Context context = null;
	//DataPersistenceManager bean;
	SshRemoteCommandExecutor sshHandler = null;
	public static final String NW_ID = "nwId";
	public static final String SN_ID = "snId";

	private static final Logger LOGGER = Logger
			.getLogger(ActionSender.class);

	/**
	 * @method setAlarmSupervision
	 * @description Method to change AlarmSupervision value for a node in OSS.
	 * @param fdn
	 *            - as a String,FDN of the node
	 * @Param state -as int,0 - OFF ,1 - ON
	 * 
	 */

	public void setAlarmSupervision(String fdn, int state,Host host) throws Exception {
		sshHandler = getSshHandler(host);
		String imcmd = SupervisionAPIOperatorAPIGetter.IMCMD;
		imcmd = imcmd.concat(fdn);
		imcmd = imcmd.concat(SupervisionAPIOperatorAPIGetter.REPLACE_ALARM_SUPERVISION);
		imcmd = imcmd.concat(Integer.toString(state));
		sshHandler.simplExec(imcmd);
	}

	/**
	 * @method generateRequest
	 * @description Method to change HeartBeatTimeout value for a node in OSS.
	 * @param fdn
	 *            - as a String,FDN of the node
	 * @Param state -as int,0 - OFF ,1 - ON
	 * 
	 */
	public void setAlarmSupervison(String testData, int state, int hbtimeout,Host host)
			throws Exception {
		sshHandler = getSshHandler(host);
		String imcmd = SupervisionAPIOperatorAPIGetter.IMCMD;
		imcmd = imcmd.concat(testData);
		imcmd = imcmd.concat(SupervisionAPIOperatorAPIGetter.REPLACE_HB_TIMEOUT);
		imcmd = imcmd.concat(Integer.toString(state));
		sshHandler.simplExec(imcmd);
		setAlarmSupervision(testData, state,host);
	}

	/**
	 * @method generateSyncRequest
	 * @description Method to initiate Alarm Sync on a node in OSS.
	 * @param fdn
	 *            - as a String,FDN of the node
	 * @Param state -as int,0 - OFF ,1 - ON
	 * 
	 */
	public void generateSyncRequest(String fdn,Host host) throws Exception {
		sshHandler = getSshHandler(host);
		String command = SupervisionAPIOperatorAPIGetter.FMA_SYNC;
		command = command.concat(fdn).concat(" -o");
		sshHandler.simplExec(command);
	}
	
	/**
	 * @method alarmAck
	 * @description Method to Ack an alarm of a node
	 * @fdn FDN of the node
	 */	
	public void alarmAck(String fdn,Host host) throws Exception {
		sshHandler = getSshHandler(host);
		String ackCommand = SupervisionAPIOperatorAPIGetter.FMACK;
		ackCommand = ackCommand.concat(fdn);
		ackCommand = ackCommand.concat(" all");
		sshHandler.simplExec(ackCommand);
	}
	
	/**
	 * @method alarmUnAck
	 * @description Method to Unack an alarm of a  node
	 * @fdn FDN of the node
	 */
	public void alarmUnAck(String fdn,Host host) throws Exception {
		sshHandler = getSshHandler(host);
		String ackCommand = SupervisionAPIOperatorAPIGetter.FMUNACK;
		ackCommand = ackCommand.concat(fdn);
		ackCommand = ackCommand.concat(" all");
		sshHandler.simplExec(ackCommand);
	}
	

	/**
	 * @method getSshHandler
	 * @description Method to get a reference/handler to a server using SSH
	 *              connection
	 * @return SshRemoteCommandExecutor set to the Required Host.
	 */
	private SshRemoteCommandExecutor getSshHandler(Host host) throws Exception {
		sshHandler = new SshRemoteCommandExecutor(host);
		return sshHandler;
	}

	/*public void createMOs(String testData) throws Exception {
		bean = lookup();
		String torFdn = FDNConverter.convertOssFdnToTorFdn(testData);
		String[] splitStr = torFdn.split(",");
		ManagedObject parent = null;
		ManagedObject rg = null;
		ManagedObject mec = null;

		if (splitStr.length != 3) {
			LOGGER.error("Wrong Test FDN");
			return;
		}
		String[] parts = splitStr[0].split("=");
		parent = bean.findMO(splitStr[0]);
		if (parent == null) {
			parent = bean.createMO(parts[1], "NW", parent, null);
		}
		parts = splitStr[1].split("=");
		rg = bean.findMO(splitStr[0] + "," + splitStr[1]);
		if (rg == null) {
			rg = bean.createMO(parts[1], "SN", parent, null);
		}
		parts = splitStr[2].split("=");
		mec = bean.findMO(splitStr[0] + "," + splitStr[1] + "," + splitStr[2]);
		if (mec == null) {
			mec = bean.createMO(parts[1], "MeC", rg, null);
		}
		if (parent.getEntityAddressInfo() == null) {
			EntityAddressInfo eaInfo = (EntityAddressInfo) bean.createPO(
					"EntityAddressInfo", null);
			parent.setEntityAddressInfo(eaInfo);
			bean.persist(eaInfo);
		}
	}

	*//**
	 * @method getAlarmSupervisionDPSResult
	 * @description Method to retrieve a value for a given attribute from the
	 *              AlarmSupervision PO of DPS
	 * @param fdn
	 *            - FDN of the node as String
	 * @attribute - Attribute to be retrieved
	 * @return result as a String
	 */
	/*public String getAlarmSupervisionDPSResult(String fdn, String attribute)
			throws Exception {
		String result = null;
		String torFdn = FDNConverter.convertOssFdnToTorFdn(fdn);

		bean = lookup();
		ManagedObject managedObject = bean.findMO(torFdn);

		if (managedObject != null) {
			AlarmSupervision alarmSupervision = null;
			Collection<PersistentObject> alarmAssociations = managedObject
					.getAssociation(DPSAPIGetter.ALARM_SUPERVISION_TYPE);

			for (PersistentObject alSupervision : alarmAssociations) {
				if (alSupervision.getType().equals(
						DPSAPIGetter.ALARM_SUPERVISION_TYPE)) {
					alarmSupervision = (AlarmSupervision) alSupervision;
					result = alarmSupervision.getAttribute(attribute)
							.toString();
					break;
				}
			}
		} else {
			LOGGER.error("Could not find MO");
		}
		return result;
	}*/

	/**
	 * @method getHeartBeatSupervisionDPSResult
	 * @description Method to retrieve a value for a given attribute from the
	 *              HeartBeatSupervision PO of DPS
	 * @param fdn
	 *            - FDN of the node as String
	 * @attribute - Attribute to be retrieved
	 * @return result as a String
	 *//*
	public String getHeartBeatSupervisionDPSResult(String fdn, String attribute)
			throws Exception {

		String result = null;
		String torFdn = FDNConverter.convertOssFdnToTorFdn(fdn);

		bean = lookup();
		ManagedObject managedObject = bean.findMO(torFdn);
		if (managedObject != null) {
			HeartbeatSupervision heartbeatSupervision = null;
			Collection<PersistentObject> hbAssociations = managedObject
					.getAssociation(DPSAPIGetter.HEARTBEAT_SUPERVISION_TYPE);
			for (PersistentObject hbSupervision : hbAssociations) {
				if (hbSupervision.getType().equals(
						DPSAPIGetter.HEARTBEAT_SUPERVISION_TYPE)) {
					heartbeatSupervision = (HeartbeatSupervision) hbSupervision;
					result = heartbeatSupervision.getAttribute(attribute)
							.toString();
					break;
				}
			}
		} else {
			LOGGER.error("Could not find MO");
		}
		return result;
	}

	*//**
	 * @method getEANodeDPSResult
	 * @description Method to retrieve a value for a given attribute from the
	 *              EANodeInfo PO of DPS
	 * @param fdn
	 *            - FDN of the node as String
	 * @attribute - Attribute to be retrieved
	 * @return result as a String
	 *//*
	public String getEANodeDPSResult(String testData, String attribute)
			throws Exception {
		
		String result = null;

		String torFdn = FDNConverter.convertOssFdnToTorFdn(testData);
		ManagedObject managedObject = bean.findMO(torFdn);
		EntityAddressInfo eaInfo = (EntityAddressInfo) managedObject
				.getEntityAddressInfo();

		if (eaInfo != null) {
			NodeInfo nodeInfo = null;
			Collection<PersistentObject> niAssociations = eaInfo
					.getAssociation(DPSAPIGetter.PROTOCOL_INFO_ASSOCIATION_NAME);
			for (PersistentObject nInfo : niAssociations) {
				if (nInfo.getType().equals(DPSAPIGetter.NODE_INFO_TYPE)) {
					nodeInfo = (NodeInfo) nInfo;
					result = (String) nodeInfo.getAttribute(attribute)
							.toString();
					break;
				}
			}
		} else {
			LOGGER.error("Method getEANodeDPSResult: Could not find MO from FDNConversion");
		}
		return result;
	}

	*//**
	 * @method lookup
	 * @description - Method to lookup for the DPS bean
	 * @return a reference to the bean.
	 *//*
	private DataPersistenceManager lookup() throws Exception {
		String DPS_TOR_IP = null;
		String DPS_JNDI_PORT = null;
		DPS_TOR_IP = (String) DataHandler.getAttribute("DPS_TOR_IP");
		DPS_JNDI_PORT = (String) DataHandler.getAttribute("DPS_JNDI_PORT");

		final Properties properties = new Properties();
		properties.put(Context.URL_PKG_PREFIXES,
				AlarmAPIOperatorAPIGetter.NAMING);
		properties.put(AlarmAPIOperatorAPIGetter.SSL_ENABLED, "false");
		properties.put(AlarmAPIOperatorAPIGetter.CONNECTIONS, "default");

		properties.put(AlarmAPIOperatorAPIGetter.HOST, DPS_TOR_IP);
		properties.put(AlarmAPIOperatorAPIGetter.PORT, DPS_JNDI_PORT);
		properties.put(AlarmAPIOperatorAPIGetter.SASL_POLICY, "false");
		properties.put(AlarmAPIOperatorAPIGetter.USERNAME, "guest");
		properties.put(AlarmAPIOperatorAPIGetter.PASSWORD, "guestp");
		properties.put(Context.SECURITY_PRINCIPAL, "guest");
		properties.put(Context.SECURITY_CREDENTIALS, "guestp");

		final EJBClientConfiguration clientConfiguration = new PropertiesBasedEJBClientConfiguration(
				properties);
		final ContextSelector<EJBClientContext> contextSelector = new ConfigBasedEJBClientContextSelector(
				clientConfiguration);

		// set the selector for use
		EJBClientContext.setSelector(contextSelector);

		// 1. Obtaining Context
		context = new InitialContext(properties);
		bean = (DataPersistenceManager) context
				.lookup(SupervisionAPIOperatorAPIGetter.DPSLookUpName);

		return bean;
	}
*/
}
