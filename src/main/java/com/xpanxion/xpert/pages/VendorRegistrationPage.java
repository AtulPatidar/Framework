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
public class VendorRegistrationPage extends WebPageBase {

    By inputName = By.cssSelector("vendor_name");
    By inputRestaurantName = By.cssSelector("kitchen_name");
    By inputAboutYourBusiness = By.cssSelector("description");
    By buttonCusineServed = By.cssSelector(".faasos-arrow-right.right");
    By inputAddress = By.cssSelector("address");
    By selectCity = By.cssSelector("city_id");
    By inputPhoneNumber = By.cssSelector("phone_number");
    By inputEmailID = By.cssSelector("email_id");
    By inputComment = By.cssSelector("comments");
    
    public void fillVendorRegistrationForm() {
        
    }
    
}
