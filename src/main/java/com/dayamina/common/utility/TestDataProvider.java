package com.dayamina.common.utility;

import org.testng.annotations.DataProvider;
public class TestDataProvider extends DataGenerator{

	@DataProvider(name = "valid-job-keywords")
	public static Object[][] validJobKeywords() {
		return new Object[][] {{jobKeywords()}};
	}
}
