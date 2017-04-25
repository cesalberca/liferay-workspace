package com.autentia.liferay.api.rest.users.application;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.UserLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.Set;

@ApplicationPath("/users")
@Component(immediate = true, service = Application.class)
public class ApiRestUsersApplication extends Application {

	public Set<Object> getSingletons() {
		return Collections.singleton(this);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getUsers() {
		try {
			return userLocalService.getUser(20164L).toString();
		} catch (PortalException e) {
			return "";
		}
	}

	@Reference
	private volatile UserLocalService userLocalService;
}