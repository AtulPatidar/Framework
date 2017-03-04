package com.xpanxion.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

public class FirefoxWebDriver extends WebDriverFactory{
	
	WebDriver driver;

        @Override
	public WebDriver getDriver() {
        if (isRemote) {
//            driver = launchGridDriver(capabilities, Configuration.getInstance().getHubUrl());
            Reporter.log("Running test on Grid, in browser 'Firefox'", true);
        } else {
            driver = new FirefoxDriver();
            Reporter.log("Running test in browser 'FIREFOX'", true);
        }
		
		return driver;
	}
	
	

}
