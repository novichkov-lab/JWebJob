package gov.lbl.webjob.ent;

import java.util.ArrayList;

import gov.lbl.webjob.util.Util;

public class TaskCollection {

	public String taskName;
	public int taskStatus;
	public String taskId;
	public int subTaskLevel; //represents how deep into the main task the subtask is
	
	public ArrayList<TaskCollection> subTasks;
	
	public TaskCollection(){
		//Morphia empty constructor
	}
	
	public TaskCollection(String taskName){
		this.taskName = taskName;
		initialize();
	}
	
	private void initialize(){
		taskId = Util.genStringUUID();
		taskStatus = 0;
		subTasks = null;
		subTaskLevel = 0;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public int getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public ArrayList<TaskCollection> getSubTasks() {
		return subTasks;
	}

	public void setSubTasks(ArrayList<TaskCollection> subTasks) {
		this.subTasks = subTasks;
	}

	public int getSubTaskLevel() {
		return subTaskLevel;
	}

	public void setSubTaskLevel(int taskLevel) {
		this.subTaskLevel = taskLevel;
	}
	
}
