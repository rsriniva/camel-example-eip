package com.buildria.camel.example.basis.bean;

import org.apache.camel.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChosingMethod {

    private final Logger log = LoggerFactory.getLogger(getClass());
    
    public void convertBody(String body) {
        log.debug("ChosingMethod#convertBody(String) called.");
    }

    public void convertBody(String body, @Header("type") String type) {
        log.debug("ChosingMethod#convertBody(String, String) called.");
    }
    
}
