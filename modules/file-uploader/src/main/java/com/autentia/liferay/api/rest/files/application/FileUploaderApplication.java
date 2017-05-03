package com.autentia.liferay.api.rest.files.application;

import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.dynamic.data.mapping.kernel.DDMForm;
import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

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

    @Override
    public Set<Object> getSingletons() {
        super.getSingletons();
        return Collections.singleton(this);
    }

    @POST
    public String postFile() {
        final File file = new File("/Users/calberca/Desktop/port_v2.png");
        try (InputStream in = new FileInputStream(file)) {
            final Map<String, DDMFormValues> fieldsMap = new HashMap<>();
            fieldsMap.put("test2", new DDMFormValues(new DDMForm()));
            log.info(file.getName());
            log.info(file.getTotalSpace());

            //https://web.liferay.com/community/forums/-/message_boards/message/17070585
            dlFileEntryLocalService.addFileEntry(
                    20164,
                    20147,
                    20147,
                    30302,
                    "test2",
                    "ASCII",
                    "Test 2",
                    "Testing description",
                    "changelog",
                    0,
                    fieldsMap,
                    file,
                    in,
                    file.getTotalSpace(),
                    new ServiceContext()
            );
            in.close();
        } catch (FileNotFoundException e) {
            log.info("xxx1 " + e);
        } catch (IOException e) {
            log.info("xxx2 " + e);
        } catch (PortalException e) {
            log.info("xxx3 " + e);
        }

        return "ok";
    }
}