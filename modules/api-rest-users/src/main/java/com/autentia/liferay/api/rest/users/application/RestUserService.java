package com.autentia.liferay.api.rest.users.application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RestUserService {

    private static RestUserService ourInstance = new RestUserService();
    private final List<RestUser> restUsers = new ArrayList<>();

    public static RestUserService getInstance() {
        return ourInstance;
    }

    private RestUserService() {
        restUsers.add(new RestUser("César", "Alberca"));
        restUsers.add(new RestUser("Javier", "Sánchez"));
    }

    List<RestUser> getRestUsers() {
        return Collections.unmodifiableList(restUsers);
    }

}
