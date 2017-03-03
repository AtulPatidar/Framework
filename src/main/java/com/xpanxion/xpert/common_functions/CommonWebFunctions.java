package com.xpanxion.xpert.common_functions;

import com.xpanxion.base.DriverFactory;
import com.xpanxion.base.WebPageBase;
import com.xpanxion.xpert.data.VendorInfo;
import com.xpanxion.xpert.pages.HomePage;
import com.xpanxion.xpert.pages.VendorRegistrationPage;
import java.util.ArrayList;

public class CommonWebFunctions implements IWebFunctions {

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
        return (T) page.enterName(vendor.getName())
                .enterAboutYourBusiness(vendor.getBriefAboutBusiness())
                .enterRestaurant(vendor.getRestaurantKitchenName())
                .enterAddress(vendor.getAddress())
                .selectCityByText(vendor.getCity())
                .enterPhoneNumber(vendor.getPhoneNumber())
                .enterEmailID(vendor.getEmail())
                .enterComment(vendor.getComment())
                .selectCusineServed(vendor.getCuisinesServed())
                .submit(page.getClass());
    }

}
