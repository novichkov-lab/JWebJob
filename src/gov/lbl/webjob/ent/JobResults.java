package gov.lbl.webjob.ent;

import java.util.Date;

import org.mongodb.morphia.annotations.*;

import gov.lbl.webjob.util.Util;

@Entity("jobResult")
public class JobResults {

	@Id private String objectId;
	public String jobId;
	
	public Date created;
	public Date lastUpdated;
	public Object resultObj;
	
	public JobResults(){
		//Empty for Morphia
	}
	
	public JobResults(String jobId) {
		this.jobId = jobId;
		objectId = Util.genStringObjectId();
		created = Util.genTimestamp();
		lastUpdated = created;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Object getResultObj() {
		return resultObj;
	}

	public void setResultObj(Object resultObj) {
		this.resultObj = resultObj;
	}

}
