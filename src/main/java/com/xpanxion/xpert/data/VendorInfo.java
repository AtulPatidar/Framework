package com.xpanxion.xpert.data;

import java.util.ArrayList;
import java.util.List;

public class VendorInfo {
    private String name;
    private String restaurantKitchenName;
    private String briefAboutBusiness;
    private final List<String> cuisinesServed = new ArrayList<>();
    private String address;
    private String city;
    private String phoneNumber;
    private String email;
    private String comment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestaurantKitchenName() {
        return restaurantKitchenName;
    }

    public void setRestaurantKitchenName(String restaurantKitchenName) {
        this.restaurantKitchenName = restaurantKitchenName;
    }

    public String getBriefAboutBusiness() {
        return briefAboutBusiness;
    }

    public void setBriefAboutBusiness(String briefAboutBusiness) {
        this.briefAboutBusiness = briefAboutBusiness;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
