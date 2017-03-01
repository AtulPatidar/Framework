package com.xpanxion.xrest.request;

import com.jayway.restassured.mapper.DataToDeserialize;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class Deserializer implements DataToDeserialize {

    private final String text;

    public Deserializer(String text) {
        this.text = text;
    }

    @Override
    public String asString() {
        return this.text;
    }

    @Override
    public byte[] asByteArray() {
        try {
            return this.text.getBytes("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("UTF-8 isn't a supported encoding.", ex);
        }
    }

    @Override
    public InputStream asInputStream() {
        return new ByteArrayInputStream(asByteArray());
    }

}
