package com.xpanxion.tests;

import com.xpanxion.base.DriverFactory;
import com.xpanxion.core.BrowserTypes;
import org.testng.annotations.Test;

import com.xpanxion.core.CoreTestCase;
import com.xpanxion.dataproviders.DataProviderLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SampleMobileTestCase extends CoreTestCase {

    @Test(dataProvider = DataProviderLibrary.DP_SAMPLE_DATA, dataProviderClass = DataProviderLibrary.class)
    public void testGoogleSearch(BrowserTypes types, String name, String company) {
        DriverFactory.registerInstance(types.getDriverInstance());
        WebDriver driver = DriverFactory.getDriverInstance();

        driver.get("http://www.google.com");
        driver.findElement(By.id("lst-ib")).sendKeys(name + " from " + company);
        driver.findElement(By.name("btnG")).click();
    }

}
