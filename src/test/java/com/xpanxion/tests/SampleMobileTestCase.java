package com.xpanxion.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.xpanxion.base.DriverFactory;
import com.xpanxion.core.CoreTestCase;
import com.xpanxion.core.MobileTypes;
import com.xpanxion.dataproviders.DataProviderLibrary;

public class SampleMobileTestCase extends CoreTestCase {

    @Test(dataProvider = DataProviderLibrary.DP_GENERIC_MOBILE_NATIVE, dataProviderClass = DataProviderLibrary.class)
    public void testAppLaunch(MobileTypes types) {
        DriverFactory.registerInstance(types.getDriverInstance());
        WebDriver driver = DriverFactory.getDriverInstance();
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String loc = driver.findElement(By.id("com.weather.Weather:id/location_actionbar_name")).getText();
        System.out.println(loc);
    }

}
