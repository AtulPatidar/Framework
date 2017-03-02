/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xpanxion.tests;

import com.xpanxion.base.DriverFactory;
import com.xpanxion.core.BrowserTypes;
import static com.xpanxion.core.BrowserTypes.CHROME;
import com.xpanxion.core.CoreTestCase;
import com.xpanxion.dataproviders.DataProviderLibrary;
import javax.swing.JOptionPane;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 *
 * @author xpanxion
 */
public class SampleToolsQATest extends CoreTestCase{
    
    @Test
    public void verifyToolsQA_NoData() {
       WebDriver driver = DriverFactory.getDriverInstance();
       driver.get("https://mvnrepository.com/");
    }
    
     @Test(dataProvider = DataProviderLibrary.DP_GENERIC, dataProviderClass = DataProviderLibrary.class)
    public void verifyToolsQA_MultipleData(BrowserTypes type) {
       DriverFactory.registerInstance(type.getDriverInstance());
       WebDriver driver = DriverFactory.getDriverInstance();
       driver.get("https://mvnrepository.com/");
    }
    
    @Test(dataProvider = DataProviderLibrary.VERIFY_TABLE_DATA, dataProviderClass = DataProviderLibrary.class)
    public void verifyToolsQA(BrowserTypes type,String a, String b) {
        DriverFactory.registerInstance(type.getDriverInstance());
       WebDriver driver = DriverFactory.getDriverInstance();
       System.out.println(a);
       driver.get("https://mvnrepository.com/");
    }
    
}
