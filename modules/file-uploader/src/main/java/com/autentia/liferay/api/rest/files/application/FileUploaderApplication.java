package com.autentia.liferay.api.rest.files.application;

import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;
import java.io.*;
import java.util.Collections;
import java.util.Set;
import java.util.regex.Pattern;

@ApplicationPath("/files")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component(immediate = true, service = Application.class)
public class FileUploaderApplication extends Application {

    public static final String UPLOAD_FILE_SERVER = "/Users/calberca/Desktop";
    private static final Pattern COMPILE = Pattern.compile("\"");

    @Reference
    private volatile DLFileEntryLocalService dlFileEntryLocalService;

    private static final Log log = LogFactoryUtil.getLog(FileUploaderApplication.class);

    @Override
    public Set<Object> getSingletons() {
        super.getSingletons();
        return Collections.singleton(this);
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response postFile(Iterable<Attachment> attachments, HttpServletRequest request, @QueryParam("title") String title, @QueryParam("description") String description) {
        for (Attachment attachment : attachments) {
            final DataHandler dataHandler = attachment.getDataHandler();

            try{
                final MultivaluedMap<String, String> multivaluedMap = attachment.getHeaders();
                final String fileName = getFileName(multivaluedMap);
                log.info(fileName);
                final InputStream inputStream = dataHandler.getInputStream();
                final File file = new File(UPLOAD_FILE_SERVER + fileName);
                writeToFileServer(inputStream, file, fileName, "Description");
            } catch(IOException e) {
                log.info(e);
            }
        }
        return Response.status(Status.CREATED).build();
    }

    private void writeToFileServer(InputStream inputStream, File file, String title, String description) {
        try {
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
        } catch (PortalException e) {
            log.info(e);
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.info(e);
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private String getFileName(MultivaluedMap<String, String> multivaluedMap) {
        final String[] contentDisposition = multivaluedMap.getFirst("Content-Disposition").split(";");
        for (String filename : contentDisposition) {

            if (filename.trim().startsWith("filename")) {
                final String[] name = filename.split("=");
                return COMPILE.matcher(name[1].trim()).replaceAll("");
            }
        }
        return "unknownFile";
    }
}