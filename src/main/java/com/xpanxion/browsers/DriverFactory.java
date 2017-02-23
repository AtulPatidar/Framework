package com.framework.browsers;

import com.framework.common.ConfigProperties;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import java.net.URL;

public abstract class DriverFactory {
    public static ConfigProperties configProperties=new ConfigProperties();
    boolean isRemote = configProperties.isRemote();
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
