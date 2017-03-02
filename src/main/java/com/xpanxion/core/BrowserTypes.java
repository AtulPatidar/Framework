package com.xpanxion.core;

import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

public enum BrowserTypes implements DriverInstance<WebDriver> {
    CHROME {
        @Override
        public WebDriver getDriverInstance() {
            String chromePath = "C:\\TestDrivers\\chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", chromePath);
            DesiredCapabilities capabilities;
            capabilities = DesiredCapabilities.chrome();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("test-type", "start-maximized", "no-default-browser-check", "--disable-extensions");
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            capabilities.setPlatform(Platform.ANY);
            WebDriver webDriver;
            if (Configuration.getInstance().isRemote()) {
                webDriver = new RemoteWebDriver(capabilities);
                Reporter.log("Running test on Grid, in browser 'CHROME' ", true);
            } else {
                webDriver = new ChromeDriver(capabilities);
                Reporter.log("Running test in browser 'CHROME'", true);
            }

            return webDriver;
        }
    }, FIREFOX {
        @Override
        public WebDriver getDriverInstance() {
            String firefoxPath = "C:\\TestDrivers\\geckodriver.exe";
            System.setProperty("webdriver.gecko.driver", firefoxPath);
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setCapability("marionette", true);
            WebDriver driver = null;

            if (Configuration.getInstance().isRemote()) {
                driver = new RemoteWebDriver(capabilities);
                Reporter.log("Running test on Grid, in browser 'Firefox'", true);
            } else {
            	driver = new FirefoxDriver();
                Reporter.log("Running test in browser 'FIREFOX'", true);
            }

            return driver;
        }

    }, IE {
        @Override
        public WebDriver getDriverInstance() {
            WebDriver driver = null;
//            System.setProperty("webdriver.edge.driver", "C:\\TestDrivers\\MicrosoftWebDriver.exe");
//            WebDriver driver = new EdgeDriver();
            System.setProperty("webdriver.ie.driver", "C:/TestDrivers/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
            driver.manage().window().maximize();
            return driver;
        }

    };

    private static WebDriver launchGridDriver(Capabilities capabilities, String url) {
        try {
            return new RemoteWebDriver(new URL(url), capabilities);
        } catch (Exception e) {
            Reporter.log("There was an error setting up the remote WebDriver.");
            e.printStackTrace();
            return null;
        }
    }

}
