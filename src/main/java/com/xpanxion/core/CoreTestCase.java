package com.xpanxion.core;

import com.xpanxion.base.DriverFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.util.HashSet;
import java.util.Set;

public class CoreTestCase extends Assert {

    protected Set<WebDriver> drivers = new HashSet<>();
    private final static Logger LOG = Logger.getLogger(CoreTestCase.class);

    @BeforeMethod
    public void setup() {
        //String browser = System.getProperty("selenium.browser");
    }

    @DataProvider(name = "generic", parallel = true)
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
        DriverFactory.getDriverInstance().close();
    }

    public static Logger log() {
        return LOG;
    }
}
