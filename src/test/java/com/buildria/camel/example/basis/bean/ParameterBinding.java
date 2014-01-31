package com.buildria.camel.example.basis.bean;

import java.util.Map;
import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterBinding {

    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * <p>{@link Exchange}をバインドします。</p>
     *
     * @param exchange Exchange
     */
    public void bindExchange(Exchange exchange) {
        if (exchange == null) {
            return;
        }
        log.debug("Exchange: {}", exchange);
        
        // ヘッダを取得
        Map<String, Object> headers = exchange.getIn().getHeaders();
        for (Map.Entry<String, Object> header : headers.entrySet()) {
            log.debug("ヘッダ  key: [{}] 値: [{}]", new Object[] { header.getKey(), header.getValue()});
        }
        
        // BODYを取得
        String body = exchange.getIn().getBody(String.class);
        log.debug("BODY [{}]", body);
    }

    /**
     * <p>{@link Message}をバインドします。</p>
     *
     * @param message Message
     */
    public void bindMessage(Message message) {
        if (message == null) {
            return;
        }
        log.debug("Message: {}", message);

        // ヘッダを取得
        Map<String, Object> headers = message.getHeaders();
        for (Map.Entry<String, Object> header : headers.entrySet()) {
            log.debug("ヘッダ  key: [{}] 値: [{}]", new Object[] { header.getKey(), header.getValue()});
        }

        // BODYを取得
        String body = message.getBody(String.class);
        log.debug("BODY [{}]", body);
    }

    /**
     * <p>アノテーションでヘッダの値とBODYをバインドします。</p>
     * 
     * @param type ヘッダのtypeの値
     * @param body BODY
     */
    public void bindHeaderValues(@Header("type") String type, @Body String body) {
        log.debug("ヘッダ(type): {}", type);
        log.debug("BODY: {}", body);
    }
    
}
