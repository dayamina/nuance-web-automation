package com.dayamina.common;

import com.dayamina.common.constants.PageURL;
import com.dayamina.common.core.TestBase;
import com.dayamina.common.pagefactory.JobsPortalPage;
import com.dayamina.common.utility.TestDataProvider;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class JobPortalTests extends TestBase {

	JobsPortalPage jobsPortalPage;

	@BeforeMethod(alwaysRun=true)
	public void loadJobPortalPage(){
		jobsPortalPage = new JobsPortalPage(getDriver());
		navigateToPage(PageURL.JOBPORTAL);
	}

	@Test(description="T00006 - Candidate can search with valid job keywords", dataProvider = "valid-job-keywords", dataProviderClass = TestDataProvider.class)
	public void T0006_CanSearchWithValidKeywords(String jobKeyword){
		jobsPortalPage.searchJob(jobKeyword);
		assertTrue(jobsPortalPage.getJobsFoundCount() > 0);
	}
}

