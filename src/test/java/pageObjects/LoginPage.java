package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtemailaddress;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtpassword;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btnLogin;
	
	
	public void SetEmail(String email) {
		txtemailaddress.sendKeys(email);
	}
	
	public void SetPassword(String password) {
		txtpassword.sendKeys(password);
	}
	
	
	public void ClickLogin() {
		btnLogin.click(); 
	}
	


}
