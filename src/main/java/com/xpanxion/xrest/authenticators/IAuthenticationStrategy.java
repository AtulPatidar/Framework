package com.xpanxion.xrest.authenticators;

import com.jayway.restassured.specification.RequestSpecification;

public interface IAuthenticationStrategy {

    public RequestSpecification createRequest(RequestSpecification requestSpec);

    public void setAuthContext(AuthContext context);

}
