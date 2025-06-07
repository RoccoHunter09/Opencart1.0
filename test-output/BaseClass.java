package testCases;

import java.time.Duration;


import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
//below are the common things from every test cases thas why we kept it in a single file	
public WebDriver driver;

public Logger logger; // for log4j
	
	@BeforeClass
	public void setup() {
		
	//	logger=LogManager.getlogger
		logger=LogManager.getLogger(this.getClass());  //import org.apache.logging.log4j.Logger; //dynamically we can get the class each time
		
		driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost/opencartsite/");
		driver.manage().window().maximize();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	//creating a java method to randomly allocate the values
		public String randomeString() {
			//for below line , i used old version of lang 3 which is 3.12(please research why it did not work with 3.17)
			String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
		}

		public String randomeNumber() {
			//for below line , i used old version of lang 3 which is 3.12(please research why it did not work with 3.17)
			String generatedNumber=RandomStringUtils.randomNumeric(10);
		return generatedNumber;
		}


}
