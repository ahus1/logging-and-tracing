package de.ahus1.tracing.rest;

import org.slf4j.MDC;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 * Response filter for logging. Will clean up after {@link RequestFilter}.
 *
 * @author Alexander Schwartz 2016
 */
@Provider
public class ResponseFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext containerRequestContext,
                       ContainerResponseContext containerResponseContext) {
        MDC.remove("http.url");
        MDC.remove("http.method");
        MDC.remove("user");
    }
}
