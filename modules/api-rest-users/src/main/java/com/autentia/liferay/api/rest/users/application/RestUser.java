package com.autentia.liferay.api.rest.users.application;

import com.liferay.portal.kernel.model.User;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RestUser {

    private String name;
    private String lastname;

    public RestUser() {
    }

    public RestUser(User user) {
        name = user.getFirstName();
        lastname = user.getLastName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}
