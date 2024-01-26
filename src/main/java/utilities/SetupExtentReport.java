package utilities;

import java.io.IOException;
import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import seleniumBase.BaseClass;

public class SetupExtentReport implements ITestListener {
	
	
	public static ExtentReports extentReports;
	//extent test represents one test case. But when run in parallel, then we need to ensure both test case is not overlap.
	//So for that thread local. help u to keep separate extent test for 2 cases.
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	
	/**
	 * Initialize the extent report
	 */
	public void onStart(ITestContext context) {
		String fileName = ExtentReportManager.getReportNameWithTimeStamp();
		String fullReportPath = System.getProperty("user.dir")+"\\reports\\"+fileName;
		extentReports = ExtentReportManager.createInstance(fullReportPath,  "API Automation Report",  "Test ExecutionReport");
	}
	/**
	 * method to execute on finish of test
	 */
	public void onFinish(ITestContext context) {
		if(extentReports != null)
			extentReports.flush();
	}
	/**
	 * method to execute at start of test get test class- wrtie package name get method -write test case name
	 */
	public void onTestStart(ITestResult result) {
		ExtentTest test = extentReports.createTest("Test Case"+"-"+result.getMethod().getMethodName(),result.getMethod().getDescription());
		
		/**
		 * ExtentRest test = extentReports.createTest("Test Name"+result.getTestClass().getName()+"-"+result.getMethod().getMethodName(),result.getMethod().getDescription());
		 */
		extentTest.set(test);
		test.pass("details").addScreenCaptureFromPath("screenshot.png");

		// i added this for webui screenshot not applicable for api
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * method to execute on failure of test
	 */
	public void onTestFailure(ITestResult result) {
		ExtentReportManager.logFailureDetails(result.getThrowable().getMessage());
		String stackTrace = Arrays.toString(result.getThrowable().getStackTrace());
		stackTrace = stackTrace.replaceAll(",","<br>");
		String formmatedTrace = "<details>\n" + "<summary>Click here to see exception logs</summary>\n"+"   "+ stackTrace+"\n"+"</detauks>\n";
		ExtentReportManager.logExceptionDetails(formmatedTrace);
		
		// i added this for webui screenshot not applicable for api
		ExtentTest test = extentReports.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL,result.getName()+" got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		//test.fail("details").addScreenCaptureFromPath("screenshot.png");

		
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	

}
