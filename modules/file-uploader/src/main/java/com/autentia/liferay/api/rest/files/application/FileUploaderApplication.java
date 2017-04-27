package com.autentia.liferay.api.rest.files.application;

import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations .Reference;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ApplicationPath("/files")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component(immediate = true, service = Application.class)
public class FileUploaderApplication extends Application {

    @Reference
    private volatile DLFileEntryLocalService dlFileEntryLocalService;

    private static final Log log = LogFactoryUtil.getLog(FileUploaderApplication.class);

    public Set<Object> getSingletons() {
        return Collections.singleton(this);
    }

    @POST
    public String postFile() {
        try {
            File file = new File("/Users/calberca/Desktop/port_v2.png");
            try (InputStream in = new FileInputStream(file)) {
                final Map<String, DDMFormValues> fieldsMap = new HashMap<>();
                log.info(file.getName());
                log.info(file.getTotalSpace());

            dlFileEntryLocalService.addFileEntry(
                    20164,
                    20147,
                    0,
                    30302,
                    "test",
                    "ASCII",
                    "Test",
                    "Testing description",
                    "changelog",
                    0,
                    fieldsMap,
                    file,
                    in,
                    file.getTotalSpace(),
                    null
            );
                in.close();
            }
        } catch (PortalException e) {
            log.info("xx1 Portal Exception ");
        } catch (FileNotFoundException e) {
            log.info("xx2 FileNotFound Exception ");
        } catch (IOException e) {
            log.info("xx3 IO Exception");
        }
        return "ok";
    }
}