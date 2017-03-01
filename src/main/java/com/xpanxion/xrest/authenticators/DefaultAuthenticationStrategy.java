package com.xpanxion.xrest.authenticators;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.specification.RequestSpecification;

public class DefaultAuthenticationStrategy implements IAuthenticationStrategy {

    private AuthContext context;

    public DefaultAuthenticationStrategy() {
    }

    @Override
    public RequestSpecification createRequest(RequestSpecification requestSpec) {
        return RestAssured.given(requestSpec).auth().none();
    }

    @Override
    public void setAuthContext(AuthContext context) {
        this.context = context;
    }
}
