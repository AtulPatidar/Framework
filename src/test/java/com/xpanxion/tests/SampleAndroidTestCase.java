package com.xpanxion.tests;

import org.testng.annotations.Test;

import com.xpanxion.base.DriverFactory;
import com.xpanxion.core.CoreTestCase;
import com.xpanxion.core.MobileTypes;
import com.xpanxion.dataproviders.DataProviderLibrary;
import com.xpanxion.pages.IOSHomeScreen;

public class SampleAndroidTestCase extends CoreTestCase {

    @Test(dataProvider = DataProviderLibrary.DP_GENERIC_MOBILE_NATIVE, dataProviderClass = DataProviderLibrary.class)
    public void testAppLaunch(MobileTypes types) {
    	DriverFactory.registerInstance(types.getDriverInstance());
    	IOSHomeScreen sample = new IOSHomeScreen();
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String loc = sample.getLocationName();
        System.out.println(loc);
        sample.tapSearch();
        sample.tapSearch();
        sample.tapSettings();
        sample.tapSettings();
    }

}
