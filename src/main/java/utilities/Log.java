package utilities;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
	
	//Initialize Log4j logs
	 private static final Logger Log = LogManager.getLogger(Log.class.getName());
	 
	 
	//This is to print log for the beginning of the test case, as we usually run so many test cases a s a test suite
	 
	 public static void startTestCase(String sTestCaseName) {
		 Log.info("*********");
		 Log.info("*********");
		 Log.info("$$$$      "+sTestCaseName+"     $$$$$$");
		 Log.info("*********");
		 Log.info("*********");
		 
	 }
	 
	 
	//This is to print log for the ending of the test case
	 public static void endTestCase() {
		 Log.info("XXXXX    "+"E--N---D"+" XXXXXXXX");
	 }
	 
	 //Need to create these methods, so that they can be called
	 
	 public static void infor(String message) {
		 Log.info(message);
	 }
	 
	 public static void warn(String message) {
		 Log.warn(message);
	 }
	 
	 public static void error(String message) {
		 Log.error(message);
	 }
	 
	 public static void fatal(String message) {
		 Log.fatal(message);
	 }
	 
	 public static void debug(String message) {
		 Log.debug(message);
	 }
	 
}