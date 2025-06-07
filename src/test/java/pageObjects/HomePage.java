/**
 * 
 */
package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//basepage is parent of all the pageobjects classes

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnkMyAccount;
	
	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement Login;
	
	public void ClickMyAccount() {
		lnkMyAccount.click();
	}
	
	public void ClickRegister() {
		lnkRegister.click();
	}
	public void LoginToAccount() { //added in 5th step in framework document
		Login.click();
	}
	
}
