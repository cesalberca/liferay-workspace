package com.autentia.liferay.api.rest.users.application;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;


@Path("/users")
public class RestResource {

    private final RestUserService restUserService = RestUserService.getInstance();

    @GET
    public List<RestUser> getUsers() {
        return restUserService.getRestUsers();
    }

}
