package com.xpanxion.tests;

import com.xpanxion.base.DriverFactory;
import org.testng.annotations.Test;

import com.xpanxion.core.CoreTestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SampleSeleniumSearchTest extends CoreTestCase {

    @Test
    public void testGoogleSearch() {
        String name = "Rajesh";
        String company = "Xpanxion";
        WebDriver driver = DriverFactory.getDriverInstance();

        driver.get("http://www.google.com");
        driver.findElement(By.id("lst-ib")).sendKeys(name + " from " + company);
        driver.findElement(By.name("btnG1")).click();
    }

}
