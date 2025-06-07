package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//h2[normalize-space()='My Account']") //myaccount heading
	WebElement msgHeading;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") //logout button//added in step 6
	WebElement Logout;
	
	


public boolean isMyAccounPageExits() {
	
	try {
	return(msgHeading.isDisplayed());}
	catch(Exception e) {
		return false;
	}
} 

public void LogOutFromAccount() {
	Logout.click();
}
}
