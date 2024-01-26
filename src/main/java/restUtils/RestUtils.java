package restUtils;

import java.util.Base64;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import queryParamAndPayload.pojo;
import utilities.ExtentReportManager;
import utilities.ReadPropertiesFile;

public class RestUtils {
	
	/**
	 * getRequest for baseURI and queryparams
	 * @param queryParam
	 * @return
	 */
	public static RequestSpecification getRequestSpecification(Map<String, Object> queryParam) {
		return RestAssured.given().baseUri(ReadPropertiesFile.readProperties()).queryParams(queryParam);
	}
	
	  private static RequestSpecification getRequestSpecificationPost(String name, String job) {
		  pojo p = new pojo(name,job);
		  return RestAssured.given().baseUri(ReadPropertiesFile.readProperties()).body(p);
	    }
	
	
	/**
	 * method to print details of request in report
	 * @param requestSpecification
	 */
	public static void printRequestLogInReport(RequestSpecification requestSpecification) {
		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
		ExtentReportManager.logInfoDetails("EndPoint is"+ queryableRequestSpecification.getBaseUri());
		ExtentReportManager.logInfoDetails("Method is"+ queryableRequestSpecification.getMethod());
		ExtentReportManager.logInfoDetails("Request body is"+ queryableRequestSpecification.getBody());
		ExtentReportManager.logInfoDetails("Query Params is"+ queryableRequestSpecification.getQueryParams());

	}
	
	/**
	 * method to print response details in report
	 * @param response
	 */
	public static void printResponseLogInReport(Response response) {
		ExtentReportManager.logInfoDetails("Response status is"+ response.getStatusCode());
		ExtentReportManager.logInfoDetails("Response body is"+ response.getBody().prettyPrint());
	}
	/**
	 * request to execute the request,get() and print request and response in
	 * @param queryParam
	 * @return
	 */
	public static Response performGet(Map<String, Object> queryParam) {
		RequestSpecification requestSpecification = getRequestSpecification(queryParam);
		Response response = requestSpecification.get();
		printRequestLogInReport(requestSpecification);
		printResponseLogInReport(response);
		return response;
	}
	
	 public static Response performPost(String name, String job) {
		 
	        RequestSpecification requestSpecification = getRequestSpecificationPost(name,job);
	       
	        //pojo authRequest = new pojo("TOOLSQA-Test", "Test@@123");
	        Response response =  requestSpecification.post();
	        printRequestLogInReport(requestSpecification);
	        printResponseLogInReport(response);
	        return response;
	    }
	 
	/**
	 * function to decode the base64code
	 * @param encodedString
	 * @return
	 */
	public static String base64decode(String encodedString) {
		byte[] base64DecodedBytes = Base64.getDecoder().decode(encodedString.substring(2));
		String decodedString = new String(base64DecodedBytes);
		
		decodedString = decodedString.replaceAll("?","<br>");
		String formattedTrace = "<details>\n"+"<summary>Click Here</summary>\n"+"   "+decodedString +"\n"+"</details>\n";
		ExtentReportManager.logInfoDetails("Decoded label data is" + formattedTrace);
		//assetTrue(decodedString.contains("PreLoadCheckComplete"));
		return decodedString;
		
	}

}
