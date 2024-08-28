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
package com.ericsson.fmtest.getters;

import com.ericsson.cifwk.taf.ApiGetter;

public class SupervisionAPIOperatorAPIGetter implements ApiGetter {
	
	public static final  String DPSLookUpName = "ejb:DataPersistence/DataPersistence-ejb/DataPersistenceManagerImpl!com.ericsson.oss.itpf.datalayer.datapersistence.api.DataPersistenceManager";
	public static final String IMCMD = "/opt/ericsson/bin/imcmd -select FM_supi ";
	public static final String FMA_SYNC = "/opt/ericsson/bin/fma_sync -system `domainame` -objectname ";
	public static final String ALLIST = "/opt/ericsson/bin/allist -ts `domainname` -d ";
	public static final String FMACK = "/opt/ericsson/bin/fmack -oor ";
	public static final String FMUNACK = "/opt/ericsson/bin/fmack -u -oor ";
	public static final String REPLACE_ALARM_SUPERVISION = " -replace Alarm_supervision ";
	public static final String REPLACE_HB_TIMEOUT=" -replace Heart_beat_timeout ";
	
	public static final String ALARM_SUPERVISION_QUERY = "select Alarm_supervision from IM_alarm_state where Id_number IN (Select Id_number from IM_top where Object_name like \'%";
	public static final String ALARM_SUPERVISION = "Alarm_supervision";
}

