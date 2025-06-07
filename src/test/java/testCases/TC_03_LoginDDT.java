package testCases;

import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_03_LoginDDT extends BaseClass {
	
	@Test(dataProvider = "loginData" ,dataProviderClass=DataProviders.class,groups="DataDriven") //getting data provider from diff. class

	public void VerifyLoginDDT(String email,String password,String exp) {
	
	logger.info("-----Starting Test Case----");	
	try {
	HomePage hp=new HomePage(driver);
	hp.ClickMyAccount();
	hp.LoginToAccount();
	
	
	LoginPage lp=new LoginPage(driver);
	lp.SetEmail(email);
	lp.SetPassword(password);
	lp.ClickLogin();
	
	
	MyAccountPage ma=new MyAccountPage(driver);
	boolean targetPage=ma.isMyAccounPageExits();
	
	// Data is valid --login success --test pass-- logout
		// Data is valid --login failed --test fail
		
		// Data is invalid --login success --test fail --logout
		// Data is invalid--login failed--test pass
	
	if(exp.equalsIgnoreCase("valid")) {
		if(targetPage==true) {
			Assert.assertTrue(true);
			ma.LogOutFromAccount();
		}else {
			Assert.fail();
		}
	}if(exp.equalsIgnoreCase("invalid")) {
		if(targetPage==true) {
			ma.LogOutFromAccount();
			Assert.assertTrue(false);
			
		}else {
			Assert.assertTrue(true);
		}
	}
	
	}catch(Exception e) {
		System.out.println(e);
		Assert.fail();
	}
	
	
}



}
