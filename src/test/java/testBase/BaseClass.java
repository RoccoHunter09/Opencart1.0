package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
//below are the common things from every test cases thats why we kept it in a single file	
public static WebDriver driver;
//public WebDriver driver; //--make sure to make driver non static while running parallel testing/ other driver will get confuse

public Logger logger; // for log4j

public Properties p; //loading property file
	
	@BeforeClass(groups={"Regression","Sanity","Master","DataDriven"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException {
		
		//loading config.properties file
		
		FileReader file =new FileReader("./src//test//resources//config.properties"); //we nead to load the "file" for that we will create a object for properties file
		p=new Properties();
		p.load(file);
		
		
		
		
	//	logger=LogManager.getlogger
		logger=LogManager.getLogger(this.getClass());  ////import org.apache.logging.log4j.Logger; dynamically we can get the class each time
		
		//below things are for grid setup for remote
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities capabilities =new DesiredCapabilities();
			
		//for os
			if(os.equalsIgnoreCase("windows")) {
				
				capabilities.setPlatform(Platform.WIN11);
			}else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}else if(os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			}else {
				System.out.println("no matching found");
				return;
			}
			//for browser
			
			switch(br.toLowerCase()) {
			case "chrome":capabilities.setBrowserName("chrome");break;
			case "edge":capabilities.setBrowserName("MicrosoftEdge");break;
			case "firefox":capabilities.setBrowserName("firefox");break;
			default:  System.out.println("No matching browser found");return;
			}
			//driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
			
			
			try {
				driver=new RemoteWebDriver(new URI("http://localhost:4444/wd/hub").toURL(),capabilities);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			//here we will write code for cross browser 
			switch(br.toLowerCase()) {
			case "chrome" :driver=new ChromeDriver();break;
			case "edge" :driver=new EdgeDriver();break;
			case "firefox" :driver=new FirefoxDriver();break;
			default :System.out.println("...invalid driver....");
			return; //this will exit from execution
			
			}	
			
		}
		
		
		
		
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(p.getProperty("appUrl1")); //reading url from properties file
		driver.manage().window().maximize();
	}

	@AfterClass(groups={"Regression","Sanity","Master","DataDriven"})
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

		public String captureScreen(String tname) {
			String timestamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			TakesScreenshot takescreenshot=(TakesScreenshot)driver;
			File sourcefile=takescreenshot.getScreenshotAs(OutputType.FILE);
			
			String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+timestamp+".png";
			File targetFile=new File(targetFilePath);
			
			sourcefile.renameTo(targetFile);
			return targetFilePath;
		}
		
		
}
