package com.xpanxion.xrest.request;

import com.jayway.restassured.internal.mapping.ObjectMapperDeserializationContextImpl;
import com.jayway.restassured.internal.mapping.ObjectMapperSerializationContextImpl;
import com.jayway.restassured.mapper.DataToDeserialize;
import com.xpanxion.xrest.media.BasicMediaTypes;


public class RequestProcessor {

    private final BasicMediaTypes mediaType;
    private final XObjectMapper mapper;

    public RequestProcessor(BasicMediaTypes mediaType, XObjectMapper mapper) {
        this.mediaType = mediaType;
        this.mapper = mapper;
    }

    public String serialize(final Object obj) {
        ObjectMapperSerializationContextImpl omsc = new ObjectMapperSerializationContextImpl();
        omsc.setCharset("UTF-8");
        omsc.setContentType(this.mediaType.getContentType());
        omsc.setObject(obj);

        return this.mapper.serialize(omsc).toString();
    }

    @SuppressWarnings("unchecked")
	public <T> T deserialize(final Class<T> clazz, final String text) {
        DataToDeserialize dtd = new Deserializer(text);

        ObjectMapperDeserializationContextImpl omdc = new ObjectMapperDeserializationContextImpl();
        omdc.setCharset("UTF-8");
        omdc.setContentType(this.mediaType.getContentType());
        omdc.setType(clazz);
        omdc.setDataToDeserialize(dtd);

        return (T) this.mapper.deserialize(omdc);
    }

    public String getSerializationType() {
        return this.mediaType.getSerialType();
    }
}
