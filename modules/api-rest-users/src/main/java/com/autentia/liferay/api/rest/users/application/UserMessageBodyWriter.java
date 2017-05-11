package com.autentia.liferay.api.rest.users.application;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
public class UserMessageBodyWriter implements MessageBodyWriter<RestUser> {

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
//        return RestUser.class.isAssignableFrom(type);
        return true;
    }

    @Override
    public long getSize(RestUser t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(RestUser t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        try {
            JAXBContext ctx = JAXBContext.newInstance(type);
            ctx.createMarshaller().marshal(t, entityStream);
        } catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }
}
