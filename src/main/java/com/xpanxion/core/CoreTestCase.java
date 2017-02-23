package com.xpanxion.core;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

public class CoreTestCase extends Assert {

    protected Set<WebDriver> drivers = new HashSet<>();
    private final static Logger LOG = Logger.getLogger(CoreTestCase.class);

    @BeforeMethod
    public void setup() {
        //String browser = System.getProperty("selenium.browser");
    }

    @DataProvider(name = "generic")
    public static Object[][] getData() {
        BrowserTypes[][] browserTypes = new BrowserTypes[BrowserTypes.values().length][1];
        int i = 0;
        for (BrowserTypes type : BrowserTypes.values()) {
            browserTypes[i][0] = type;
            i++;
        }
        return browserTypes;
    }

    @AfterMethod
    public void tearDown() {
        //driver.close();
    }

    public static Logger log() {
        return LOG;
    }
}
