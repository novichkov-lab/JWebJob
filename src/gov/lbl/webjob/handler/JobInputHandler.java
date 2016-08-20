package gov.lbl.webjob.handler;

import java.io.IOException;
import java.util.ArrayList;
//import java.util.Properties; uncomment to access config.properties file

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gov.lbl.webjob.JobLauncher;
import gov.lbl.webjob.db.DataProvider;

import gov.lbl.webjob.ent.JobParameters;
import gov.lbl.webjob.ent.JobResults;
import gov.lbl.webjob.ent.JobStatus;
import gov.lbl.webjob.ent.TaskCollection;
import gov.lbl.webjob.ent.TempStorage;

/**
 * Servlet implementation class JobInputHandler
 */
@WebServlet("/JobInputHandler")
public class JobInputHandler extends HttpServlet {
	private static final long serialVersionUID = 11L;
    //private static Properties prop; uncomment to access config.properties file
    private HttpSession session;
        
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    
    /**
        * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
        * 
        */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	/*//USEFUL: config.properties access
        Properties properties = new Properties();
		InputStream input = getServletContext().getResourceAsStream("/WEB-INF/config.properties");
		properties.load(input);
		input.close();
		prop = properties;
        prop.getProperty(String.valueOf(ERRORCODESTRING));
        */			
    	
    	session = request.getSession();
    	JobParameters jp = new JobParameters();
    	jp.initialize();
    	
    	jp.setJobName(request.getParameter("jobNameInput"));
    	jp.setTextInput1(request.getParameter("textInput1"));
    	if(request.getParameter("checkbox1")!=null){
    		jp.setCheckbox1(true);
    	}else{
    		jp.setCheckbox1(false);
    	}
    	if(request.getParameter("checkbox2")!=null){
    		jp.setCheckbox2(true);
    	}else{
    		jp.setCheckbox2(false);
    	}
    	System.out.println("passed selection parameter: " + (String) request.getParameter("dialogSelection"));
    	jp.setDialogEnt((String) request.getParameter("dialogSelection"));
    	
    	DataProvider dp = DataProvider.getInstance();
    	System.out.println(jp);
    	dp.save(jp);
    	System.out.println("Sent jobId: " + jp.jobId);
    	
    	//session.setAttribute("sessionJobId", jp.jobId);
    	
    	System.out.println("Creating database entires for JobStatus and JobResults");
    	
    	JobStatus jStatus = new JobStatus(jp.jobId);
    	jStatus.setJobName(request.getParameter("jobNameInput"));
    	dp.save(jStatus);
    	dp.save(new JobResults(jp.jobId));
    	
    	createTaskCollection(dp, jp.jobId);
    	
    	JobLauncher jl = new JobLauncher(jp.jobId);
    	Thread thread = new Thread(jl, "JobLauncher");
    	thread.start();
    	
    	session.setAttribute("jobId", jp.jobId);
    	
    	//Removing Temp Storage after data from it is stored in the Job Parameters
    	String tempId = (String) session.getAttribute("uploadTempStorageId");
    	System.out.println("tempId from session: " + tempId);
    	dp.deleteEntity(TempStorage.class, "storageId", tempId);

    	
    	
    	
    	
    	RequestDispatcher rd = request.getRequestDispatcher("JobProgressVC"); 
    	rd.include(request, response);
    	
		//------//rd.forward(request, response);
    }

    private void createTaskCollection(DataProvider dp, String jobId){
    	//JobStatus jStatus = dp.findOne(JobStatus.class, "jobId", jobId);
		ArrayList<TaskCollection> tempList1 = new ArrayList<TaskCollection>();
		ArrayList<TaskCollection> tempList2 = new ArrayList<TaskCollection>();
		ArrayList<TaskCollection> tempList3 = new ArrayList<TaskCollection>();
		
		TaskCollection tcOne = new TaskCollection("task1.0");
		tcOne.setSubTaskLevel(0);
		TaskCollection tcOnePointOne = new TaskCollection("task1.1");
		tcOnePointOne.setSubTaskLevel(1);
		TaskCollection tcOnePointOnePointOne = new TaskCollection("task1.1.1");
		tcOnePointOnePointOne.setSubTaskLevel(2);
		TaskCollection tcOnePointOnePointTwo = new TaskCollection("task1.1.2");
		tcOnePointOnePointTwo.setSubTaskLevel(2);
		TaskCollection tcOnePointTwo = new TaskCollection("task1.2");
		tcOnePointTwo.setSubTaskLevel(1);
		TaskCollection tcTwo = new TaskCollection("task2.0");
		tcOnePointTwo.setSubTaskLevel(0);
		tempList3.add(tcOnePointOnePointOne);
		tempList3.add(tcOnePointOnePointTwo);
		tcOnePointOne.setSubTasks(tempList3);
		tempList2.add(tcOnePointOne);
		tempList2.add(tcOnePointTwo);
		tcOne.setSubTasks(tempList2);
		tempList1.add(tcOne);
		tempList1.add(tcTwo);
		dp.updateKey(JobStatus.class, "jobId", jobId, "tasks", tempList1);
    }

}