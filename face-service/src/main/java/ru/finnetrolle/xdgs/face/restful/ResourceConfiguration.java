package ru.finnetrolle.xdgs.face.restful;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.stereotype.Component;

/**
 * Created by finnetrolle on 13.02.2016.
 */

@Component
public class ResourceConfiguration extends ResourceConfig {

    public ResourceConfiguration() {
        packages(ResourceConfiguration.class.getPackage().getName());
        register(JacksonJaxbJsonProvider.class);
        property(ServerProperties.WADL_FEATURE_DISABLE, Boolean.TRUE);
        property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, Boolean.TRUE);
    }

}
