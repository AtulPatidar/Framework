/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xpanxion.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.xpanxion.base.DriverFactory;
import com.xpanxion.core.CoreTestCase;
import com.xpanxion.dataproviders.DataProviderLibrary;

/**
 *
 * @author xpanxion
 */
public class FasoosTest extends CoreTestCase {

    //@Test
    public void verifySearchItem() {
        WebDriver driver = DriverFactory.getDriverInstance();
        driver.get("https://order.faasos.io/");
    }

    @Test(dataProvider = DataProviderLibrary.DP_SEARCHFOODITEM, dataProviderClass = DataProviderLibrary.class)
    public void verifySearchItem(String searchItem) {
        WebDriver driver = DriverFactory.getDriverInstance();
        driver.get("https://order.faasos.io/");
        
    }

}
