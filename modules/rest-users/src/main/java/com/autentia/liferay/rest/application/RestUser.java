package com.autentia.liferay.rest.application;

import com.liferay.portal.kernel.model.User;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement // Permite parsear nuestra entidad a XML o JSON según el Content-Type de la petición
public class RestUser {

    private String name;
    private String lastname;

    // Importante tener un constructor público vacío para que CXF Apache pueda hacer las intancias correctamente
    public RestUser() {
    }

    RestUser(User user) {
        name = user.getFirstName();
        lastname = user.getLastName();
    }

    // Los getters y setters deberán ser públicos
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
