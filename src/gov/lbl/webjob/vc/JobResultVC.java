package gov.lbl.webjob.vc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gov.lbl.webjob.db.DataProvider;
import gov.lbl.webjob.ent.JobParameters;

/**
 * Servlet implementation class JobResultVC
 */
@WebServlet("/JobResultVC")
public class JobResultVC extends HttpServlet {
	private static final long serialVersionUID = 17687694L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobResultVC() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jobId = request.getParameter("Job");
		if(jobId==null){
			System.out.println("Lost job Id!");
			response.sendRedirect("JobInputVC");
		}else{
			DataProvider dp = DataProvider.getInstance();
			JobParameters jp = dp.findOne(JobParameters.class, "jobId", jobId); //JobResult should be accessed here, JobParameters only used for demonstration
			if(jp==null){
				//Handle attempt to access non-existent job 
			}
			System.out.println(jp);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/JobResult.jsp"); 
			request.setAttribute("jobName", jp.jobName);
			request.setAttribute("created", jp.created);
			
			rd.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
