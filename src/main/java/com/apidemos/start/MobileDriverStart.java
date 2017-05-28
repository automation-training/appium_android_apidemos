package com.apidemos.start;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class MobileDriverStart {

	@Test
	public void testDialogTitle() throws MalformedURLException {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "android");
		capabilities.setCapability("platformVersion", "7.1.1");
		capabilities.setCapability("deviceName", "Emulator");
		capabilities.setCapability("avd", "Pixel_XL_API_25");
		capabilities.setCapability("app", "E:\\android\\apps\\ApiDemos-debug.apk");

		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		AndroidDriver<MobileElement> driver = new AndroidDriver<>(url, capabilities);

		MobileElement appTab = driver.findElementByAccessibilityId("App");
		appTab.click();

		MobileElement alertDialogsTab = driver.findElementByAccessibilityId("Alert Dialogs");
		alertDialogsTab.click();

		driver.findElementByAccessibilityId("Text Entry dialog").click();

		MobileElement nameInput = driver.findElement(By.id("io.appium.android.apis:id/username_edit"));
		nameInput.sendKeys("Appium");

		MobileElement alertTitleElement = driver.findElement(By.id("android:id/alertTitle"));
		String actualTitle = alertTitleElement.getText();
		String expectedTitle = "Text Entry Dialog";
		
		try {
			FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE), new File("screenshot.png"));
		} catch (WebDriverException | IOException e) {
			e.printStackTrace();
		}
		
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
//	ApiDemosApp.openMainPage().selectAppTab().then().selectAlertDialogsTab();
}
