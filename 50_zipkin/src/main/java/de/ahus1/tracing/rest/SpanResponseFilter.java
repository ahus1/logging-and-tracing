package de.ahus1.tracing.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.instrument.web.TraceRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Response filter for setting an attribute for the trace filter.
 *
 * @author Alexander Schwartz 2016
 */
@Provider
public class SpanResponseFilter implements ContainerResponseFilter {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private Tracer tracer;

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        Span span = (Span) request.getAttribute(TraceRequestAttributes.HANDLED_SPAN_REQUEST_ATTR);
        if (span != null) {
            tracer.close(span);
        }
    }
}
