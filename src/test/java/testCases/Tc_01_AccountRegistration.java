package testCases;



import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;



public class Tc_01_AccountRegistration extends BaseClass {


@Test(groups={"Regression","Master"})
 public void Verify_Account_registration() {
	
	logger.info("-----Starting Test Case----");
	
	try {
	//to get method from home page
	HomePage hp=new HomePage(driver);
	
	logger.info("-----Launching web page----");
	hp.ClickMyAccount();
	Thread.sleep(2000);
	logger.info("-----Clicked my account----");
	hp.ClickRegister();
	Thread.sleep(2000);
	logger.info("-----Clicked register----");

	
	//to get method from registration page
	AccountRegistrationPage ARP=new AccountRegistrationPage(driver);
	logger.info("-----providing customer details----");
	ARP.setFirstName(randomeString().toUpperCase());
	ARP.setLastName(randomeString().toUpperCase());
	ARP.setEmail(randomeString().toUpperCase()+"@gmail.com");
	ARP.setTelephone(randomeNumber());
	String passwordall=randomeString().toUpperCase()+"@w"+randomeNumber();
	ARP.setPassword(passwordall);
	System.out.println("1st time "+passwordall);
	ARP.setConfirmPassword(passwordall);
	System.out.println("2nd time "+passwordall);
	ARP.SetPrivacyPolicy();
	ARP.clickContinue();

String confmsg=ARP.checkConfirmmsg();
//assert.assertEquals(confmsg, "Your Account Has Been Created!");
System.out.println(confmsg);


//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
logger.info("-----Assertion completed with test case closure----");

if(confmsg.equals("Your Account Has Been Created!")){
	
	Assert.assertTrue(true);
}else {
	logger.error("---test failed------");
	logger.debug("debug logs");
	Assert.assertTrue(false);
}

	}catch(Exception e) { //this won't get executed because we used hard assertion above
		System.out.println(e);
		
		Assert.fail();
	}
}


}
