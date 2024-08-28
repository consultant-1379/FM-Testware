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
package com.ericsson.fmtest.data;

import java.util.*;

public class AlarmAttributes {

	private String AAU;
	private Map<String,String> attributesMap = new HashMap<String,String> ();
	
	public void setAttributesMap(String key,String value){
		this.attributesMap.put(key, value);
	}
	
	public Map<String,String> getAttributesMap(){
		return attributesMap;
	}



	/**
	 * @return the aAU
	 */
	public String getAAU() {
		return AAU;
	}

	/**
	 * @param aAU
	 *            the aAU to set
	 */
	public void setAAU(String aAU) {
		AAU = aAU;
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AlarmAttributes [AAU=" + AAU + ", attributes="
				+ attributesMap.toString() + "]";
	}

}
