package com.autentia.liferay.rest.payrolls.application;

import com.google.common.collect.ImmutableSet;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Component;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.POST;
import javax.ws.rs.core.Application;
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
    public String postFile() {
//        try (InputStream inputStream = file.getObject(InputStream.class)) {
//            log.info(inputStream.toString());
//            return "It works!";
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "It doesn't work";
//        }
        final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of(
                "red",
                "orange",
                "yellow",
                "green",
                "blue",
                "purple"
        );
    return String.valueOf(COLOR_NAMES.size());
    }
}