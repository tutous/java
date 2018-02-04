package de.tutous.myutil;

public class MyUtil {

	   public static String getProcessInfos()
	   {
	      return "pid: "    + ProcessHandle.current().pid() + '\n' +
	             "user: " + ProcessHandle.current().info().user().get() + '\n' +
	             "cmd: "  + ProcessHandle.current().info().command().get() + '\n';
	   }
	
}
