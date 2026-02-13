package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastName;
	
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;
	
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtpassword;
	
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtConfirmpassword;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement PrivacyPolicy;
	
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btncontinue;
	
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfiramation;
	
	public void  setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}
	
	public void  setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}

	public void  setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void  setTelephone(String tphone) {
		txtTelephone.sendKeys(tphone);
	}
	
	public void  setPassword(String pwd) {
		txtpassword.sendKeys(pwd);
	}
	
	public void  setConfirmPassword(String pwd) {
		txtConfirmpassword.sendKeys(pwd);
	}
	
	public void  SetPrivacyPolicy() {
		PrivacyPolicy.click();
	}
	
	public void  clickContinue() {
		btncontinue.click();
	}
	
	public String checkConfirmmsg() {
		try{
			return(msgConfiramation.getText());
		}catch(Exception e) {
		return(e.getMessage());
	}
	
	}
}
