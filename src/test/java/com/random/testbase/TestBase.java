package com.random.testbase;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.random.utilities.ExtentManager;
import com.random.utilities.RandomConstants;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public abstract class TestBase {

	public static AppiumDriver<MobileElement> driver;
	private static Properties prop = new Properties();
	private static InputStream input = null;
	public static String includePattern = null;
	public static final String DATA_FILE = "TestData.json";
	public ExtentReports reports = ExtentManager.getInstance();
	public static ExtentTest test;
	public static ExtentReports extent;

	private String stringPath = "src/test/resources/App/";

	@BeforeTest
	public abstract void setUpPage();

	@BeforeSuite
	public void setUpAppium() throws IOException, InterruptedException {

		final String URL_STRING = "http://127.0.0.1:4723/wd/hub";

		URL url = new URL(URL_STRING);
		DesiredCapabilities capabilities = new DesiredCapabilities();

		if ("ANDROID".equalsIgnoreCase(RandomConstants.APP_TYPE)) {
			capabilities.setPlatform(Platform.ANDROID);
			capabilities.setVersion(RandomConstants.ANDROID_OS_VER);
			capabilities.setCapability("deviceName", RandomConstants.ANDROID_DEVICE);
			capabilities.setCapability("appPackage", "com.vasilchenko.randomfree");
			try {
				String filename = "configuration.properties";
				input = getClass().getClassLoader().getResourceAsStream(filename);
				if (input == null) {
					System.out.println("Sorry, unable to find " + filename);
					return;
				}
				prop.load(input);
				if (!prop.getProperty("ANDROID_APK").equalsIgnoreCase("NoInstall")) {
					File appDir = new File(stringPath);
					File app = new File(appDir, prop.getProperty("ANDROID_APK"));
					capabilities.setCapability("app", app.getAbsolutePath());
				} else {
					System.out.println("App Already Installed");
				}

			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			capabilities.setCapability("appActivity", "com.vasilchenko.randomfree.MenuActivity");
			driver = new AndroidDriver<MobileElement>(url, capabilities);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} else if ("iOS".equalsIgnoreCase(RandomConstants.PLATFORM)) {

			// capabilities for iOS have been given just for reference

			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.1.2");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Laxmi iPhone");
			capabilities.setCapability("udid", "2022d06a1d3dce96v4f3dc030gf58955c97e8");
			capabilities.setCapability("bundleId", "com.random.Digit88");
			capabilities.setCapability("appiumVersion", "1.7.2");
			capabilities.setCapability("simpleIsVisibleCheck", true);
			capabilities.setCapability("report.disable", true);
			capabilities.setCapability("xcodeOrgId", "RL6GFT3M5I");
			capabilities.setCapability("xcodeSigningId", "Developer");
			try {
				String filename = "configuration.properties";
				input = getClass().getClassLoader().getResourceAsStream(filename);
				if (input == null) {
					System.out.println("Sorry, unable to find " + filename);
					return;
				}
				prop.load(input);
				if (!prop.getProperty("IOS_IPA").equalsIgnoreCase("NoInstall")) {
					File appDir = new File(stringPath);
					File app = new File(appDir, prop.getProperty("IOS_IPA"));
					capabilities.setCapability("app", app.getAbsolutePath());
				} else {
					System.out.println("App Already Installed");
				}

			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			driver = new IOSDriver<MobileElement>(url, capabilities);
		}
	}

	@AfterSuite
	public void tearDownAppium() {
		System.out.println("afterSuite");
		driver.quit();
	}

	@AfterMethod
	public void restartApp() throws InterruptedException, IOException {
		System.out.println("********@AfterMethod********");
		driver.resetApp();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
	}
}