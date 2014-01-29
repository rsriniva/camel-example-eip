package com.buildria.camel.example.eip.cbr;

import org.apache.camel.Exchange;

public class HeaderChecker {

    /**
     * ヘッダ"type"の値が"A"であるかチェックします。
     * 
     * @param exchange Exchange
     * @return  ヘッダ"type"の値が"A"の場合<code>true</code>
     */
    public boolean isTypeA(Exchange exchange) {
        String type = exchange.getIn().getHeader("type", String.class);
        return "A".equalsIgnoreCase(type);
    }
    
}
