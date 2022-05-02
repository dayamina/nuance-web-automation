package com.dayamina.common.pagefactory;

import com.dayamina.common.core.PageBase;
import com.dayamina.common.utility.ExtentLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
* Extends the PageBase and Contains Page Objects and Methods of Login Page.
* @author dayamina
*/
public class JobsPortalPage extends PageBase {

	public JobsPortalPage(WebDriver _driver) {
		super(_driver);
		ExtentLogger.logInfo("Loading Nuance Jobs Portal Page");
	}

	@FindBy(css="input[data-automation-id='keywordSearchInput']")
	public WebElement jobSearchBar;

	@FindBy(className="css-1se7ipt")
	public WebElement jobSearchBtn;

	@FindBy(className="css-12psxof")
	public WebElement jobsFoundTxt;

	public void searchJob(String jobTitle){
		try {
			ExtentLogger.logInfo("Searching for : "+jobTitle+" jobs");
			clearAndSendKeys(jobSearchBar, jobTitle);
			click(jobSearchBtn);
			ExtentLogger.logInfo("Completed the search!");
		} catch (Exception ex) {
			ExtentLogger.logError("Error while searching for Jobs with keyword "+ jobTitle);
		}
	}

	public int getJobsFoundCount(){
		int jobsFoundCount = 0;
		try {
			ExtentLogger.logInfo("Checking the number of jobs found");
			String jobsFoundCountLabel = jobsFoundTxt.getText();
			ExtentLogger.logInfo("LABEL = "+ jobsFoundCountLabel);
			jobsFoundCount = Integer.parseInt(jobsFoundCountLabel.split(" ")[0]);
			ExtentLogger.logInfo("Job Search Results = " + jobsFoundCountLabel);
		} catch (Exception ex) {
			ExtentLogger.logError("Error while finding the Jobs count");
		}
		return jobsFoundCount;
	}
}
