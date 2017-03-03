package com.xpanxion.xperts.enums;

import com.xpanxion.xpert.data.VendorInfo;
import com.xpanxion.xpert.generators.StringRegex;

public enum VendorInfoEnums implements IDataInfo<VendorInfo> {
    VALID_VENDOR_INFO {
        @Override
        public VendorInfo getData() {
            VendorInfo vendor = new VendorInfo();
            StringRegex regex = new StringRegex();
            vendor.setName(regex.stringFromRegex("[A-Z][a-z]{8} [A-Z][a-z]{8}"));
            vendor.setRestaurantKitchenName(regex.stringFromRegex("[A-Z][a-z]{10}"));
            vendor.setCity("Pune");
            vendor.setComment(regex.stringFromRegex("[A-Za-z]{6}"));
            vendor.setEmail(regex.stringFromRegex("[a-z]{10}@[a-z]{5}.com"));
            vendor.setPhoneNumber(regex.stringFromRegex("[0-9]{10}"));
            return vendor;
        }

    }
}
