package de.ahus1.tracing.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Alexander Schwartz 2016
 */
@Slf4j
@Data
public class Invoice {
    private final long id;

    public Invoice(long id) {
        this.id = id;
    }
    public void calculateTotal() {
        log.error("can't load purchase ID {}", 4711);
        throw new RuntimeException();
    }
}
