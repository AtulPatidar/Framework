package com.xpanxion.pages;

import org.openqa.selenium.By;

import com.xpanxion.base.WebPageBase;

public class AndroidHomeScreen extends WebPageBase {

	By locationTitle = By.id("com.weather.Weather:id/location_actionbar_name");
	By searchButton = By.name("topNav plus");
	By settingsButton = By.name("topNav settings"); 
	
	public String getLocationName() {
		return driver.findElement(locationTitle).getText();
	}

	public void tapSearch(){
    	driver.findElement(searchButton).click();
    }
	
	public void tapSettings(){
    	driver.findElement(settingsButton).click();
    }
}
