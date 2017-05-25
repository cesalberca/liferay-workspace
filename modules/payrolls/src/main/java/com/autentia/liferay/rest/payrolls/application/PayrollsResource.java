package com.autentia.liferay.rest.payrolls.application;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Component;

import javax.ws.rs.GET;

@Component(immediate = true, service = PayrollsResource.class)
public class PayrollsResource {

    private static final Log log = LogFactoryUtil.getLog(PayrollsApplication.class);

    @GET
    public String getPayroll() {
        return "It works!";
    }

//    @POST
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    public String postFile(@FormDataParam("file") InputStream file) {
//        log.info(file.toString());
//        return "It works!";
//    }

}
