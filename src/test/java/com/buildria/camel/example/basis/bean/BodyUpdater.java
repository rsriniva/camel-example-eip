package com.buildria.camel.example.basis.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BodyUpdater {

    private static final Logger LOG = LoggerFactory.getLogger(BodyUpdater.class);
    
    public String toUpperCase(String body) {
        LOG.debug("BodyUpdater#toUpperCase called. body = [{}]", body);
        if (body == null) {
            return null;
        }
        return body.toUpperCase();
    }
    
}
