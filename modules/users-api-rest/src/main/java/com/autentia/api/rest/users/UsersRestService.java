package com.autentia.api.rest.users;

import org.osgi.service.component.annotations.Component;

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
		String json = "{\n" + " \"value\": \"ok\"\n" + "}";
		return Response
				.status(200)
				.entity(json)
				.build();
	}
}