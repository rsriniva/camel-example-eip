package com.buildria.camel.example.eip.cbr;

import org.apache.camel.Exchange;

public class HeaderChecker {

    public boolean isTypeA(Exchange exchange) {
        String type = exchange.getIn().getHeader("type", String.class);
        return "A".equalsIgnoreCase(type);
    }
    
}
