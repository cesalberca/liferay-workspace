package com.autentia.liferay.api.rest.users.application;

import org.osgi.service.component.annotations.Component;

import javax.ws.rs.GET;
import java.util.List;

@Component(immediate = true, service = RestUserResource.class)
public class RestUserResource {

    private final RestUserService restUserService = RestUserService.getInstance();

    @GET
    public List<RestUser> getUsers() {
        return restUserService.getRestUsers();
    }

}
