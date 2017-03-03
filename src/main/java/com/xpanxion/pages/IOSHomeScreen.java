package com.xpanxion.pages;

import org.openqa.selenium.By;

import com.xpanxion.base.MobilePagebase;

public class IOSHomeScreen extends MobilePagebase {
	public static MobilePagebase mobile = new MobilePagebase();
	

	By locationTitle = By.id("DAPURI, INDIA");
	By searchButton = By.id("topNav plus");
	By settingsButton = By.id("topNav settings");
	By outdoorLabel = By.id("OUTDOORS");
	
	
	public String getLocationName() {
		return mobile.getText(locationTitle);
	}

	public void tapSearch(){
		mobile.tapElement(searchButton);
    }
	
	public void tapSettings(){
		mobile.tapElement(settingsButton);
    }
	
	public void scrollToOutdoor() {
		mobile.scrollTo(outdoorLabel);
	}
}
