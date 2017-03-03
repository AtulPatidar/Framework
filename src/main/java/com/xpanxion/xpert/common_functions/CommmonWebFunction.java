package com.xpanxion.xpert.common_functions;

import com.xpanxion.base.DriverFactory;
import com.xpanxion.base.WebPageBase;
import com.xpanxion.xpert.data.VendorInfo;
import com.xpanxion.xpert.pages.HomePage;
import com.xpanxion.xpert.pages.VendorRegistrationPage;

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
