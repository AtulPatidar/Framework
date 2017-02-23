package com.framework.browsers;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CloudDeviceBrowser extends DriverFactory{
	
	private  WebDriver driver;
	public static final String URL = "https://" + configProperties.getCloudUserName() + ":" + configProperties.getCloudAutomationKey() + "@hub-cloud.browserstack.com/wd/hub";
	String browserName;
	String platform;
	String device;

	public WebDriver getDriver(){	
		try
		{

            String platform = System.getProperty("platform.name");
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("browserName", configProperties.getBrowserName());
			caps.setCapability("platform", platform);
			caps.setCapability("device", configProperties.getDevice());
		    caps.setCapability("browserstack.debug", "true");
	
		    driver = new RemoteWebDriver(new URL(URL), caps);
		    driver = new Augmenter().augment(driver);
		    
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return driver;
	}

	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

}
