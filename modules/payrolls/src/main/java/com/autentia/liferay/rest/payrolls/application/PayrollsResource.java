package com.autentia.liferay.rest.payrolls.application;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.osgi.service.component.annotations.Component;

import javax.ws.rs.GET;

@Component(immediate = true, service = PayrollsResource.class)
public class PayrollsResource {

    private static final Log log = LogFactoryUtil.getLog(PayrollsApplication.class);

    @GET
    public void helloWorld(MultipartBody input) {
        log.info(input.toString());
    }

}
