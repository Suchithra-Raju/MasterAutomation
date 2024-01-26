package Suchithra.MasterAutomation;

import java.io.IOException;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import utilities.DataProviders;
import utilities.ExtentReportManager;
import utilities.Log;

public class AmazonWebUI extends seleniumBase.BaseClass{
	
	@Test(dataProvider ="webui")
	public void verify_account_registration(String searchName, String title)
	{
		
		Log.startTestCase(this.getClass().getSimpleName());
		logger.debug("application logs started......");
		logger.info("**** starting TC_001_AccountRegistrationTest  *****");
		
		try
		{
		HomePage hp=new HomePage(driver);
		hp.enterValue(searchName);
		logger.info("Entered value");
		ExtentReportManager.logInfoDetails("value entered in search box");
		
		hp.clickSubmit();
		logger.info("Clicked on submit");
		ExtentReportManager.logInfoDetails("submit button is clicked");
		hp.clickFirstImage();
		logger.info("Clicked on first image");
		ExtentReportManager.logInfoDetails("first image is clicked");
		
		Set<String> hv = driver.getWindowHandles();
		for(String h:hv) {
			System.out.println(h);
			String titles = driver.switchTo().window(h).getTitle();
			System.out.println(titles);
			if(titles.equals(title)) {
				driver.switchTo().window(h);
				
			}
			
		}
		ExtentReportManager.logInfoDetails("window title validated and switched");
		
		
		
		}
		catch(Exception e)
		{
			logger.error("test failed..");
			Assert.fail("An exception occurred: " + e.getMessage());
		}
		
		logger.debug("application logs end.......");
		logger.info("**** finished TC_001_AccountRegistrationTest  *****");
		
	}
	
	
	@DataProvider(name="webui")
	public Object[][] getData() throws IOException{
		return DataProviders.getAllData("testData.xlsx", "webui");
	}
	
	
	
}








