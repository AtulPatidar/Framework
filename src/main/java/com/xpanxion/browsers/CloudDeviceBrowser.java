package com.xpanxion.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.xpanxion.core.Configuration;

import java.net.URL;

public class CloudDeviceBrowser extends WebDriverFactory {

    private WebDriver driver;
    
//    private final String URL = "https://" + configProperties.getCloudUserName() + ":" + configProperties.getCloudAutomationKey() + "@hub-cloud.browserstack.com/wd/hub";
    private String browserName;
    private String platform;
//    private String device;

    @Override
    public WebDriver getDriver() {
    	Configuration config = Configuration.getInstance();
        try {

            String platform = System.getProperty("platform.name");
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browserName", config.getBrowserName());
            caps.setCapability("platform", platform);
            caps.setCapability("device", config.getDevice());
            caps.setCapability("browserstack.debug", "true");

            driver = new RemoteWebDriver(new URL(config.getHubUrl()), caps);
            driver = new Augmenter().augment(driver);

        } catch (Exception e) {
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

//    public String getDevice() {
//        return device;
//    }

//    public void setDevice(String device) {
//        this.device = device;
//    }

}
