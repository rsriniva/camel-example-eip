package com.buildria.camel.example.eip.messagefilter;

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
public class MessageFilterTest extends JunitBase {
    
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
    public void testHeaderType() throws Exception {  
        // 入力データ送信
        template.sendBodyAndHeader("direct:start", "No.1 data", "type", "D");
        template.sendBodyAndHeader("direct:start", "No.2 data", "type", "A");
        template.sendBodyAndHeader("direct:start", "No.3 data", "type", "?");
    }
}
