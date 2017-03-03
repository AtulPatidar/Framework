/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xpanxion.xpert.common_functions;

import com.xpanxion.base.DriverFactory;
import com.xpanxion.base.WebPageBase;
import com.xpanxion.xpert.pages.HomePage;
import com.xpanxion.xpert.pages.VendorRegistrationPage;

/**
 *
 * @author xpanxion
 */
public class CommmonWebFunction implements IWebFunctions{

    @Override
    public HomePage navigateToHomePage(String url) {
        DriverFactory.getDriverInstance().get(url);
        HomePage page = new HomePage();
        return page;
    }

    @Override
    public VendorRegistrationPage navigateToVendorRegistrationPage(HomePage page) {
        return page.gotoPartnerWithUsPage();
    }

    @Override
    public <T extends WebPageBase> T fillOutTheVendorInformation(VendorRegistrationPage page, VendorInfo vendor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
