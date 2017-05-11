package com.autentia.liferay.api.rest.users.application;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@ApplicationPath("/users")
@Consumes(MediaType.APPLICATION_XML)
@Component(immediate = true, service = Application.class)
public class ApiRestUsersApplication extends Application {

    private final RestUserService restUserService = RestUserService.getInstance();

    @Override
    public Set<Object> getSingletons() {
        super.getSingletons();
        return Collections.singleton(this);
    }

    @GET
    public List<RestUser> getUsers() {
        return restUserService.getRestUsers();
    }

}
