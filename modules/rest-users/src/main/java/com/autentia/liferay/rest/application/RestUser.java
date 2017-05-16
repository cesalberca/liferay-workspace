package com.autentia.liferay.rest.application;

import com.liferay.portal.kernel.model.User;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement // Permite parsear nuestra entidad a XML o JSON según el Accept de la petición
public class RestUser {

    private long id;
    private String firstname;
    private String lastname;

    // Importante tener un constructor público vacío para que CXF Apache pueda hacer las instancias correctamente
    public RestUser() {
    }

    RestUser(User user) {
        id = user.getUserId();
        firstname = user.getFirstName();
        lastname = user.getLastName();
    }

    // Los getters y setters deberán ser públicos
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}
