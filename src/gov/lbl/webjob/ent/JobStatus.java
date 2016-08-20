package gov.lbl.webjob.ent;

import java.util.ArrayList;
import java.util.Date;

import org.mongodb.morphia.annotations.*;

import gov.lbl.webjob.util.Util;

@Entity("jobStatus")
public class JobStatus {

	@Id private String objectId;
	public String jobId;

	public int overallStatus;
	public Date created;
	public Date lastUpdated;
	public Date finished;
	public ArrayList<TaskCollection> tasks;
	public String jobName;
	
	public JobStatus(){
		//Empty for Morphia
	}
	
	public JobStatus(String jobId){
		this.jobId = jobId;
		objectId = Util.genStringObjectId();
		overallStatus = 0;
		created = Util.genTimestamp();
		lastUpdated = created;
		tasks = null;
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

	public int getOverallStatus() {
		return overallStatus;
	}

	public void setOverallStatus(int overallStatus) {
		this.overallStatus = overallStatus;
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

	public Date getFinished() {
		return finished;
	}

	public void setFinished(Date finished) {
		this.finished = finished;
	}

	public ArrayList<TaskCollection> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<TaskCollection> tasks) {
		this.tasks = tasks;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
}
