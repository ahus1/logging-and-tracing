package de.ahus1.tracing.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.instrument.web.TraceFilter;
import org.springframework.cloud.sleuth.instrument.web.TraceRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Request filter for setting an attribute for the trace filter.
 *
 * @author Alexander Schwartz 2016
 */
@Provider
public class SpanRequestFilter implements ContainerRequestFilter {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private Tracer tracer;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        Span span = (Span) request.getAttribute(TraceFilter.class.getName() + ".TRACE");
        if (span != null) {
            span = tracer.createSpan(request.getServletPath() + request.getPathInfo(), span);
            request.setAttribute(TraceRequestAttributes.HANDLED_SPAN_REQUEST_ATTR,
                    span);
        }
    }
}
