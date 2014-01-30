package com.buildria.camel.example.basis.bean;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BodyUpdateProcessor implements Processor {

   private static final Logger LOG = LoggerFactory.getLogger(BodyUpdateProcessor.class);
        
    @Override
    public void process(Exchange exchange) throws Exception {
        LOG.debug("BodyUpdateProcessor#process called.");
        if (exchange == null) {
            return;
        }    
        
        // BodyをStringで取得
        String body = exchange.getIn().getBody(String.class);
        if (body != null) {
            // Bodyの内容を大文字にして、再度Bodyに設定
            exchange.getIn().setBody(body.toUpperCase());
        }
    }
    
}
