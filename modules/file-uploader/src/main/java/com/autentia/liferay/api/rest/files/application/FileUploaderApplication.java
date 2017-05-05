package com.autentia.liferay.api.rest.files.application;

import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.io.*;
import java.util.Collections;
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
    public Response postFile(@QueryParam("title") String title, @QueryParam("description") String description) {
        final File file = new File("/Users/calberca/Desktop/port_v2.png");
        try (InputStream inputStream = new FileInputStream(file)) {
            log.info(file.getName());

            //https://web.liferay.com/community/forums/-/message_boards/message/17070585
            dlFileEntryLocalService.addFileEntry(
                    20164,
                    20147,
                    20147,
                    30302,
                    file.getName(),
                    MimeTypesUtil.getContentType(file),
                    title,
                    description,
                    null,
                    0,
                    null,
                    file,
                    inputStream,
                    file.length(),
                    new ServiceContext()
            );
            inputStream.close();
        } catch (FileNotFoundException e) {
            log.info("xxx1 " + e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        } catch (IOException e) {
            log.info("xxx2 " + e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        } catch (PortalException e) {
            log.info("xxx3 " + e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Status.CREATED).build();
    }
}