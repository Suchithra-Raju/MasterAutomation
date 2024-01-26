package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
@FindBy(xpath="//input[@type='text']") 
WebElement txtSearchbox;

@FindBy(xpath="//input[@type='submit']") 
WebElement btnSubmit;

@FindBy(xpath = "//img[@class='s-image'][1]")   
WebElement linkFirstImage;




public void enterValue(String text)
{
	txtSearchbox.sendKeys(text);;
}

public void clickSubmit()
{
	btnSubmit.click();
}


public void clickFirstImage()    // added in step5
{
	linkFirstImage.click();
}
}

