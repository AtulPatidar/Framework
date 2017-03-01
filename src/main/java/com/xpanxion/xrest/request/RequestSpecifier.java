package com.xpanxion.xrest.request;

import com.jayway.restassured.specification.RequestSpecification;

public class RequestSpecifier{

    private final RequestSpecification requestSpec;


    public RequestSpecifier(RequestSpecification requestSpec) {
        this.requestSpec = requestSpec;
    }
}
