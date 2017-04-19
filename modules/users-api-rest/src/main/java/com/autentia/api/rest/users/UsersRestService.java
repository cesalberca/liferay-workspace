package com.autentia.api.rest.users;

import org.osgi.service.component.annotations.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Set;

@ApplicationPath("/users")
@Produces(MediaType.APPLICATION_JSON)
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
	public Response getUsers() {
		String json = "{\n" + " \"value\": \"ok\"\n" + "}";
		return Response
				.status(200)
				.entity(json)
				.build();
	}

	@POST
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("id") String id) {
		String json = "{\n" + " \"value\": \"" + id + "\"\n" + "}";
		return Response
				.status(201)
				.entity(json)
				.build();
	}
}