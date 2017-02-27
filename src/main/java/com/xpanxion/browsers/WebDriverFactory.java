package com.xpanxion.browsers;

import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import com.xpanxion.core.Configuration;

public abstract class WebDriverFactory {
    boolean isRemote = Configuration.getInstance().isRemote();
	public abstract WebDriver getDriver();

    /**
     * Launches a RemoteWebDriver
     * @param capabilities browser capabilities
     * @param url URL of the grid Hub
     * @return
     */
    public WebDriver launchGridDriver(Capabilities capabilities, String url){
        try{
            return new RemoteWebDriver(new URL(url), capabilities);
        } catch(Exception e){
            Reporter.log("There was an error setting up the remote WebDriver.");
            e.printStackTrace();
            return null;
        }
    }

}
