package com.buildria.camel.example.eip.messagefilter;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class MessageFilterTest {
    
    @Autowired
    protected CamelContext camelContext;
    
    @Produce(uri = "direct:start")
    private ProducerTemplate template;

    @EndpointInject(uri = "mock:result")
    private MockEndpoint resultEndpoint;
    
    @Test
    @DirtiesContext
    public void testHeaderType() throws Exception {  
        // 最後にデータを3つ受け取ること
        resultEndpoint.expectedMessageCount(3);
        
        // 入力データ送信
        template.sendBodyAndHeader("No.1 data", "type", "D");
        template.sendBodyAndHeader("No.2 data", "type", "A");
        template.sendBodyAndHeader("No.3 data", "type", "?");

        // 検証
        resultEndpoint.assertIsSatisfied();
    }
}
