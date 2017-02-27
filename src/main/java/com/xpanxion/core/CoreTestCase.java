package com.xpanxion.core;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.xpanxion.base.DriverFactory;

public class CoreTestCase {

    private final static Logger LOG = Logger.getLogger(CoreTestCase.class);

    @BeforeMethod
    public void setup() {
        //String browser = System.getProperty("selenium.browser");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.getDriverInstance().quit();
    }

    public static Logger log() {
        return LOG;
    }
}
