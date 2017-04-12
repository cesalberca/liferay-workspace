package com.autentia.api.rest.users;

import com.liferay.portal.kernel.service.UserLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Set;

@ApplicationPath("/users")
@Component(
		immediate = true, property = {"jaxrs.application=true"},
		service = Application.class
)
public class UsersRestService extends Application {

	@Override
	public Set<Object> getSingletons() {
		return Collections.singleton(this);
	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers() {
		String json = "{\n" + "  \"value\": \"cool\"\n" + "}";
		return Response
				.status(200)
				.header("Access-Control-Allow-Origin", "*")
				.entity(json)
				.build();
	}

	@Reference
	private volatile UserLocalService _userLocalService;

}