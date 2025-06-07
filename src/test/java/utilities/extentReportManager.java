package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class extentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter; //UI of the report
	public ExtentReports extent; //populate common info on the report
	public ExtentTest test;  //creating test case entries in the report and update status of test method
	
	String repName;  //report name
	
	public void onStart(ITestContext testContext) {
		
		SimpleDateFormat df=new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		Date dt=new Date();
		String currentdte=df.format(dt); //to get the current date time  stamp
		
		//String currentdte1=new SimpleDateFormat("dd-mm-yyyy").format(new Date()); //to get the current date time  stamp
		 
		repName="Test Name-"+currentdte+".html";
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);
		sparkReporter.config().setDocumentTitle("Opencart Automation Testing");
		sparkReporter.config().setReportName("Opencart functional testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os=testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser=testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
		extent.setSystemInfo("IncludedGroups", includedGroups.toString());
		
		}
		
		}
	
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName()); //create a new entry in the report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, "Test case passed is: "+result.getName()); //update status as p/f/s
	}
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName()); //create a new entry in the report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test case failed is: "+result.getName()); //update status as p/f/s
		test.log(Status.INFO, "Test case failed is: "+result.getThrowable().getMessage()); //it will return the error
	try {
	String imgpath=new BaseClass().captureScreen(result.getName()); //due to this we made driver static in base class
	test.addScreenCaptureFromPath(imgpath);
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
	 
	public void onTestSkip(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName()); //create a new entry in the report
		test.log(Status.SKIP, "Test case skipped is: "+result.getName());//update status as p/f/s
		test.log(Status.INFO, "Test case failed is: "+result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
		
		String pathOfExtentReport=System.getProperty("user.dir")+ "\\reports\\"+repName;
		File extentReport=new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		try {
	//URL url=new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
	URL url = new URI("file:\\"+System.getProperty("user.dir")+"\\reports\\"+repName).toURL();
			
		// Create the email message
		
		ImageHtmlEmail email= new ImageHtmlEmail();
		email.setDataSourceResolver (new DataSourceUrlResolver (url));
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator (new DefaultAuthenticator("pratapabhishek087@gmail.com","Rocky"));
		email.setSSLOnConnect(true);
		email.setFrom("pratapabhishek087@gmail.com"); //Sender
		email.setSubject("Test Results");
		email.setMsg("Please find Attached Report....");
		email.addTo("pratapabhishek08@gmail.com"); //Receiver

		email.attach(url, "extent report", "please check report...");

		email.send(); // send the email

		} catch(Exception e)


		{

		e.printStackTrace();

		} 
		
	    */
	  }
	
	

}
