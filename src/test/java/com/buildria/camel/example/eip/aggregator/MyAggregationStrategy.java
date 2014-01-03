package com.buildria.camel.example.eip.aggregator;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class MyAggregationStrategy implements AggregationStrategy {

    /**
     * <p>メッセージを集約します。</p>
     * 
     * 取得したString型のメッセージを<code>List&lt;String&gt;</code>に追加します。
     * 
     * <code>newExchange</code>は、これから集約するExchange、
     * <code>oldExchange</code>は、集約識別単位で集約中のExchangeです。
     * この2つのExchangeから集約後のExchangeを生成し返します。
     * 
     * ある集約識別子単位で初めての<code>neExchange</code>を受け取った場合、
     * <code>oldExchange</code>は<code>null</code>です。
     * 
     * @param oldExchange 集約後のExchange
     * @param newExchange 集約するExchange
     * @return 集約したExchange
     */
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        // 初めてのExchange
        if (oldExchange == null) {
            return newExchange;
        }

        // 集約中のCSVを取得
        String oldBody = oldExchange.getIn().getBody(String.class);
        // 新規に集約する文字列を習得
        String newBody = newExchange.getIn().getBody(String.class);
        // CSV形式の文字列を生成して再設定
        oldExchange.getIn().setBody(oldBody + "," + newBody);

        return oldExchange;
    }

}
