package com.xpanxion.xrest.response;

import com.jayway.restassured.response.Response;

public class ResponseProcessor {

    private final ThreadLocal<Response> lastResponse = new ThreadLocal<Response>();

    public <T> T getResultAfterVerifyingStatusCode(Response response, Class<T> resultType, int acceptableCode) {
        int status = response.statusCode();
        if (status != acceptableCode) {
            throw new RuntimeException("Error parsing the object: " + resultType.getSimpleName() + ". Expected Code: " + acceptableCode + " but got: " + status + ".");
        }

        return (T) response.as(resultType);
    }

    public Response lastResponseForThread() {
        return lastResponse.get();
    }

    public Response setLastResponseForThread(Response resp) {
        lastResponse.set(resp);
        return resp;
    }
}
