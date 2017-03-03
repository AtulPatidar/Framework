package com.xpanxion.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.xpanxion.base.DriverFactory;
import com.xpanxion.base.WebPageBase;


public class MobileHomeScreen extends WebPageBase{
	
	By locationtitle = By.id("com.weather.Weather:id/location_actionbar_name");

    public String getLocationName() {
    	return driver.findElement(locationtitle).getText();
    }
    
}
