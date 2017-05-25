package com.autentia.liferay.rest.payrolls.application;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/payrolls")
@Component(immediate = true, service = Application.class)
public class PayrollsApplication extends Application {

    @Reference
    private PayrollsResource payrollsResource;

    @Override
    public Set<Object> getSingletons() {
        super.getSingletons();
        return new HashSet<>(Collections.singletonList(payrollsResource));
    }

}