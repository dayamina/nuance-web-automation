package com.dayamina.common.pagefactory;

import com.dayamina.common.constants.PageTitle;
import com.dayamina.common.constants.PageURL;
import com.dayamina.common.core.PageBase;
import org.openqa.selenium.WebDriver;

public class AboutPage extends PageBase {
    public AboutPage(WebDriver _driver) {
        super(_driver);
    }

    public boolean isLoaded(){
        super.ensurePageReadyState();
        return isLoaded(PageTitle.ABOUT, PageURL.ABOUT);
    }


}
