package de.ahus1.tracing.rest;

import de.ahus1.tracing.domain.Invoice;
import de.ahus1.tracing.domain.InvoiceRepository;
import lombok.RequiredArgsConstructor;
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

    private final InvoiceRepository invoiceRepository;

    @GET
    @Path("startBillingRun")
    @Produces("text/plain")
    public String startBillingRun() {
        try {
            for (Invoice i : invoiceRepository.findAll()) {
                i.calculateTotal();
            }
            return "OK";
        } catch (Exception e) {
            return "Error occured, please look at log";
        }
    }

}
