package utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
	
	public static ExtentReports extentReports;
	
	
	
	/**
	 * Method to mention report features
	 * @param fileName
	 * @param reportName
	 * @param documentTitle
	 * @return
	 */
	public static ExtentReports createInstance(String fileName, String reportName, String documentTitle) {
		
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(fileName);
		extentSparkReporter.config().setReportName(reportName);//report name
		extentSparkReporter.config().setDocumentTitle(documentTitle);//title of the report
		extentSparkReporter.config().setTheme(Theme.DARK);//standard or dark theme
		extentSparkReporter.config().setEncoding("utf-8");
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		return extentReports;
	}
	
	/**
	 * To create a report with name and timestamp
	 * @return
	 */
	public static String getReportNameWithTimeStamp() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
		LocalDateTime localDateTime = LocalDateTime.now();
		String formattedTime = dateTimeFormatter.format(localDateTime);
		String reportName = "API_AutomationTestReport" + "_" + formattedTime + ".html";
		return reportName;
	}
	/**
	 * method to log pass details
	 * @param log
	 */
	public static void logPassDetails(String log) {
		SetupExtentReport.extentTest.get().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
	}
	
	/**
	 * method to log fail details
	 * @param log
	 */
	public static void logFailureDetails(String log) {
		SetupExtentReport.extentTest.get().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
	}
	/**
	 * method to log exceptional details
	 * @param log
	 */
	public static void logExceptionDetails(String log) {
		SetupExtentReport.extentTest.get().fail(log);
	}
	/**
	 * Method to log info details
	 * @param log
	 */
	public static void logInfoDetails(String log) {
		SetupExtentReport.extentTest.get().info(MarkupHelper.createLabel(log, ExtentColor.BLUE));
	}
	/**
	 * method to log warning details
	 * @param log
	 */
	public static void logWarningDetails(String log) {
		SetupExtentReport.extentTest.get().warning(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
	}
	
	/**
	 * method to log json data
	 * @param object
	 */
	public static void logJson(Object object) {
		SetupExtentReport.extentTest.get().info(MarkupHelper.createCodeBlock((String) object,CodeLanguage.JSON));
	}
	

}
