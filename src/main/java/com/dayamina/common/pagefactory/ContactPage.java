package com.dayamina.common.pagefactory;

import com.dayamina.common.constants.PageTitle;
import com.dayamina.common.constants.PageURL;
import com.dayamina.common.core.PageBase;
import com.dayamina.common.utility.ExtentLogger;
import org.openqa.selenium.WebDriver;

public class ContactPage extends PageBase {
    public ContactPage(WebDriver _driver) {
        super(_driver);
        ExtentLogger.logInfo("Loaded the Contact Us page");
    }

    public boolean isLoaded(){
        return super.isLoaded(PageTitle.CONTACTUS, PageURL.CONTACTUS);
    }
}
