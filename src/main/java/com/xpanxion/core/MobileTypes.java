package com.xpanxion.core;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.UnreachableBrowserException;

import com.xpanxion.exceptions.InvalidHubUrlException;
import com.xpanxion.utils.AppiumServer;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public enum MobileTypes implements DriverInstance<WebDriver> {
	
	ANDROID {
		@Override
		public AppiumDriver<WebElement> getDriverInstance() {
			
			DesiredCapabilities capabilities = getCapability();
			
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
			AppiumDriver<WebElement> driver;
			try {
				LOG.info("Starting appium server...");
				AppiumServer.startServer(getAppiumUrl().toString());
				driver = new AndroidDriver<WebElement>(getAppiumUrl(),capabilities);
			} catch (SessionNotCreatedException | UnreachableBrowserException e) {
				LOG.error("Failed to launch application, Please make sure appium server is up and running at: "
						+ getAppiumUrl(), e);
				e.printStackTrace();
				throw e;
			}
			return driver;
		}

	},
	IOS {
		@Override
		public WebDriver getDriverInstance() {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	
	final static Logger LOG = Logger.getLogger(CoreTestCase.class);
	private static DesiredCapabilities getCapability() {
		Configuration config = Configuration.getInstance();
		DesiredCapabilities capabilities = Configuration.getInstance().getCapabilities();
		String appPath = config.getAppPath();
		if (appPath != null) {
			capabilities.setCapability(MobileCapabilityType.APP, appPath);
		}
		return capabilities;
	}
	
	private static URL getAppiumUrl(){
		String appiumhubUrl = Configuration.getInstance().getHubUrl();
		try{
			return new URL(appiumhubUrl);
		}
		catch(MalformedURLException e){
			LOG.error("Invalid appium hub URL: "+appiumhubUrl);
			new InvalidHubUrlException("Invalid appium hub URL: "+appiumhubUrl);
		}
		return null;
	}
}
