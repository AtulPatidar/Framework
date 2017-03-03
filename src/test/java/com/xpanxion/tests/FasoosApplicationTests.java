package com.xpanxion.tests;

import com.xpanxion.core.CoreTestCase;
import com.xpanxion.xpert.common_functions.CommonWebFunctions;
import com.xpanxion.xpert.common_functions.IWebFunctions;
import com.xpanxion.xpert.data.VendorInfo;
import com.xpanxion.xpert.pages.HomePage;
import com.xpanxion.xpert.pages.VendorRegistrationPage;
import com.xpanxion.xpert.enums.VendorInfoEnums;
import org.testng.annotations.Test;

public class FasoosApplicationTests extends CoreTestCase {

    @Test
    public void testFasoosVendorRegistrationFlow() {
        IWebFunctions functions = new CommonWebFunctions(); //Replace with your implementation

        VendorInfo vendor = VendorInfoEnums.VALID_VENDOR_INFO.getData();
        HomePage homePage = functions.navigateToHomePage("https://www.faasos.com/");
        VendorRegistrationPage vendorRegistrationPage = functions.navigateToVendorRegistrationPage(homePage);

        vendorRegistrationPage = functions.fillOutTheVendorInformation(vendorRegistrationPage, vendor);
    }

}
