package com.autentia.liferay.rest.payrolls.application;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.osgi.service.component.annotations.Component;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.POST;
import javax.ws.rs.core.Application;
import java.io.InputStream;
import java.util.Collections;
import java.util.Set;

@ApplicationPath("/payrolls")
@Component(immediate = true, service = Application.class)
public class PayrollsApplication extends Application {

    private static final Log log = LogFactoryUtil.getLog(PayrollsApplication.class);

    @Override
    public Set<Object> getSingletons() {
        return Collections.singleton(this);
    }

    @POST
    public String postFile(@FormDataParam("file") InputStream file) {
        log.info(file.toString());
        return "It works!";
    }
}