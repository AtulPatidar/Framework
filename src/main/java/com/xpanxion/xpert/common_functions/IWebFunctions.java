package com.xpanxion.xpert.common_functions;

import com.xpanxion.base.WebPageBase;
import com.xpanxion.xpert.pages.HomePage;
import com.xpanxion.xpert.pages.VendorRegistrationPage;
import com.xpanxion.xpert.data.VendorInfo;

public interface IWebFunctions {

    public HomePage navigateToHomePage(String url);

    public VendorRegistrationPage navigateToVendorRegistrationPage(HomePage page);

    public <T extends WebPageBase> T fillOutTheVendorInformation(VendorRegistrationPage page, VendorInfo vendor);
}
