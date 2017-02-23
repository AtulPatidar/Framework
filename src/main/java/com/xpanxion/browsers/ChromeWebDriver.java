package com.framework.browsers;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

public class ChromeWebDriver extends DriverFactory{
	
	private WebDriver webDriver;

	public WebDriver getDriver() {
		// TODO Auto-generated method stub
		
		String chromePath = System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromePath);
        DesiredCapabilities capabilities;capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type", "start-maximized", "no-default-browser-check","--disable-extensions");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setPlatform(Platform.ANY);
        if (isRemote) {
            webDriver = launchGridDriver(capabilities, configProperties.getNodeUrl());
            Reporter.log("Running test on Grid, in browser 'CHROME' ", true);
        } else {
            webDriver = new ChromeDriver(capabilities);
            Reporter.log("Running test in browser 'CHROME'", true);
        }
		
		return webDriver;
	}

}
