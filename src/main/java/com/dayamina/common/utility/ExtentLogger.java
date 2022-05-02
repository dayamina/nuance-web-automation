package com.dayamina.common.utility;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Optional;
import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

/**
* Logger Class for Generating the HTML Test Reports.
* @author dayamina
*/
public final class ExtentLogger {

	public static ExtentHtmlReporter htmlReporter;
	public static ExtentTest extTest;
	public static ExtentReports extent = new ExtentReports(); ;

	public ExtentLogger() {
	} 

	public static void startReport(String suiteName)
     {
         htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\test-reports\\"+suiteName.toLowerCase()+".html");
		 extent.attachReporter(htmlReporter);
         htmlReporter.config().setReportName("Test Report - 1.0");
         htmlReporter.config().setDocumentTitle("NUAN Careers Automation Report");
         htmlReporter.config().setEncoding("UTF-8");
		 extent.setSystemInfo("Environment", "Pre-Production");
		 extent.setSystemInfo("Build", "zxzxxzx Release");
		 extent.setSystemInfo("Version", "1.0.0");
		 extent.setSystemInfo("TestSuite", "Acceptance");
		 extent.setSystemInfo("Browser", "Chrome");
		 extent.setSystemInfo("Owner", "Test Automation Team");
		 extent.setSystemInfo("OS Name", System.getProperty("os.name"));
		 extent.setAnalysisStrategy(AnalysisStrategy.TEST);

     }	
	 
	public static void startTest(String testCaseName, @Optional String testDescription){	
		 extTest = extent.createTest(testCaseName, testDescription);
		 ExtentLogger.logInfo("Test execution started.");
	 }
	
	public static void getResults(ITestResult result, WebDriver driver) throws Exception{
		
		String screenCaptureName = result.getMethod().getMethodName().substring(4).toLowerCase()+"-"+DataGenerator.generateNumber(1);
		String testDescription = result.getMethod().getDescription();
		String testDefnition = testDescription.substring(testDescription.lastIndexOf("-")+1);
		 
		if (result.getStatus()==ITestResult.SUCCESS){
			logPass("Test Passed");
			extTest.log(Status.PASS, "Test Validated - "+testDefnition);
		 }
		 else if (result.getStatus()==ITestResult.FAILURE) {
		 	logFail("Test Failed");
			 String ScreenShotPath = ScreenShotUtil.getScreenShotPath(driver,screenCaptureName);
			 extTest.addScreenCaptureFromPath(ScreenShotPath);
			 extTest.log(Status.ERROR, result.getThrowable().getLocalizedMessage());
			 extTest.log(Status.FAIL, "Failed Test - "+ testDefnition+" <p> Refer the SNAPSHOT Below: </p>");	 
		}else {
			extTest.log(Status.SKIP, "Skipped the Test - " + testDefnition);
		} 
	 }
	 
	public static void flushReport(){
		extent.flush();
	 }
	
	public static void logPass(String successMessage){
		extTest.log(Status.PASS, successMessage);
	}
	
	public static void logFail(String failureMessage){
		extTest.log(Status.FAIL, failureMessage);
	}
	
	public static void logInfo(String infoMessage){
		Log.info(infoMessage);
		extTest.log(Status.INFO, infoMessage);
	}
	
	public static void logError(String errorMessage){
		extTest.log(Status.ERROR, errorMessage);
		Assert.fail();
	}
	
	public static void logFatal(String fatalMessage){
		extTest.log(Status.FATAL, fatalMessage);
	}

}
