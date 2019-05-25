package com.random.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.random.utilities.WaitConfig;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Base {

	public static WebDriver driver = null;

	@SuppressWarnings("static-access")
	public Base(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		System.setProperty("file.encoding", "UTF-8");
	}

	protected void setValueToTextField(String input, MobileElement element) {
		//	element.click();
		//	element.clear();
			element.sendKeys(input);
		}

}