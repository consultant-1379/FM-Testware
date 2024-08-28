package com.ericsson.oss.fmservice.api.beans;

/**
 * 
 */
/*

 *//**
 * @author evinjay
 *
 */
/*

package com.ericsson.oss.fmservice.api.beans;
import java.io.Serializable;
import java.util.Date;



import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Alarm")
public class Alarm implements Serializable {

private String FDN;
private Date EventTime;
private String SpecificProblem;
private String ProbableCause;
private String Severity;
private String EventType;
private String SourceType;


 (non-Javadoc)
 * @see java.lang.Object#toString()
 
@Override
public String toString() {
	
	System.out.println("Vinay strings are " +"Alarm [FDN=" + FDN + ", EventTime=" + EventTime
			+ ", SpecificProblem=" + SpecificProblem + ", ProbableCause="
			+ ProbableCause + ", Severity=" + Severity + ", EventType="
			+ EventType + ", SourceType=" + SourceType + "]");
	return "Alarm [FDN=" + FDN + ", EventTime=" + EventTime
			+ ", SpecificProblem=" + SpecificProblem + ", ProbableCause="
			+ ProbableCause + ", Severity=" + Severity + ", EventType="
			+ EventType + ", SourceType=" + SourceType + "]";
}

 *//**
 * @return the sourceType
 */
/*
public String getSourceType() {
return SourceType;
}

 *//**
 * @param sourceType the sourceType to set
 */
/*
public void setSourceType(String sourceType) {
SourceType = sourceType;
}

 *//**
 * @param eventTime the eventTime to set
 */
/*
public void setEventTime(Date eventTime) {
EventTime = eventTime;
}

public String getFDN()
{

return FDN;
}

public Date getEventTime()
{

return EventTime;
}

public String getSpecificProblem()
{

return SpecificProblem;
}

public String getProbableCause()
{

return ProbableCause;
}

public String getEventType()
{

return EventType;
}

public String getSeverity()
{

return Severity;
}

public void setFDN(String FDN)
{

this.FDN = FDN;
}



public void setSpecificProblem(String SP)
{

this.SpecificProblem = SP;
}

public void setProbableCause(String PC)
{

this.ProbableCause = PC;
}

public void setEventType(String ET)
{

this.EventType = ET;
}

public void setSeverity(String severity)
{

this.Severity = severity;
}

}
 */

import java.io.Serializable;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "notification", "fdn", "eventTime",
		"severity", "specificProblem", "probableCause", "eventType", "numId",
		"stringId", "sourceType" })
@XmlRootElement(name = "Alarm")
public class Alarm implements Serializable {
	@XmlElement(name = "Notification", required = true)
	protected String notification;
	@XmlElement(name = "FDN", required = true)
	protected String fdn;
	@XmlElement(name = "EventTime")
	protected String eventTime;
	@XmlElement(name = "Severity")
	protected String severity;
	@XmlElement(name = "SpecificProblem")
	protected String specificProblem;
	@XmlElement(name = "ProbableCause")
	protected String probableCause;
	@XmlElement(name = "EventType")
	protected String eventType;
	@XmlElement(name = "NumId")
	protected String numId;
	@XmlElement(name = "StringId")
	protected String stringId;
	@XmlElement(name = "SourceType")
	protected String sourceType;

	/**
	 * @return the notification
	 */
	public String getNotification() {
		return notification;
	}

	/**
	 * @param notification the notification to set
	 */
	public void setNotification(String notification) {
		this.notification = notification;
	}

	/**
	 * @return the fdn
	 */
	public String getFdn() {
		return fdn;
	}

	/**
	 * @param fdn
	 *            the fdn to set
	 * @return possible object is {@link String }
	 * 
	 */
	public void setFdn(String fdn) {
		this.fdn = fdn;
	}

	/**
	 * @return the eventTime
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEventTime() {
		return eventTime;
	}

	/**
	 * @param eventTime
	 *            the eventTime to set
	 */
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	/**
	 * @return the severity
	 */
	public String getSeverity() {
		return severity;
	}

	/**
	 * @param severity
	 *            the severity to set
	 */
	public void setSeverity(String severity) {
		this.severity = severity;
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
	public void setSpecificProblem(String specificProblem) {
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
	public void setProbableCause(String probableCause) {
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
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the numId
	 */
	public String getNumId() {
		return numId;
	}

	/**
	 * @param numId
	 *            the numId to set
	 */
	public void setNumId(String numId) {
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
	public void setStringId(String stringId) {
		this.stringId = stringId;
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
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		System.out.println("Alarm [fdn=" + fdn + ", eventTime=" + eventTime
				+ ", severity=" + severity + ", specificProblem="
				+ specificProblem + ", probableCause=" + probableCause
				+ ", eventType=" + eventType + ", sourceType=" + sourceType
				+ "]");
		return "Alarm [fdn=" + fdn + ", eventTime=" + eventTime + ", severity="
				+ severity + ", specificProblem=" + specificProblem
				+ ", probableCause=" + probableCause + ", eventType="
				+ eventType + ", sourceType=" + sourceType + "]";
	}

}