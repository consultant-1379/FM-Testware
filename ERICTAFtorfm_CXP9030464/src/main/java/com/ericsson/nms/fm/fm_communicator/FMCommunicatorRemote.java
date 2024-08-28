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
package com.ericsson.nms.fm.fm_communicator;

import java.rmi.Remote;
import java.rmi.RemoteException;



/**
 * @author tcsjapa
 *
 */
public interface FMCommunicatorRemote extends Remote{

	 /**
	 * @param obj
	 * @return
	 * @throws RemoteException
	 */
	int sendNotification(Notification obj) throws RemoteException;
}
