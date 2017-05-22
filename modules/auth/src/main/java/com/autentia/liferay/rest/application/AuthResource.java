package com.autentia.liferay.rest.application;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.GET;

@Component(immediate = true, service = AuthResource.class)
public class AuthResource {

    @Reference
    private AuthService authService;

    @GET
    public String getToken() {
        return authService.getToken();
    }

}
