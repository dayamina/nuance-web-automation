package com.dayamina.common.core;

import com.dayamina.common.utility.ExtentLogger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;


/**
 * Base Class for all the Page Factory Classes Contains Common Methods.
 * @author dayamina
 */

public class PageBase {

	public WebDriver driver;
	
	public final Logger logger = Logger.getLogger("com.dayamina.common.utility");
	
	public PageBase(WebDriver _driver) {
	        driver = _driver;
	        this.ensurePageReadyState();
	        PageFactory.initElements(driver, this);
	}
	 
	public void ensurePageReadyState(){
		 ExpectedCondition<Boolean> pageLoadCondition = new
	                ExpectedCondition<Boolean>() {
	                    public Boolean apply(WebDriver driver) {
	                        return ((JavascriptExecutor) driver).executeScript("return document.readyState;").equals("complete");
	                    }
	                };
	        WebDriverWait wait = new WebDriverWait(driver, 10);
	        wait.until(pageLoadCondition);
	}
	 
	public void waitForPageLoad(String expectedURL, int maxWaitTime){
		
		WebDriverWait wait = new WebDriverWait(driver,maxWaitTime);
		wait.until(ExpectedConditions.urlToBe(expectedURL));
	}

	public void waitForElementVisible(WebElement Element, int maxWaitTime){
		WebDriverWait wait = new WebDriverWait(driver,maxWaitTime);
		wait.until(ExpectedConditions.visibilityOf(Element));
	}

	public void click(WebElement element) {
		waitForElementVisible(element, 30);
		try {
			if (element.isDisplayed()) {
				Thread.sleep(1000);
				element.click();
				Thread.sleep(2000);
				ExtentLogger.logInfo("Clicked on "+ element);
			}
		}catch (Exception ex){
			ExtentLogger.logFatal("The element "+element.getText()+" is Not Visible");
		}
	}
	
	public void clearAndSendKeys(WebElement element, String textToEnter){
		waitForElementVisible(element, 30);
		if (element.isDisplayed()){
			element.clear();
			element.sendKeys(textToEnter);
			ExtentLogger.logInfo("Typing in "+ textToEnter);
		}else{
			ExtentLogger.logFatal("The element "+element.getText()+" is Not Visible");
		}
	}

	public void scrollToElementView(WebElement element){
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
	}
	
	public String getPageTitle(){
		this.ensurePageReadyState();
		return driver.getTitle();
	}

	public String getCurrentPageTitle(){
		this.ensurePageReadyState();
		return driver.getTitle();
	}

	public String getCurrentPageURL(){
		this.ensurePageReadyState();
		return driver.getCurrentUrl();
	}

	public boolean isLoaded(String pageTitle, String pageURL){
		Boolean pageLoadedState;
		try{
			ExtentLogger.logInfo("Expected Title: "+ pageTitle);
			ExtentLogger.logInfo("Actual Title: "+getCurrentPageTitle());
			ExtentLogger.logInfo("Expected URL: "+pageURL);
			ExtentLogger.logInfo("Actual URL: "+getCurrentPageURL());
			waitForPageLoad(pageURL,5);
			pageLoadedState = getCurrentPageTitle().contentEquals(pageTitle) && getCurrentPageURL().contentEquals(pageURL);
		} catch (Exception ex) {
			ExtentLogger.logError("Failed While verifying whether the page is loaded or not");
			pageLoadedState = false;
		}
		return pageLoadedState;
	}
}