package de.ahus1.tracing.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Alexander Schwartz 2016
 */
@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("de.ahus1.tracing.rest");
    }
}
