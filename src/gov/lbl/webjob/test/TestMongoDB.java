package gov.lbl.webjob.test;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import gov.lbl.webjob.db.DataProvider;
import gov.lbl.webjob.ent.JobStatus;

public class TestMongoDB {
	public static void main(String[] args) throws NamingException {
		
//		
//		Hashtable<String,String> vals = new Hashtable<String,String>();
//		vals.put("mongoPath", "localhost:27017");
//		vals.put("dbName", "pgpDatabase");
//		Context env = new javax.naming.InitialContext(vals);
//		
//		
				
		
		DataProvider dp = DataProvider.getInstance();
		dp.updateKey(JobStatus.class, "jobId", "93b3c157-bf59-4051-a81a-c87ee27b750c", "jobName", "QQQ");
	}
}
