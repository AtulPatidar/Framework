package com.xpanxion.base;

import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class MobilePagebase extends WebPageBase {

	public String getText(By locator) {
		return waitForElement(locator).getText();
	}

	public void tapElement(By locator) {
		waitForElement(locator).click();
	}

	@SuppressWarnings("unchecked")
	public void scrollTo(By locator) {
        Dimension size = this.driver.manage().window().getSize();
        int loop = 0;
        int height = size.getHeight();
        int width = size.getWidth();
        
        while(!this.isEnabled(locator) && loop!=15) {
        	if(driver instanceof AppiumDriver<?>){
        		((AppiumDriver<MobileElement>)this.driver).swipe(height - (height/4),  width/2, height - (height/4 * 3), width/2, 500);
        	}
        	loop++; 
        }
	}
	
	public boolean isEnabled(By locator){
		try {
			return this.driver.findElement(locator).isEnabled();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
