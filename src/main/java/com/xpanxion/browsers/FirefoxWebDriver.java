package com.xpanxion.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

import com.xpanxion.core.Configuration;

public class FirefoxWebDriver extends WebDriverFactory{
	
	WebDriver driver;

        @Override
	public WebDriver getDriver() {
		// TODO Auto-generated method stub
		
		String firefoxPath = System.getProperty("user.dir") + "/src/main/resources/geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", firefoxPath);
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		//driver = new FirefoxDriver(capabilities);

        if (isRemote) {
            driver = launchGridDriver(capabilities, Configuration.getInstance().getHubUrl());
            Reporter.log("Running test on Grid, in browser 'Firefox'", true);
        } else {
            driver = new FirefoxDriver(capabilities);
            Reporter.log("Running test in browser 'FIREFOX'", true);
        }
		
		return driver;
	}
	
	

}
