package br.com.rsinet.HUB_Appium.Utility;

import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class DriverManager {

	public static AndroidDriver driver;

	public static AndroidDriver openApp(String url, String pacote, String ativador) throws Exception {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", "Moto G5 Plus");
		capabilities.setCapability("udid", "192.168.137.247:5656");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", pacote);
		capabilities.setCapability("appActivity", ativador);

		driver = new AndroidDriver(new URL(url), capabilities);

		return driver;
	}

	public static void closeApp(AndroidDriver driver) {
		if (driver != null) {
			driver.quit();
		}
	}

}