package com.autentia.liferay.rest.application;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = AuthService.class)
public class AuthService {

    public String getToken() {
        return "token";
    }

}
