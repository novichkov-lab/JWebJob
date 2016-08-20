package gov.lbl.webjob.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessRunner {

	String directory;
	String commands;
	
	public ProcessRunner(String initCommand){
		directory = "/tmp";
		this.commands = initCommand;
	}
	
	public ProcessRunner(String directory, String initCommand){
		this.directory = directory;
		this.commands = initCommand;
	}
	
	public String runAndReturn(){
		StringBuffer sb = new StringBuffer();
		try {
			ProcessBuilder pb = new ProcessBuilder("./" + "myDocker.sh");
			pb.directory(new File("/Users/Alex/Desktop/Code/Docker/cmdLineArgTest"));
			
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
	
	public static void main(String[] args) {
		new ProcessRunner("").runAndReturn();
	}

}
