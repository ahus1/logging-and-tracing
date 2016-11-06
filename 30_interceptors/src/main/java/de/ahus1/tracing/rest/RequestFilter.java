package de.ahus1.tracing.rest;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;

/**
 * Request filter for logging. Sets logging context variables. These will be removed by the {@link ResponseFilter}.
 *
 * @author Alexander Schwartz 2016
 */
@Provider
public class RequestFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        MDC.put("http.url", containerRequestContext.getUriInfo().getRequestUri().toString());
        MDC.put("http.method", containerRequestContext.getMethod());
        Principal principal = containerRequestContext.getSecurityContext().getUserPrincipal();
        if(principal != null) {
            MDC.put("user", principal.getName());
        } else {
            MDC.put("user", "anonymous");
        }
    }
}
