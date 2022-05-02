package com.dayamina.common.pagefactory;

import com.dayamina.common.constants.PageTitle;
import com.dayamina.common.constants.PageURL;
import com.dayamina.common.core.PageBase;
import com.dayamina.common.utility.ExtentLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
* Extends the PageBase and Contains Page Objects and Methods of Header Page.
* @author dayamina
*/
public class CareersPage extends PageBase {

	public CareersPage(WebDriver _driver) {
		super(_driver);
		ExtentLogger.logInfo("Loaded Nuance careers page!");
	}

	@FindBy(xpath = "//*[@id='main-content']/div[3]/div[1]/div/div/div/div/p/a")
	public WebElement findCareerBtn;

	@FindBy(xpath="//button[contains(text(),'See all job listings')]")
	public WebElement jobListingsBtn;

	public JobsPortalPage clickFindCareer() {
		scrollToElementView(findCareerBtn);
		click(findCareerBtn);
		return new JobsPortalPage(driver);
	}

	public JobsPortalPage clickAllJobListings() {
		click(jobListingsBtn);
		return new JobsPortalPage(driver);
	}

	public boolean isLoaded(){
		super.ensurePageReadyState();
		return isLoaded(PageTitle.CAREERS, PageURL.CAREERS);
	}
}
