package com.xpanxion.xrest.request;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.EncoderConfig;
import com.jayway.restassured.config.ObjectMapperConfig;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.specification.RequestSpecification;
import com.xpanxion.xrest.media.BasicMediaTypes;

public class RequestConfigurator {

    private final RequestSpecification requestSpec;

    public RequestConfigurator(XObjectMapper mapper, BasicMediaTypes mediaType) {

        this.requestSpec = RestAssured.given()
                .config(createRestAssuredConfig(mapper))
                .relaxedHTTPSValidation()
                .header("User-Agent", "XRest From Xpanxion")
                .header("Accept", mediaType.getAccept())
                .header("Content-Type", mediaType.getContentType());
        RestAssured.defaultParser = Parser.TEXT;
    }
    
    public RequestConfigurator(XObjectMapper mapper, BasicMediaTypes mediaType, Headers headers) {

        this.requestSpec = RestAssured.given()
                .config(createRestAssuredConfig(mapper))
                .relaxedHTTPSValidation()
                .header("User-Agent", "XRest From Xpanxion")
                .header("Accept", mediaType.getAccept())
                .header("Content-Type", mediaType.getContentType())
                .headers(headers);
        RestAssured.defaultParser = Parser.TEXT;
    }

    public RequestSpecification getRequestSpec() {
        return this.requestSpec;
    }

    private RestAssuredConfig createRestAssuredConfig(XObjectMapper mapper) {
        ObjectMapperConfig omc = ObjectMapperConfig.objectMapperConfig().defaultObjectMapper(mapper);
        RestAssuredConfig restConfig = RestAssuredConfig.newConfig()
                .objectMapperConfig(omc)
                .encoderConfig(EncoderConfig.encoderConfig().defaultContentCharset("UTF-8"));
        return restConfig;
    }

}
