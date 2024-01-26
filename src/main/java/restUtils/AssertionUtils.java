package restUtils;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;

import io.restassured.response.Response;
import utilities.ExtentReportManager;

public class AssertionUtils {

	/**
	 * Assertion for two string values
	 * @param ActualValue
	 * @param ExpectedValue
	 * @param message
	 */
	
	public static void AssertEquals(String ActualValue, String ExpectedValue, String message) {
		try {
			String ActualValueWithoutSpaces = ActualValue.replace(" ","").toUpperCase();
			String ExpectedValueWithoutSpaces = ExpectedValue.replace(" ","").toUpperCase();
			Assert.assertEquals(ActualValueWithoutSpaces,  ExpectedValueWithoutSpaces, message);
			ExtentReportManager.logPassDetails(message);
		}catch(AssertionError e) {
			throw e;
		}
	}
	/**
	 * Assertion for status code
	 * @param response
	 * @param statuscode
	 * @param message
	 */
	
	public static void AssertStatusCode(Response response,int statuscode, String message) {
		try {
			
			Assert.assertEquals(response.statusCode(),statuscode);
			ExtentReportManager.logPassDetails(message);
		}catch(AssertionError e) {
			throw e;
		}
	}
	
	public static void AssertFalse(String label, String message) {
		try {
			boolean lable = label.isEmpty();
			Assert.assertFalse(lable);
			ExtentReportManager.logPassDetails(message);
		}catch(AssertionError e) {
			throw e;
		}
	}
	
	public static void AssertContainsInsideDataPara(String decodedString, String value, String message) {
		try {
			String strArray[]= value.split(",");
			for(int i=0;i<strArray.length;i++) {
				System.out.print(strArray[i]+",");
				assertTrue(decodedString.contains(strArray[i]));
			}
			ExtentReportManager.logPassDetails(message);
		}catch(AssertionError e) {
			throw e;
		}
	}
	
	public static void AssertContains(String decodedString, String value, String message) {
		try {
			assertTrue(decodedString.contains(value));
			
			ExtentReportManager.logPassDetails(message);
		}catch(AssertionError e) {
			throw e;
		}
}
	
	
	
	
}
