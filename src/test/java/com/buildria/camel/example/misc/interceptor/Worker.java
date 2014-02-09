package com.buildria.camel.example.misc.interceptor;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Worker {

    private final Logger log = LoggerFactory.getLogger(getClass());
    
    public void work(Exchange exchange) {
        log.debug("do something");
        exchange.getIn().setBody("done");
    }
}
