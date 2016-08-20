package gov.lbl.webjob.vc;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gov.lbl.webjob.db.DataProvider;
import gov.lbl.webjob.ent.JobStatus;
import gov.lbl.webjob.ent.TaskCollection;

/**
 * Servlet implementation class JobProgressVC
 */
@WebServlet("/JobProgressVC")
public class JobProgressVC extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobProgressVC() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		session = request.getSession();
		
		DataProvider dp = DataProvider.getInstance();
		System.out.println("jobId passed by session from JobProgressVC: " + session.getAttribute("jobId"));
		JobStatus jStatus = dp.findOne(JobStatus.class, "jobId", session.getAttribute("jobId"));
		ArrayList<TaskCollection> tasks = jStatus.getTasks();
		ArrayList<TaskCollection> statusEvents = new ArrayList<TaskCollection>();
		statusLister(tasks, statusEvents);
		request.setAttribute("iterableItemList", statusEvents);
		request.setAttribute("jobName", jStatus.jobName);
		
		jStatus.setTasks(null);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/JobProgress.jsp"); 
		rd.forward(request, response);
	}

	private void statusLister(ArrayList<TaskCollection> fromList, ArrayList<TaskCollection> toList){
		if(fromList!=null){
			for(int i = 0; i<fromList.size(); i++){
				TaskCollection tc = fromList.get(i);
				toList.add(tc);
				statusLister(tc.getSubTasks(), toList);
				tc.setSubTasks(null); //set to decrease storage space since toList would contain every TaskCollection anyway.
			}
		}
	}
	
}
