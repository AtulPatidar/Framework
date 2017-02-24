package com.xpanxion.base;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;

public class DriverFactory {

    private static final Map<String, WebDriver> drivers = new HashMap<>();

    public static WebDriver getDriverInstance() {
        return drivers.get(Thread.currentThread().getName());
    }

    public static void registerInstance(WebDriver driverInstance) {
        drivers.put(Thread.currentThread().getName(),driverInstance);
    }

}
