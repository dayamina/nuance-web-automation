package com.dayamina.common.pagefactory;

import com.dayamina.common.constants.PageTitle;
import com.dayamina.common.constants.PageURL;
import com.dayamina.common.core.PageBase;
import com.dayamina.common.utility.ExtentLogger;
import org.openqa.selenium.WebDriver;

public class CompanyPage extends PageBase {
    public CompanyPage(WebDriver _driver) {
        super(_driver);
        ExtentLogger.logInfo("Company overview page is loaded");
    }

    public boolean isLoaded(){
        return isLoaded(PageTitle.COMPANY, PageURL.COMPANY);
    }


}
