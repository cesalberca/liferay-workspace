package com.autentia.liferay.api.rest.files.application;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.Set;

@ApplicationPath("/files")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component(immediate = true, service = Application.class)
public class FileUploaderApplication extends Application {

    private static final Log log = LogFactoryUtil.getLog(FileUploaderApplication.class);

    @Reference
    private volatile DLAppServiceUtil dlAppServiceUtil;

    public Set<Object> getSingletons() {
        return Collections.singleton(this);
    }

    @POST
    public String postFile() {
        try {
            DLAppServiceUtil.addFileEntry(0, 0, "Testing", "ASCII", "Test", "Testing description", null, "LOL".getBytes(), null);
        } catch (PortalException e) {
            log.info(e.getMessage());
        }
        return "Test";
    }

    @GET
    public String test() {
        return "test";
    }
}