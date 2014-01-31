package com.buildria.camel.example.eip.aggregator;

import com.buildria.camel.example.JunitBase;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AggregatorBasicTest extends JunitBase {
    
    @Autowired
    protected CamelContext camelContext;
    
    private ProducerTemplate template;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        template = camelContext.createProducerTemplate();
    }    
        
    @Test
    @DirtiesContext
    public void testAggregate() throws Exception {  
        // 入力データ送信
        template.sendBodyAndHeader("direct:start", "No.1", "type", "A");
        template.sendBodyAndHeader("direct:start", "No.2", "type", "B");
        template.sendBodyAndHeader("direct:start", "No.3", "type", "C");
        template.sendBodyAndHeader("direct:start", "No.4", "type", "A");
        template.sendBodyAndHeader("direct:start", "No.5", "type", "A");
        template.sendBodyAndHeader("direct:start", "No.6", "type", "B");
        template.sendBodyAndHeader("direct:start", "No.7", "type", "B");
    }
}
