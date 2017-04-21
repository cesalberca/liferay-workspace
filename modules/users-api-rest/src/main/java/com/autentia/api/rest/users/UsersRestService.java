package com.autentia.api.rest.users;

import com.google.common.collect.ImmutableSet;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
	private static Log _log = LogFactoryUtil.getLog(UsersRestService.class);

	@Override
	public Set<Object> getSingletons() {
		return Collections.singleton(this);
	}

	@GET
	public Response getUsers() {
		_log.info("User made a get request");
		String json = "{\n" + " \"value\": \"ok\"\n" + "}";
		ImmutableSet<String> COLOR_NAMES = ImmutableSet.of(
			"red",
			"orange"
		);
		_log.info("Size " + COLOR_NAMES.size());

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

//	@POST
//	@Path("/files")
//	@Produces(MediaType.MULTIPART_FORM_DATA)
//	public Response uploadFile() {
//		Attachment attachment = new Attachment();
//		String filename = attachment.getContentDisposition().getParameter("filename");
//		java.nio.file.Path path = Paths.get("/Users/calberca/Tmp" + filename);
//		Files.deleteIfExists(path);
//		InputStream in = attachment.getObject(InputStream.class);
//
//		Files.copy(in, path);
//
//		return Response
//				.status(203)
//				.entity("file created ")
//				.build();
//	}
}