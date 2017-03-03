package com.xpanxion.xpert.common_functions;

import com.xpanxion.base.WebPageBase;
import com.xpanxion.xpert.data.VendorInfo;

public class CommonWebFunctions implements IWebFunctions{

    @Override
    public HomePage navigateToHomePage(String url) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VendorRegistrationPage navigateToVendorRegistrationPage(HomePage page) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T extends WebPageBase> T fillOutTheVendorInformation(VendorRegistrationPage page, VendorInfo vendor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
