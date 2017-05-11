package com.autentia.liferay.api.rest.users.application;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.GET;
import java.util.List;
import java.util.stream.Collectors;

@Component(immediate = true, service = RestUserResource.class)
public class RestUserResource {

    @Reference
    private UserLocalService userLocalService;

    @GET
    public List<RestUser> getUsers() {
        final List<User> users = userLocalService.getUsers(-1, -1);
        return users.stream().map(RestUser::toRestUser).collect(Collectors.toList());
    }

}
