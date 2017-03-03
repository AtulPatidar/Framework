/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xpanxion.pages;

import com.xpanxion.base.WebPageBase;
import org.openqa.selenium.By;

/**
 *
 * @author xpanxion
 */
public class FSHomePage extends WebPageBase{
     
    By linkPartnerWithUs = By.cssSelector("a[href*='register']");
    
    public void gotoPartnerWithUsPage() {
        clickButton(waitForElement(linkPartnerWithUs));
        
    }
    
}
