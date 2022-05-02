package com.dayamina.common.core;

import com.dayamina.common.pagefactory.HomePage;
import com.dayamina.common.utility.ExtentLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Base Class for all the Test Classes Contains Common Methods.
 * @author dayamina
 */
public class TestBase {

	public WebDriver driver;
	public String testName;
	public String testDescription = null;
	private Properties testProp;
	private FileInputStream inputSettings;
	public HomePage homePage;
	public SoftAssert verify;
	public static Logger log = LogManager.getLogger(TestBase.class.getName());
	
	public TestBase(){
		this.loadTestSettings();
		this.loadLoggerConfig();
	}

	@BeforeSuite
	@Parameters("browser")
	public void loadTestReport(@Optional("Chrome") String browserName){
		ExtentLogger.startReport("SeleniumReport-"+browserName);
		log.info("Executing the Before Suite..");
	}
	
	@BeforeMethod
	@Parameters("browser")
	public void loadNuanceAndStartTest(@Optional("Chrome") String browserName, Method method) throws Exception{
		verify = new SoftAssert();
		testName = method.getName();
		Test test = method.getAnnotation(Test.class);
		testDescription = test.description();
		System.out.println("=========================================================");
		System.out.println(testName+" : "+testDescription);
		ExtentLogger.startTest(testName, testDescription);
		driver = loadDriver(browserName);
	}
		
	@AfterMethod
	public void closeBrowserAndLogResults(ITestResult result) throws Exception{
		System.out.println(result.getMethod().getDescription());
		ExtentLogger.getResults(result, driver);
		closeBrowser();
	}
	
	@AfterSuite
	public void flushResults() throws Exception{
		ExtentLogger.flushReport();
		System.out.println("After @AfterSuite");
	}
	
	public void loadTestSettings(){
		try {
			setTestProp(new Properties());
			inputSettings = new FileInputStream("configuration/settings.properties");
			getTestProp().load(inputSettings);	
		} catch (Exception Ex) {
			Assert.fail("Failed to Load the Settings ");
			Ex.printStackTrace();
		}	
	}
	
	public void loadLoggerConfig(){
		 //DOMConfigurator.configure("resources/log4j.xml");
	}
	
	public void loadPageAndMaximize(String targetURL){
		driver.navigate().to(targetURL);
	}

	public void closeBrowser(){
		try {
			this.driver.quit();
			System.out.println("<INFO> : Closing the Browser");
		} catch (Exception Ex) {
			Ex.printStackTrace();
		}
	}

	public WebDriver loadDriver(String browserType) {

    	switch (browserType) {
            case "FireFox":
            	DesiredCapabilities desiredFFCap = DesiredCapabilities.firefox();
            	desiredFFCap.setCapability("marionette", true);
            	WebDriverManager.firefoxdriver().setup();
            	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            	driver.manage().window().maximize(); 
            	break;
            case "InternetExplorer":
            	DesiredCapabilities desiredIECap = DesiredCapabilities.internetExplorer();
            	desiredIECap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				WebDriverManager.iedriver().setup();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                break;
            case "Chrome":
            	ChromeOptions options = new ChromeOptions();
            	options.addArguments("--start-maximized");
            	Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                options.setExperimentalOption("prefs", prefs);
				WebDriverManager.chromedriver().setup();
                driver =  new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                System.out.println("<INFO> : Loaded the Chrome Driver");
                break;
            case "Safari":
            	//TO-DO
            	break;
            default:
            	System.out.println(driver.toString()+ "Browser Not Supported");
            	break;     	
        }
    	
    	System.out.println("<INFO> : Loaded the Browser as "+ browserType);
    	loadPageAndMaximize(getTestProp().getProperty("NUAN_URL"));
        System.out.println("<INFO> : Browser is Launched with the URL " + getTestProp().getProperty("NUAN_URL"));
        return driver;
    }
	
	public WebDriver getDriver(){
		return this.driver;
	}

	public Properties getTestProp() {
		return testProp;
	}

	public void navigateToPage(String pageURL){
		driver.navigate().to(pageURL);
		ExtentLogger.logInfo("Navigating to "+pageURL);
	}

	public void setTestProp(Properties testProp) {
		this.testProp = testProp;
	}

}
