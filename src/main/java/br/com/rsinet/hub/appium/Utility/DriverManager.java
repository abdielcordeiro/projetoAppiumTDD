package br.com.rsinet.hub.appium.Utility;

import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class DriverManager {

	public static AndroidDriver<WebElement> driver;

	public static AndroidDriver<WebElement> openApp(String url, String pacote, String ativador) throws Exception {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", "Moto G5 Plus");
		capabilities.setCapability("udid", "emulator-5554");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", pacote);
		capabilities.setCapability("appActivity", ativador);
		capabilities.setCapability("newCommandTimeout", "120");

		driver = new AndroidDriver<WebElement>(new URL(url), capabilities);

		return driver;
	}

	public static void closeApp(AndroidDriver<WebElement> driver) {
		if (driver != null) {
			driver.quit();
		}
	}

}