/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xpanxion.xpert.pages;

import com.xpanxion.base.WebPageBase;
import org.openqa.selenium.By;

/**
 *
 * @author xpanxion
 */
public class HomePage extends WebPageBase{
     
    By linkPartnerWithUs = By.cssSelector("a[href*='register']");
    
    public VendorRegistrationPage gotoPartnerWithUsPage() {
        clickButton(waitForElement(linkPartnerWithUs));
        return new VendorRegistrationPage();
        
    }
    
}
