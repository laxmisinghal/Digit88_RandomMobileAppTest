package com.random.pages;

import java.awt.image.TileObserver;
import java.util.List;
import java.util.Random;
import java.util.SplittableRandom;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.random.utilities.RandomConstants;
import com.random.utilities.WaitConfig;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class GenerateNumberPage extends Base {

	@AndroidFindBy(id = "title_app")
	private MobileElement appTitle;
//	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"Generate number\")")
	@AndroidFindBy(id="number")
	private MobileElement generateNumber;
	@AndroidFindBy(id = "min")
	private MobileElement min;
	@AndroidFindBy(id = "max")
	private MobileElement max;
	@AndroidFindBy(id = "btn")
	private MobileElement generateBtn;
	@AndroidFindBy(id = "result")
	private MobileElement result;
	
	
	public GenerateNumberPage(AppiumDriver driver) {
		super(driver);
	}

	/*
	 * public boolean validateTitle() { try {
	 * System.out.println(appTitle.getText()); if
	 * (appTitle.getText().equals(RandomConstants.APP_TITLE)) {
	 * System.out.println("true"); return true; } } catch (Exception e) {
	 * e.printStackTrace(); return false; } return false; }
	 */

	public int generateRandomNumber() {
		try {
			if (generateNumber.isDisplayed()) {
				// WaitConfig.waitForPageToLoad(driver, generateNumber);
				generateNumber.click();
			//	driver.manage().timeouts().implicitlyWait(WaitConfig.PAGE_LOAD_DURATION, TimeUnit.MILLISECONDS);
				
				int r1 = random();
				System.out.println("r1 = "+r1);
				super.setValueToTextField((Integer.toString(r1)), min);
				int r2 = random();
				System.out.println("r2 = "+r2);
				super.setValueToTextField((Integer.toString(r2)), max);
				generateBtn.click();
			//	driver.manage().timeouts().implicitlyWait(WaitConfig.PAGE_LOAD_DURATION, TimeUnit.MILLISECONDS);
				System.out.println("result is "+result.getText());
				String res = result.getText();
				int result = Integer.parseInt(res);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}

	public int random() {
		int randomInt = new SplittableRandom().nextInt(1, 1000);
		return randomInt;
	}
}
