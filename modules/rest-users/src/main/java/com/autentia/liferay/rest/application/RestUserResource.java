package com.autentia.liferay.rest.application;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@Component(immediate = true, service = RestUserResource.class) // Nuestro recurso es a su vez un componente de OSGi
public class RestUserResource {

    @Reference // Referencia al servicio de Liferay para usuarios
    private UserLocalService userLocalService;

    @GET // Anotación de JAX-RS que hace que cuando nos conectemos a /users por GET ejecutará este método
    public List<RestUser> getUsers() {
        final List<User> users = userLocalService.getUsers(-1, -1);
        return users.stream().map(RestUser::new).collect(Collectors.toList());
    }

    @GET
    @Path("{id}")
    public RestUser getUser(@PathParam("id") long id) throws PortalException {
        return new RestUser(userLocalService.getUserById(id));
    }

}