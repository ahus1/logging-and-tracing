package de.ahus1.tracing.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Schwartz 2016
 */
@Component
public class InvoiceRepository {
    public List<Invoice> findAll() {
        List<Invoice> result = new ArrayList<>();
        result.add(new Invoice(1));
        return result;
    }
}
