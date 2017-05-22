package com.autentia.liferay.rest.application;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@ApplicationPath("/token")
@Component(immediate = true, service = Application.class)
public class AuthApplication extends Application {

	@Reference
	private AuthResource authResource;

	@Override
	public Set<Object> getSingletons() {
		super.getSingletons();
		return new HashSet<>(Collections.singletonList(authResource));
	}

}