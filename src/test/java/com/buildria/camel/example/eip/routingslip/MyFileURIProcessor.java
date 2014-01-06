package com.buildria.camel.example.eip.routingslip;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MyFileURIProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        // ヘッダ"pref"の値を取得
        String pref = exchange.getIn().getHeader("pref", String.class);
        //. ヘッダ"routingSlipHeader"にFileプロデューサのURIを設定
        exchange.getIn().setHeader("routingSlipHeader", "file:work/" + pref);
    }
}
