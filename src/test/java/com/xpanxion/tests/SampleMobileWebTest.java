package com.xpanxion.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.xpanxion.base.DriverFactory;
import com.xpanxion.core.CoreTestCase;
import com.xpanxion.core.MobileTypes;
import com.xpanxion.dataproviders.DataProviderLibrary;

public class SampleMobileWebTest extends CoreTestCase {

    @Test(dataProvider = DataProviderLibrary.DP_GENERIC_MOBILE_BROWSERS, dataProviderClass = DataProviderLibrary.class)
    public void testPageLaunch(MobileTypes types) {
        DriverFactory.registerInstance(types.getDriverInstance());
        WebDriver driver = DriverFactory.getDriverInstance();
        driver.get("http://google.com");
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url,"http://invalid_url.com","Failing Test case");
       
    }

}
