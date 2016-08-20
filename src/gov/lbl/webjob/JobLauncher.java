package gov.lbl.webjob;

import java.io.IOException;
import java.util.ArrayList;

import gov.lbl.webjob.db.DataProvider;
import gov.lbl.webjob.ent.JobResults;
import gov.lbl.webjob.ent.JobStatus;
import gov.lbl.webjob.ent.TaskCollection;
import gov.lbl.webjob.exception.ParameterMissingException;
import gov.lbl.webjob.util.ProcessRunner;
import gov.lbl.webjob.util.Util;

public class JobLauncher implements Runnable{

	DataProvider dp;
	
	String jobId;
	
	public JobLauncher(String jobId){
		this.jobId = jobId;
		dp = DataProvider.getInstance();
	}
	
	@Override
	public void run(){
		
		if(jobId == null){
			try {
				throw new ParameterMissingException();
			} catch (ParameterMissingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("JobLauncher thread started.");
		
		

		
		
		
		//TEST, PROCESSING JOB
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JobStatus jStatus = dp.findOne(JobStatus.class, "jobId", jobId);
		ArrayList<TaskCollection> tc = jStatus.getTasks();
		tc.get(0).getSubTasks().get(0).setTaskStatus(1);
		
//		//SAMPLE DOCKER PROCESS
//		int int1 = 5;
//		int int2 = 11;
//		//the directory where the program runs doesn't really matter unless there are files that are generated during runtime that need to be imported. 
//		//As long as the docker daemon runs, any image can be called from any other directory.
//		output = Util.runProcess("/usr/local/bin/", "docker run image-name " + int1 + " " + int2);
//		System.out.println(output);
//		
//		//END PROCESS
		
		dp.updateKey(JobStatus.class, "jobId", jobId, "tasks", tc);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Example job time is done.");
		
		
		//dp.updateKey(JobResults.class, "jobId", jobId, "", valueToInsert);
		JobResults jr = dp.findOne(JobResults.class, "jobId", jobId);
		jr.setLastUpdated(Util.genTimestamp());
		jr.setResultObj("Job is completed, showing result string, but this could really be any result you need to show.");
		dp.save(jr);
		System.out.println("JobResults is updated with result.");
		
		//END OF JOB PROCESS
		terminateJob();
		System.out.println("JobLauncher thread finished.");
		
		return; //Added to end thread. Eclipse still gives warnings about resource leak.
	}
	
	private void terminateJob(){
		dp.updateKey(JobStatus.class, "jobId", jobId, "overallStatus", 1);
	}
	
}
