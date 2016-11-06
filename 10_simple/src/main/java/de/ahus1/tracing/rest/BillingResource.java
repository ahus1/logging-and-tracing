package de.ahus1.tracing.rest;

import de.ahus1.tracing.domain.Invoice;
import de.ahus1.tracing.domain.InvoiceRespository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author Alexander Schwartz 2016
 */
@Path("/")
public class BillingResource {

    @Autowired
    private InvoiceRespository invoiceRespository;

    @GET
    @Path("startBillingRun")
    @Produces("text/plain")
    public String startBillingRun() {
        try {
            for (Invoice i : invoiceRespository.findAll()) {
                i.calculateTotal();
            }
            return "OK";
        } catch (Exception e) {
            return "Error occured, please look at log";
        }
    }

}
