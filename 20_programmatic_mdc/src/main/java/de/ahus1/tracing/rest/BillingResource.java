package de.ahus1.tracing.rest;

import de.ahus1.tracing.domain.Invoice;
import de.ahus1.tracing.domain.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.CloseableThreadContext;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author Alexander Schwartz 2016
 */
@Path("/")
@Component
@RequiredArgsConstructor
public class BillingResource {

    private static final String INVOICE_ID = "invoiceId";
    private final InvoiceRepository repository;

    @GET
    @Path("startBillingRun")
    @Produces("text/plain")
    public String startBillingRun() {
        try {
            for (Invoice i : repository.findAll()) {
                MDC.put(INVOICE_ID, Long.toString(i.getId()));
                try {
                    i.calculateTotal();
                } finally {
                    MDC.remove(INVOICE_ID);
                }

                ///////////////////////////////

                try (final CloseableThreadContext.Instance c
                             = CloseableThreadContext.put(INVOICE_ID, String.valueOf(i.getId()))) {
                    i.calculateTotal();
                }

            }
            return "OK";
        } catch (Exception e) {
            return "Error occured, please look at log";
        }
    }

}
