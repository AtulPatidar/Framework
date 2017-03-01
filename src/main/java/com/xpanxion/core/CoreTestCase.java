package com.xpanxion.core;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.xpanxion.base.DriverFactory;

public class CoreTestCase {

    private final static Logger LOG = Logger.getLogger(CoreTestCase.class);

    @BeforeMethod
    public void setup(Object[] testArgs) {
        Configuration config = new Configuration();
        if (testArgs != null && testArgs.length > 0 && BrowserTypes.class.isAssignableFrom(testArgs[0].getClass())) {
        } else {
            if (config.getTestType().equalsIgnoreCase(TestTypes.WEB.name())) {
                if (!config.getBrowsers().contains(",")) {
                    if (config.getBrowsers().equalsIgnoreCase("ALL")) {
                        DriverFactory.registerInstance(BrowserTypes.values()[0].getDriverInstance());
                    } else {
                        DriverFactory.registerInstance(BrowserTypes.valueOf(config.getBrowsers()).getDriverInstance());
                    }
                } else {
                    DriverFactory.registerInstance(BrowserTypes.valueOf(config.getBrowsers().split(",")[0]).getDriverInstance());
                }
            }
            //String browser = System.getProperty("selenium.browser");
        }
    }

    @AfterMethod
    public void tearDown() {
        if (DriverFactory.getDriverInstance() == null) {
        } else {
            DriverFactory.getDriverInstance().quit();
        }
    }

    public static Logger log() {
        return LOG;
    }
}
