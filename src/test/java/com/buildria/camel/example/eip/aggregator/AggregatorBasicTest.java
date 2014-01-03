package com.buildria.camel.example.eip.aggregator;

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
public class AggregatorBasicTest {
    
    @Autowired
    protected CamelContext camelContext;
    
    @Produce(uri = "direct:start")
    private ProducerTemplate template;

    @EndpointInject(uri = "mock:result")
    private MockEndpoint resultEndpoint;
    
    @Test
    @DirtiesContext
    public void testAggregate() throws Exception {  
        // 入力データ送信
        template.sendBodyAndHeader("No.1", "type", "A");
        template.sendBodyAndHeader("No.2", "type", "B");
        template.sendBodyAndHeader("No.3", "type", "C");
        template.sendBodyAndHeader("No.4", "type", "A");
        template.sendBodyAndHeader("No.5", "type", "A");
        template.sendBodyAndHeader("No.6", "type", "B");
        template.sendBodyAndHeader("No.7", "type", "B");
    }
}
