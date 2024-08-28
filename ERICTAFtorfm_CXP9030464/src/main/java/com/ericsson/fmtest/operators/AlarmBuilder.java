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
package com.ericsson.fmtest.operators;

import java.io.File;

import javax.xml.bind.*;

import com.ericsson.oss.fmservice.api.beans.Alarm;

public class AlarmBuilder {

	JAXBContext jaxbContext;
	Unmarshaller jaxbUnmarshaller;

	/**
	 * @description Method to parse a XML to form an object of type Alarm
	 * 
	 * @param xmlpath
	 *            - the path of xml as a string
	 * @return an object of type Alarm
	 */
	public Alarm buildAlarm(String xmlpath) throws Exception {
		Alarm retAlarm = null;
		try {
			File file = new File(xmlpath);
			jaxbContext = JAXBContext.newInstance(Alarm.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			retAlarm = (Alarm) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			throw e;
		}
		return retAlarm;
	}
}
