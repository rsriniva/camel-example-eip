package com.buildria.camel.example.eip.routingslip;

import org.apache.camel.Exchange;

public class MyFileURICreator {

    public String create(Exchange exchange) throws Exception {
        // ヘッダ"pref"の値を取得
        String pref = exchange.getIn().getHeader("pref", String.class);
        // FileプロデューサのURIを生成
       return "file:target/work/" + pref;
    }
}
