package com.random.tests;

import com.random.pages.GenerateNumberPage;
import com.random.testbase.TestBase;
import com.random.utilities.JSONDataProvider;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class GenerateNumberTest extends TestBase {

	GenerateNumberPage generatenumber;

	@BeforeTest
	@Override
	public void setUpPage() {
		generatenumber = new GenerateNumberPage(driver);
		JSONDataProvider.dataFile = DATA_FILE;
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class, enabled = true)
	public void tc001_generatenumber(String rowID, String description, JSONObject testData)
			throws InterruptedException, IOException, NumberFormatException, ClassNotFoundException {

		int randomGeneratedNo = generatenumber.generateRandomNumber();
		Assert.assertTrue(randomGeneratedNo>=1 && randomGeneratedNo<=1000);
		System.out.println("Test Pass");
	}
}
