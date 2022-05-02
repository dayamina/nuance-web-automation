package com.dayamina.common.pagefactory;

import com.dayamina.common.constants.PageTitle;
import com.dayamina.common.constants.PageURL;
import com.dayamina.common.core.PageBase;
import org.openqa.selenium.WebDriver;

public class ResearchPage extends PageBase {
    public ResearchPage(WebDriver _driver) {
        super(_driver);
    }

    public boolean isLoaded(){
        return super.isLoaded(PageTitle.RESEARCH, PageURL.RESEARCH);
    }
}
