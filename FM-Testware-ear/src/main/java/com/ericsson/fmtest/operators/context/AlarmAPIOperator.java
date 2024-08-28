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

import com.ericsson.cifwk.taf.ApiOperator;
import com.ericsson.fmtest.operators.AlarmBuilder;
import com.ericsson.oss.fmservice.api.beans.Alarm;

public class AlarmAPIOperator implements ApiOperator {

	public Alarm sendAlarm(final String xmlpath) throws Exception {
		return new AlarmSender().sendModelEvent(new AlarmBuilder()
				.buildAlarm(xmlpath));
	}
}
