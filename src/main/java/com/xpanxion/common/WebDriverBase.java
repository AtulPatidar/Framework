package com.xpanxion.common;

import com.xpanxion.browsers.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;

public abstract class WebDriverBase {

    private BrowserTypes browserType;
    private WebDriver webDriver;

    private static HashMap<Long, WebDriver> map = new HashMap<Long, WebDriver>();
    private static HashMap<Long, BrowserTypes> browserMap = new HashMap<Long, BrowserTypes>();

    public static WebDriver getDriverInstance() {
        return map.get(Thread.currentThread().getId());
    }

    /**
     * Interacts with the browser map to return the browser type specific to the current thread.
     *
     * @return the browser enum specific to the currently running thread.
     */
    public static BrowserTypes getBrowserType() {
        return browserMap.get(Thread.currentThread().getId());
    }



    @BeforeMethod
    public WebDriver getWebDriver() {
        System.out.println("Running tests on thread: " + Thread.currentThread().getId());
        ConfigProperties configProperties=new ConfigProperties();
        browserType= configProperties.getBrowser();
        switch (browserType) {
            case FIREFOX:
                webDriver = new FirefoxWebDriver().getDriver();
                break;
            case CHROME:
                webDriver = new ChromeWebDriver().getDriver();
                break;
            case MOBILE:
                webDriver = new MobileEmulatorDriver().getDriver();
                break;
            case RESOLUTION_SCREEN:
                String width = System.getProperty("screen.width");
                String height = System.getProperty("screen.height");

                ResolutionBasedDriver resolutionDriver = new ResolutionBasedDriver();
                resolutionDriver.setWidth(Integer.parseInt(width));
                resolutionDriver.setHeight(Integer.parseInt(height));
                webDriver = resolutionDriver.getDriver();
                break;
            case CLOUD:
                webDriver = new CloudDeviceBrowser().getDriver();
                break;
            default:
                browserType=BrowserTypes.UNDEFINED;
                throw new RuntimeException("Browser type not supported");
        }

        webDriver.manage().deleteAllCookies();
        webDriver.manage().window().maximize();
        map.put(Thread.currentThread().getId(), webDriver);
        browserMap.put(Thread.currentThread().getId(), browserType);
        return webDriver;
    }


}
