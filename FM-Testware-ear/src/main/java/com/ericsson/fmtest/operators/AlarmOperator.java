package com.ericsson.fmtest.operators;

import org.apache.log4j.Logger;

import com.ericsson.cifwk.taf.GenericOperator;
import com.ericsson.fmtest.operators.context.AlarmAPIOperator;
import com.ericsson.oss.fmservice.api.beans.Alarm;

public class AlarmOperator implements GenericOperator {

	private static final Logger LOGGER = Logger.getLogger(AlarmOperator.class);

	public Alarm sendAlarm(final String xmlpath) {
		Alarm retAlarm = null;
		try {
			retAlarm = new AlarmAPIOperator().sendAlarm(xmlpath);
		} catch (Exception e) {
			LOGGER.error(e.getStackTrace().toString());
		}
		return retAlarm;
	}

	public Alarm alarmExpected(String xmlpath) {
		Alarm retAlarm = null;
		try {
			retAlarm = new AlarmBuilder().buildAlarm(xmlpath);
		} catch (Exception e) {
			LOGGER.error(e.getStackTrace().toString());
		}
		return retAlarm;
	}
}