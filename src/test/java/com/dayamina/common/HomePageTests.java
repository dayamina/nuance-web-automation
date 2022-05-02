package com.dayamina.common;

import com.dayamina.common.core.TestBase;
import com.dayamina.common.pagefactory.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class HomePageTests extends TestBase {

    HomePage homePage;

    @BeforeMethod
    public void loadHomePage(){
        homePage = new HomePage(getDriver());
    }

    @Test(description = "T00001 - Visitor can navigate to Company overview page")
    public void T00001_CanNavigateToCompanyOverview() {
        CompanyPage companyPage = homePage.clickCompanyOverview();
        assertTrue(companyPage.isLoaded());
    }

    @Test(description = "T00002 - Visitor can navigate to the About us page")
    public void T00002_CanNavigateToAbout() {
        AboutPage aboutPage = homePage.clickAboutUs();
        assertTrue(aboutPage.isLoaded());
    }

    @Test(description = "T00003 - Visitor can navigate to the Research page")
    public void T00003_CanNavigateToResearch() {
        ResearchPage researchPage = homePage.clickResearch();
        assertTrue(researchPage.isLoaded());
    }

    @Test(description = "T00004 - Visitor can navigate to the Careers page")
    public void T00004_CanNavigateToCareers() {
        CareersPage careersPage = homePage.clickSearchCareer();
        assertTrue(careersPage.isLoaded());
    }
}