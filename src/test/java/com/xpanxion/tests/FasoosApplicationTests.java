package com.xpanxion.tests;

import com.xpanxion.xpert.common_functions.IWebFunctions;
import org.testng.annotations.Test;

public class FasoosApplicationTests {

    @Test
    public void testFasoosVendorRegistrationFlow() {
        IWebFunctions functions = null; //Replace with your implementation
        
        functions.fillOutTheVendorInformation(this, vendor);
    }
}
