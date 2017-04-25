package com.autentia.liferay.api.rest.users.application;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Set;

@ApplicationPath("/users")
@Component(immediate = true, service = Application.class)
public class ApiRestUsersApplication extends Application {

	private static Log _log = LogFactoryUtil.getLog(ApiRestUsersApplication.class);

	@Reference
	private volatile UserLocalService userLocalService;

	public Set<Object> getSingletons() {
		return Collections.singleton(this);
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("id") Long id) {
		try {
			// 20164L
			User user = userLocalService.getUser(id);
			String json = JSONFactoryUtil.looseSerialize(user);
			return Response.status(200)
					.entity(json)
					.build();
		} catch (PortalException e) {
			_log.info(e.toString());
			throw new NotFoundException();
		}
	}
}
