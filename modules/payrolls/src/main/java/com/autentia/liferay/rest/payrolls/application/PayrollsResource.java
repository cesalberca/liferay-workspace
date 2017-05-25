package com.autentia.liferay.rest.payrolls.application;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.osgi.service.component.annotations.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.io.InputStream;

@Component(immediate = true, service = PayrollsResource.class)
public class PayrollsResource {

    private static final Log log = LogFactoryUtil.getLog(PayrollsApplication.class);

    @GET
    public String getPayroll() {
        return "It works!";
    }

    @POST
    public String postFile(@FormDataParam("file") InputStream file) {
        log.info(file.toString());
        return "It works!";
    }

}
