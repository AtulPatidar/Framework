package com.xpanxion.xrest.core;

import com.jayway.restassured.response.Headers;
import com.xpanxion.xrest.response.ResponseProcessor;
import com.xpanxion.xrest.request.XObjectMapper;
import com.xpanxion.xrest.media.BasicMediaTypes;
import com.xpanxion.xrest.request.RequestProcessor;
import com.xpanxion.xrest.request.RequestConfigurator;
import com.jayway.restassured.specification.RequestSpecification;
import com.xpanxion.xrest.authenticators.IAuthenticationStrategy;

public class XRest {

    private final RequestSpecification requestSpec;
    private final RequestProcessor requestProcessor;
    private final ResponseProcessor responseProcessor;
    private final IAuthenticationStrategy strategy;
    private XObjectMapper mapper = new XObjectMapper();
    private Headers headers;

    public Headers getHeaders() {
        return headers;
    }

    public void setHeaders(Headers headers) {
        this.headers = headers;
    }

    public XRest(BasicMediaTypes mediaType, IAuthenticationStrategy strategy) {
        RequestConfigurator requestConfig = new RequestConfigurator(mapper, mediaType);
        this.requestSpec = requestConfig.getRequestSpec();
        this.requestProcessor = new RequestProcessor(mediaType, mapper);
        this.responseProcessor = new ResponseProcessor();
        this.strategy = strategy;
    }

    public XRest(BasicMediaTypes mediaType, IAuthenticationStrategy strategy, Headers headers) {
        RequestConfigurator requestConfig = new RequestConfigurator(mapper, mediaType, headers);
        this.requestSpec = requestConfig.getRequestSpec();
        this.requestProcessor = new RequestProcessor(mediaType, mapper);
        this.responseProcessor = new ResponseProcessor();
        this.strategy = strategy;
    }

    public RequestSpecification createRequest() {
        return this.strategy.createRequest(requestSpec);
    }

    public String serialize(Object obj) {
        return requestProcessor.serialize(obj);
    }

    public <T> T deserialize(Class<T> clazz, String text) {
        return (T) requestProcessor.deserialize(clazz, text);
    }

    public ResponseProcessor getResponseProcessor() {
        return this.responseProcessor;
    }

    public void setObjectMapper(XObjectMapper mapper) {
        this.mapper = mapper;
    }
}
