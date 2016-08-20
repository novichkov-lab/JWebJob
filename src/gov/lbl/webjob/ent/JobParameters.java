package gov.lbl.webjob.ent;

import java.util.Date;

import org.mongodb.morphia.annotations.*;

import gov.lbl.webjob.util.Util;

@Entity("jobParams")
/*@Indexes(
	@Index("jobId, -created")
)*/

public class JobParameters {

	@Id private String objectId;
	public String jobId;
	public Date created;
	
	public String textInput1;
	public boolean checkbox1;
	public boolean checkbox2;
	public String dialogEnt;
	public String jobName;
	
	public JobParameters(){
		
	}
	
	public void initialize(){
		jobId = Util.genStringUUID();
		objectId = Util.genStringObjectId();
		created = Util.genTimestamp();
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

	public String getTextInput1() {
		return textInput1;
	}

	public void setTextInput1(String textInput1) {
		this.textInput1 = textInput1;
	}

	public boolean isCheckbox1() {
		return checkbox1;
	}

	public void setCheckbox1(boolean checkbox1) {
		this.checkbox1 = checkbox1;
	}

	public boolean isCheckbox2() {
		return checkbox2;
	}

	public void setCheckbox2(boolean checkbox2) {
		this.checkbox2 = checkbox2;
	}

	public String getDialogEnt() {
		return dialogEnt;
	}

	public void setDialogEnt(String dialogEnt) {
		this.dialogEnt = dialogEnt;
	}
	
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String toString(){
		return "JobParameters{objectId: " + objectId + ", jobId: " + jobId + ", created: " + created + ", textInput1: " + textInput1 + ", checkbox1: " + checkbox1 + ", checkbox2: " + checkbox2 + ", dialogEntId: " + dialogEnt + "}";
	}
	
}
