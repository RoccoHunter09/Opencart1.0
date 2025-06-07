package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_02_LoginToAccount extends BaseClass {
	
	//now we will create3 only test method. because setup and teardown methods are already there in base class
	@Test(groups={"Sanity","Master"})
	public void Verify_login(){
		
		logger.info("-----Starting Test Case----");
		try {
		HomePage hp=new HomePage(driver);
		logger.info("-----Launching web page----");
		hp.ClickMyAccount();
		logger.info("-----Clicked my account----");
		hp.LoginToAccount();
		logger.info("-----Clicked on login account----");
		
		LoginPage lp=new LoginPage(driver);
		logger.info("-----providing email and password----");
		lp.SetEmail(p.getProperty("email"));
		lp.SetPassword(p.getProperty("password"));
		lp.ClickLogin();
		
		MyAccountPage ma=new MyAccountPage(driver);
		logger.info("-----Checking My Account Test----");
		boolean targetPage=ma.isMyAccounPageExits();
		
		
		Assert.assertEquals(targetPage, true,"Login failed");//Assert.assertTrue(targetPage);
		//Assert.assertTrue(targetPage);
		
		}catch(Exception e) {
			System.out.println(e);
			Assert.fail();
		}
		logger.info("-----Finished Tc_02----");
	}
	
	

}
