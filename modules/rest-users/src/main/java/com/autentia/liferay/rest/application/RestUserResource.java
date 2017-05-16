package com.autentia.liferay.rest.application;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component(immediate = true, service = RestUserResource.class) // Nuestro recurso es a su vez un componente de OSGi
public class RestUserResource {

    private static final Log log = LogFactoryUtil.getLog(RestUserResource.class);

    @Reference // Referencia al servicio de Liferay para usuarios
    private UserLocalService userLocalService;

    @GET // Anotación de JAX-RS que hace que cuando nos conectemos a /users por GET ejecutará este método
    public List<RestUser> getUsers() {
        final List<User> users = userLocalService.getUsers(-1, -1);
        return users.stream().map(RestUser::new).collect(Collectors.toList());
    }

    @GET
    @Path("{id}")
    public RestUser getUser(@PathParam("id") long id) {
        try {
            return new RestUser(userLocalService.getUserById(id));
        } catch (PortalException e) {
            throw new NotFoundException(e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public RestUser postUser(RestUser restUser) {
        try {
            final User user = userLocalService.addUser(0, PortalUtil.getDefaultCompanyId(), false, "test", "test", false, "cesaralberca", "calberca@autentia.com", 0, "", Locale.ENGLISH, restUser.getFirstname(), "manuel", restUser.getLastname(), 0, 0, true, 1, 1, 1995, "", null, null, null, null, false, new ServiceContext());
            return new RestUser(user);
        } catch (PortalException e) {
            throw new InternalServerErrorException(e);
        }
    }

}