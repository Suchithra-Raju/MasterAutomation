package Suchithra.MasterAutomation;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import queryParamAndPayload.queryParam;
import restUtils.AssertionUtils;
import restUtils.RestUtils;
import utilities.DataProviders;
import utilities.Log;

public class API {
	
	@Test(priority=1, dataProvider ="post")
	
	public void postMethod(String name,String job, String responseMessage){
		
	
		
		Log.startTestCase(this.getClass().getSimpleName());
		Response response = RestUtils.performPost(name, job);
		Assert.assertEquals(response.statusCode(),201);
		String respBody = response.getBody().asString();
		AssertionUtils.AssertContains(respBody, responseMessage, "response message validated" );
		System.out.println(response);
	
	

	

}
	@Test(priority=0, dataProvider ="get")
	public void getMethod(String page, String responseMessage) {
		
		Log.startTestCase(this.getClass().getSimpleName());
		Map<String,Object> params = queryParam.queryParamWithoutContainer(page);
		Response response = RestUtils.performGet(params);
		Assert.assertEquals(response.statusCode(),200);
		String respBody = response.getBody().asString();
		AssertionUtils.AssertContains(respBody, responseMessage, "response message validated" );
		System.out.println(response);
	}
	
	
	
	
	@DataProvider(name="get")
	public Object[][] getData() throws IOException{
		return DataProviders.getAllData("testData.xlsx", "get");
	}
	
	@DataProvider(name="post")
	public Object[][] postData() throws IOException{
		return DataProviders.getAllData("testData.xlsx", "post");
	}
}
