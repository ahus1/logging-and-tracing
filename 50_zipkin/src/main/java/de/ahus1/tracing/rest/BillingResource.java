package de.ahus1.tracing.rest;

import de.ahus1.tracing.domain.Invoice;
import de.ahus1.tracing.domain.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author Alexander Schwartz 2016
 */
@Path("/")
@Slf4j
@Component
@RequiredArgsConstructor
public class BillingResource {

    private static final String INVOICE_ID = "invoiceId";

    private final RestTemplate restTemplate;
    private final InvoiceRepository repository;
    private final Tracer tracer;

    @GET
    @Path("startBillingRun")
    @Produces("text/plain")
    public String startBillingRun() {
        String callback = restTemplate.getForEntity("http://localhost:8081/api/callback", String.class).getBody();
        Span span = tracer.getCurrentSpan();
        try {
            for (Invoice i : repository.findAll()) {
                MDC.put(INVOICE_ID, i.getId());
                try {
                    i.calculateTotal();
                } finally {
                    MDC.remove(INVOICE_ID);
                }
            }
            return "OK";
        } catch (Exception e) {
            span.logEvent("exception occured");
            tracer.addTag("tag", "value");
            return "Error occured, please look at log ("
                    + Span.idToHex(tracer.getCurrentSpan().getTraceId())
                    + ")";
        }
    }

    @GET
    @Path("callback")
    @Produces("text/plain")
    public String callback() {
        log.info("callback called");
        return "OK";
    }

}
