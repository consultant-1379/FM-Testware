package com.ericsson.nms.fm.fm_communicator;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap; 


/**
 * @author tcsnahi This Class is used to send Notification from FmService to
 *         FMCommunicator
 * 
 */

public class Notification implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8902184529980921058L;
	private NotificationType notificationType;
	private String managedObjectInstance;
	private String specificProblem;
	private String probableCause;
	private String eventType;
	private EventSeverity severity;
	private String sourceType;
	public HashMap<String,String> otherAttributes;
	private String topObjectName;
	private String operatorName;
	private String originatorName;
	private String timeZone;
	private Date theTime;
	private EventState previousEventState;
	private long numId; 
	private String stringId; 
	private AckStatus ackState;

	/**
	 * @return the notificationType
	 */
	public NotificationType getNotificationType() {
		return notificationType;
	}

	/**
	 * @param notificationType
	 *            the notificationType to set
	 */
	public void setNotificationType(final NotificationType notificationType) {
		this.notificationType = notificationType;
	}

	/**
	 * @return the managedObjectInstance
	 */
	public String getManagedObjectInstance() {
		return managedObjectInstance;
	}

	/**
	 * @param managedObjectInstance
	 *            the managedObjectInstance to set
	 */
	public void setManagedObjectInstance(final String managedObjectInstance) {
		this.managedObjectInstance = managedObjectInstance;
	}

	/**
	 * @return the specificProblem
	 */
	public String getSpecificProblem() {
		return specificProblem;
	}

	/**
	 * @param specificProblem
	 *            the specificProblem to set
	 */
	public void setSpecificProblem(final String specificProblem) {
		this.specificProblem = specificProblem;
	}

	/**
	 * @return the probableCause
	 */
	public String getProbableCause() {
		return probableCause;
	}

	/**
	 * @param probableCause
	 *            the probableCause to set
	 */
	public void setProbableCause(final String probableCause) {
		this.probableCause = probableCause;
	}

	/**
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * @param eventType
	 *            the eventType to set
	 */
	public void setEventType(final String eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the severity
	 */
	public EventSeverity getSeverity() {
		return severity;
	}

	/**
	 * @param severity
	 *            the severity to set
	 */
	public void setSeverity(final EventSeverity severity) {
		this.severity = severity;
	}

	/**
	 * @return the sourceType
	 */
	public String getSourceType() {
		return sourceType;
	}

	/**
	 * @param sourceType
	 *            the sourceType to set
	 */
	public void setSourceType(final String sourceType) {
		this.sourceType = sourceType;
	}


	/**
	 * @return the otherAttributes
	 */
	public HashMap<String, String> getOtherAttributes() {
		return otherAttributes;
	}

	/**
	 * @param otherAttributes the otherAttributes to set
	 */
	public void setOtherAttributes(final HashMap<String, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
	}

	/**
	 * @return the topObjectName
	 */
	public String getTopObjectName() {
		return topObjectName;
	}

	/**
	 * @param topObjectName
	 *            the topObjectName to set
	 */
	public void setTopObjectName(final String topObjectName) {
		this.topObjectName = topObjectName;
	}

	/**
	 * @return the operatorName
	 */
	public String getOperatorName() {
		return operatorName;
	}

	/**
	 * @param operatorName
	 *            the operatorName to set
	 */
	public void setOperatorName(final String operatorName) {
		this.operatorName = operatorName;
	}

	/**
	 * @return the originatorName
	 */
	public String getOriginatorName() {
		return originatorName;
	}

	/**
	 * @param originatorName
	 *            the originatorName to set
	 */
	public void setOriginatorName(final String originatorName) {
		this.originatorName = originatorName;
	}

	/**
	 * @return the timeZone
	 */
	public String getTimeZone() {
		return timeZone;
	}

	/**
	 * @param timeZone
	 *            the timeZone to set
	 */
	public void setTimeZone(final String timeZone) {
		this.timeZone = timeZone;
	}

	/**
	 * @return the theTime
	 */
	public Date getTheTime() {
		return theTime;
	}

	/**
	 * @param theTime
	 *            the theTime to set
	 */
	public void setTheTime(final Date theTime) {
		this.theTime = theTime;
	}

	/**
	 * @return the previousEventState
	 */
	public EventState getPreviousEventState() {
		return previousEventState;
	}

	/**
	 * @param previousEventState
	 *            the previousEventState to set
	 */
	public void setPreviousEventState(final EventState previousEventState) {
		this.previousEventState = previousEventState;
	}

	/**
	 * @return the numId
	 */
	public long getNumId() {
		return numId;
	}

	/**
	 * @param numId
	 *            the numId to set
	 */
	public void setNumId(final long numId) {
		this.numId = numId;
	}

	/**
	 * @return the stringId
	 */
	public String getStringId() {
		return stringId;
	}

	/**
	 * @param stringId
	 *            the stringId to set
	 */
	public void setStringId(final String stringId) {
		this.stringId = stringId;
	}

	/**
	 * @return the ackState
	 */
	public AckStatus getAckState() {
		return ackState;
	}

	/**
	 * @param ackState
	 *            the ackState to set
	 */
	public void setAckState(final AckStatus ackState) {
		this.ackState = ackState;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Notification [notificationType=" + notificationType
				+ ", managedObjectInstance=" + managedObjectInstance
				+ ", specificProblem=" + specificProblem + ", probableCause="
				+ probableCause + ", eventType=" + eventType + ", severity="
				+ severity + ", sourceType=" + sourceType
				+ ", otherAttributes=" + otherAttributes + ", topObjectName="
				+ topObjectName + ", operatorName=" + operatorName
				+ ", originatorName=" + originatorName + ", timeZone="
				+ timeZone + ", theTime=" + theTime + ", previousEventState="
				+ previousEventState + ", numId=" + numId + ", stringId="
				+ stringId + ", ackState=" + ackState + "]";
	}

	public enum NotificationType {
		SYNC_START, SYNC_END, SYNC_ABORT, CLEAR_ALL_EVENT, CLOSE_NOTIFICATION, ACK_NOTIFICATION, SYNC_EVENT_NOTIFICATION, ALARM_EVENT, ERROR_EVENT, NODE_SUSPENDED
	}

	public enum EventState {
		ACTIVE_ACKNOWLEDGED, ACTIVE_UNACKNOWLEDGED, CLEARED_ACKNOWLEDGED, CLEARED_UNACKNOWLEDGED, CLOSED
	}

	public enum EventSeverity {
		CRITICAL, MAJOR, MINOR, WARNING, CLEARED, INDERMINATE
	}

	public enum AckStatus {
		ACKNOWLEDGED, UNACKNOWLEDGED
	}

}
