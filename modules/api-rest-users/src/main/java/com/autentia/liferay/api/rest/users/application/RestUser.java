package com.autentia.liferay.api.rest.users.application;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RestUser {
    private String name;
    private String lastname;

    RestUser(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
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
