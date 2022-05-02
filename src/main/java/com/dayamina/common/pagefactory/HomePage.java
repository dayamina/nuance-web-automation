package com.dayamina.common.pagefactory;

import com.dayamina.common.core.PageBase;
import com.dayamina.common.utility.ExtentLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
* Extends the PageBase and Contains Page Objects and Methods of Nuance Home Page.
* @author dayamina
*/
public class HomePage extends PageBase {

	public HomePage(WebDriver _driver) {
		super(_driver);
		ExtentLogger.logInfo("Loading the Nuance Home Page!");
	}

	@FindBy(xpath = "//*[@id='hp-career-brick-tile2']/div/div/a")
	public WebElement searchCareersBtn;

	@FindBy(xpath = "//*[@id='footer']/div/div[1]/div[1]/div[1]/div/ul/li[1]/a")
	public WebElement companyLabel;

	@FindBy(linkText = "About us")
	public WebElement aboutUsLabel;

	@FindBy(linkText = "Research")
	public WebElement researchLabel;

	@FindBy(linkText = "Careers")
	public WebElement careersLabel;

	public CompanyPage clickCompanyOverview() {
		scrollToElementView(companyLabel);
		click(companyLabel);
		return new CompanyPage(driver);
	}

	public AboutPage clickAboutUs() {
		scrollToElementView(companyLabel);
		click(aboutUsLabel);
		return new AboutPage(driver);
	}

	public CareersPage clickSearchCareer() {
		scrollToElementView(searchCareersBtn);
		click(searchCareersBtn);
		return new CareersPage(driver);
	}

	public ResearchPage clickResearch() {
		scrollToElementView(researchLabel);
		click(researchLabel);
		return new ResearchPage(driver);
	}

	public CareersPage clickCareers() {
		scrollToElementView(careersLabel);
		click(careersLabel);
		return new CareersPage(driver);
	}

}
