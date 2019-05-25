package com.random.utilities;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.random.pages.Base;

import io.appium.java_client.AppiumDriver;

public class WaitConfig extends Base {
	public static final int PAGE_LOAD_DURATION = 5000;
	
	public WaitConfig(AppiumDriver driver) {
		super(driver);
	}

	public static void waitForPageToLoad(AppiumDriver driver, WebElement id) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(id));
	}
}
