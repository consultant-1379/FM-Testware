package com.ericsson.fmtest.operators.context;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author tcsbosr Convert OSSFDN to TOR FDN
 */
public class FDNConverter {

	private static Map<String, String> conversion = new HashMap<>();
	private static Map<String, String> inveseConversion = new HashMap<>();
	static {
		conversion.put("SubNetwork", "TopologyRoot");
		conversion.put("NonRootSubNetwork", "ResourceGroup");
		conversion.put("MeContext", "MeContext");
	}
	static {
		inveseConversion.put("TopologyRoot", "SubNetwork");
		inveseConversion.put("ResourceGroup", "SubNetwork");
		inveseConversion.put("MeContext", "MeContext");
	}

	/**
	 * FDN converted from OSS to TOR
	 * 
	 * @param ossFdn
	 * @return String
	 */
	public static String convertOssFdnToTorFdn(final String ossFdn) {
		int i = 0;
		String result = "";
		final StringTokenizer ossToken = new StringTokenizer(ossFdn, ",");
		while (ossToken.hasMoreElements()) {
			final String[] fdnPart = ((String) ossToken.nextElement())
					.split("=");
			// second element could or not to exist
			if (i == 1) {
				result += "," + resolveSecondElement(result, fdnPart);
			} else {
				String convertedPart = conversion.get(fdnPart[0].trim());
				if (i != 0) {
					result += ",";
				}
				if (convertedPart != null) {
					result += convertedPart + "=" + fdnPart[1];
				} else {
					result += fdnPart[0].trim() + "=" + fdnPart[1];
				}
			}
			i++;
		}
		return result;
	}

	/**
	 * FDN converted from TOR to OSS
	 * 
	 * @param torFdn
	 * @return String
	 */
	public static String convertTorFdnToOssFdn(final String torFdn) {
		int i = 0;
		String result = "";
		final StringTokenizer torToken = new StringTokenizer(torFdn, ",");
		while (torToken.hasMoreElements()) {
			final String[] fdnPart = ((String) torToken.nextElement())
					.split("=");
			String convertedPart = inveseConversion.get(fdnPart[0].trim());
			if (i != 0) {
				result += ",";
			}
			if (convertedPart != null) {
				result += convertedPart + "=" + fdnPart[1];
			} else {
				result += fdnPart[0].trim() + "=" + fdnPart[1];
			}
			i++;
		}
		return result;
	}

	/**
	 * @return
	 */
	private static String resolveSecondElement(final String result,
			final String[] fdnPart) {
		String resolvedString = "";
		// if it exist we will rename it to Resource Group
		if ("SubNetwork".equalsIgnoreCase(fdnPart[0].trim())) {
			resolvedString = conversion.get("NonRootSubNetwork") + "="
					+ fdnPart[1];
		} else {
			// if it doesn't exist Resource group will be created and MeContext
			// will be assigned to that group
			resolvedString = conversion.get("NonRootSubNetwork")
					+ result.substring(result.indexOf('='))
					+ conversion.get(fdnPart[0].trim()) + "=" + fdnPart[1];
		}
		return resolvedString;
	}
}
