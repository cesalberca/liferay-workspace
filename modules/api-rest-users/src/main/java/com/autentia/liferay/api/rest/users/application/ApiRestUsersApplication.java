package com.autentia.liferay.api.rest.users.application;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.persistence.UserUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

@ApplicationPath("/users")
@Component(immediate = true, service = Application.class)
public class ApiRestUsersApplication extends Application {

    private static final Log log = LogFactoryUtil.getLog(ApiRestUsersApplication.class);

    @Reference
    private volatile UserLocalService userLocalService;

    @Override
    public Set<Object> getSingletons() {
        super.getSingletons();
        return Collections.singleton(this);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") Long id) {
        try {
            // 20164L
            final User user = userLocalService.getUser(id);
            final String json = JSONFactoryUtil.looseSerialize(user);
            return Response.status(Status.OK)
                    .entity(json)
                    .build();
        } catch (PortalException e) {
            log.info(e.toString());
            return Response.status(404)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        final List<User> users = userLocalService.getUsers(-1, -1);
        final String json = JSONFactoryUtil.looseSerialize(users);
        return Response.status(200)
                .entity(json)
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response postUser(@QueryParam("email") String email) throws NoSuchAlgorithmException {
        log.info("Made post request");

        try {
            userLocalService.deleteUser(31783L);
        } catch (PortalException e) {
            log.info(e.getMessage());
        }

        final User user = createTestUser();
        userLocalService.addUser(user);

        return Response.status(Status.CREATED)
                .entity(JSONFactoryUtil.looseSerialize(user))
                .build();
    }

    private User createTestUser() {
        final String random;

        try {
            random = Integer.toString(SecureRandom.getInstance("SHA1PRNG").nextInt());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        final long userId = CounterLocalServiceUtil.increment();

        final User user = UserUtil.create(userId);
        user.setFirstName("César" + random);
        user.setLastName("Alberca" + random);
        user.setContactId(20165);
        user.setEmailAddress(random + "@test.com");
        user.setPassword("test");
        user.setScreenName("cesalberca" + random);
        user.setCreateDate(new Date());
        user.setGreeting("Hi CESAR");
        user.setJobTitle("Dev");
        user.setLanguageId("es");
        user.setMiddleName("Manuel" + random);

        return user;
    }
}
