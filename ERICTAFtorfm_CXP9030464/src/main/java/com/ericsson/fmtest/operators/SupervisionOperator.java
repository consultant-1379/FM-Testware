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

import org.apache.log4j.Logger;

import com.ericsson.cifwk.taf.GenericOperator;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.fmtest.operators.context.SupervisionAPIOperator;

public class SupervisionOperator implements GenericOperator {

	SupervisionAPIOperator operator = new SupervisionAPIOperator();
	private static final Logger LOGGER = Logger
			.getLogger(SupervisionOperator.class);

	/*public String toggleSupervision(final String fdn, final int state,
			final String attribute,Host host) {

		String retString = null;
		try {
			retString = operator.toggleSupervision(fdn, state, attribute,host);
		} catch (Exception e) {
			LOGGER.error(e.getStackTrace().toString());
		}
		return retString;
	}
*/
	public String setAlarmSupervision(final String neName, final String fdn,
			final int state,Host host) {

		String retString = null;
		try {
			retString = operator.setAlarmSupervision(neName, fdn, state,host);
		} catch (Exception e) {
			LOGGER.error(e.getStackTrace().toString());
		}
		return retString;
	}

	/*public String changeHBTimeout(final String fdn, final int state,
			final String attribute, final int hbtimeout,Host host) {

		String retString = null;
		try {
			retString = operator.changeHBTimeout(fdn, state, attribute,
					hbtimeout,host);
		} catch (Exception e) {
			LOGGER.error(e.getStackTrace().toString());
		}
		return retString;
	}

	public String getHB(final String testData, final int state,
			final String attribute) {
		String retString = null;
		try {
			retString = operator.getHB(testData, state, attribute);
		} catch (Exception e) {
			LOGGER.error(e.getStackTrace().toString());
		}
		return retString;
	}

	public String getEANodeInfo(final String testData, final int state,
			final String attribute) {

		String retString = null;
		try {
			retString = operator.getEANodeInfo(testData, state, attribute);
		} catch (Exception e) {
			LOGGER.error(e.getStackTrace().toString());
		}
		return retString;
	}

	public String getHBTimeout(final String testData, final int state,
			final String attribute) {

		String retString = null;
		try {
			retString = operator.getHBTimeout(testData, state, attribute);
		} catch (Exception e) {
			LOGGER.error(e.getStackTrace().toString());
		}
		return retString;
	} */

	public String stopexpected() {
		// For Stop Supervision alarmSupervision is false.
		return "false";
	}

	public String startexpected() {
		return "true";
	}

	public String alarmSupervisionONExpected() {
		return "1";
	}

	public String alarmSupervisionOFFExpected() {
		return "0";
	}

}
