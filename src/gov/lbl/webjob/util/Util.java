package gov.lbl.webjob.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.bson.types.ObjectId;

/**
 * @author Alex
 *
 */
public class Util {

	/**
	 * Creates a unique UUID.
	 * @return randomUUID().toString()
	 * @see <a target="_top" href="http://docs.oracle.com/javase/7/docs/api/java/util/UUID.html">Java: UUID</a>
	 */
	public static String genStringUUID(){
		return UUID.randomUUID().toString();
	}
	
	/**
	 * Generates String timestamp with current time.
	 * @return Date in format <b>YYYY-MM-DD-HH:MM:SS</b> Timezone
	 */
	public static String genSimpleTimestamp(){
		Calendar currentDate = Calendar.getInstance();
	    SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD-HH:mm:ss z");
	    String dateNow = formatter.format(currentDate.getTime());
	    return dateNow;
	}
	
	public static Date genTimestamp(){
		return new Date();
	}
	
	
	
	/**
	 * Creates a String representation of a unique ObjectId.
	 * @return ObjectId.toString()
	 * @see <a target="_top" href="https://docs.mongodb.com/manual/reference/method/ObjectId/">MongoDB: ObjectId</a>
	 */
	public static String genStringObjectId(){
		return new ObjectId().toString();
	}
	
	public static String runProcess(String directory, String command){
		StringBuffer sb = new StringBuffer();
		try {
			ProcessBuilder pb = new ProcessBuilder("./" + command);
			pb.directory(new File(directory));
			
			Process p = pb.start();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String consoleOutput;
			
			while((consoleOutput = br.readLine()) != null){
				sb.append(consoleOutput);
			}
			System.out.println("Process successfully finished with result: " + sb.toString());
			
		} catch (IOException e) {
			System.out.println("ProcessRunner has caught an exception");
			e.printStackTrace();
		}
		System.out.println("Returning StringBufferResult");
		return sb.toString();
	}
	
}
