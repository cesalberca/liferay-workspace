package com.autentia.liferay.rest.application;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/users") // Indica a JAX-RS el endpoint de nuestro recurso
@Component(immediate = true, service = Application.class) // Éstas son anotaciones de OSGi, que indican que nuestro módulo se tiene que cargar inmmediatamente e indica que es un servicio (?)
public class RestUsersApplication extends Application {

	@Reference // Inyectamos nuestro recurso mediante una anotación de OSGi
	private RestUserResource restUserResource;

	// Registramos nuestro servicio en OSGi
	@Override
	public Set<Object> getSingletons() {
		super.getSingletons();
		return new HashSet<>(Collections.singletonList(restUserResource));
	}

}
