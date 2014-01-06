package com.buildria.camel.example.eip.aggregator;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class MyPredicateAggregationStrategy implements AggregationStrategy {

    /**
     * <p>メッセージを集約します。</p>
     * 
     * 取得したString型のメッセージを<code>List&lt;String&gt;</code>に追加します。
     * 取得したString型のメッセージに"END"が含まれている場合、
     * ヘッダ<code>completion</code>に<code>true</code>を設定します。
     * 
     * <code>newExchange</code>は、これから集約するExchange、
     * <code>oldExchange</code>は、集約識別単位で集約中のExchangeです。
     * この2つのExchangeから集約後のExchangeを生成し返します。
     * 
     * ある集約識別子単位で初めての<code>newExchange</code>を受け取った場合、
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
        // "END"を含む場合、ヘッダに完了条件を設定
        if (newBody != null && newBody.contains("No.4")) {
            oldExchange.getIn().setHeader("completion", true);
        }
        // CSV形式の文字列を生成して再設定
        oldExchange.getIn().setBody(oldBody + "," + newBody);

        return oldExchange;
    }

}
