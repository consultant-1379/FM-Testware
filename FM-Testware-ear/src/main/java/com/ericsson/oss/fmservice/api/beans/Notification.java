/**
 * @author evinjay
 *
 */

package com.ericsson.oss.fmservice.api.beans;
import java.io.Serializable;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="Notification")
public class Notification implements Serializable {

	private String notificationType;	
	private String FDN;
	private String eventTime;
	private String specificproblem;
	private String probableCause;
	private String severity;
	private String EventType;
	private long numId;
	private String stringId;
	private String sourceType;
	private String topMO;
	/**
	 * @return the notificationType
	 */
	@XmlElement(name="notificationtype")
	public String getNotificationType() {
		return notificationType;
	}
	/**
	 * @param notificationType the notificationType to set
	 */
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}
	/**
	 * @return the fDN
	 */
	@XmlElement(name="FDN")
	public String getFDN() {
		return FDN;
	}
	/**
	 * @param fDN the fDN to set
	 */
	public void setFDN(String fDN) {
		FDN = fDN;
	}
	/**
	 * @return the eventTime
	 */
	@XmlElement(name="eventtime")
	public String getEventTime() {
		return eventTime;
	}
	/**
	 * @param eventTime the eventTime to set
	 */
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	/**
	 * @return the sPECIFICPROBLEM
	 */
	
	/**
	 * @return the probableCause
	 */
	@XmlElement(name="probablecause")
	public String getProbableCause() {
		return probableCause;
	}
	/**
	 * @param probableCause the probableCause to set
	 */
	public void setProbableCause(String probableCause) {
		this.probableCause = probableCause;
	}
	/**
	 * @return the severity
	 */
	@XmlElement(name="severity")
	public String getSeverity() {
		return severity;
	}
	/**
	 * @param severity the severity to set
	 */
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	/**
	 * @return the eventType
	 */
	@XmlElement(name="eventtype")
	public String getEventType() {
		return EventType;
	}
	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(String eventType) {
		EventType = eventType;
	}
	/**
	 * @return the numId
	 */
	@XmlElement(name="numid")
	public long getNumId() {
		return numId;
	}
	/**
	 * @param numId the numId to set
	 */
	public void setNumId(long numId) {
		this.numId = numId;
	}
	/**
	 * @return the stringId
	 */
	@XmlElement(name="stringid")
	public String getStringId() {
		return stringId;
	}
	/**
	 * @param stringId the stringId to set
	 */
	public void setStringId(String stringId) {
		this.stringId = stringId;
	}
	/**
	 * @return the sourceType
	 */
	@XmlElement(name="sourcetype")
	public String getSourceType() {
		return sourceType;
	}
	/**
	 * @param sourceType the sourceType to set
	 */
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	/**
	 * @return the topMO
	 */
	@XmlElement(name="topmo")
	public String getTopMO() {
		return topMO;
	}
	/**
	 * @param topMO the topMO to set
	 */
	public void setTopMO(String topMO) {
		this.topMO = topMO;
	}
	/**
	 * @return the specificproblem
	 */
	@XmlElement(name="SPECIFICPROBLEM")
	public String getSpecificproblem() {
		return specificproblem;
	}
	/**
	 * @param specificproblem the specificproblem to set
	 */
	public void setSpecificproblem(String specificproblem) {
		this.specificproblem = specificproblem;
	}
}
	