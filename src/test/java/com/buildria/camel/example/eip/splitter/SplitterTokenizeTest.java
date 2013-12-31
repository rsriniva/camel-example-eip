package com.buildria.camel.example.eip.splitter;

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
public class SplitterTokenizeTest {
    
    @Autowired
    protected CamelContext camelContext;
    
    @Produce(uri = "direct:start1")
    private ProducerTemplate template1;

    @EndpointInject(uri = "mock:result1")
    private MockEndpoint resultEndpoint1;

    @Produce(uri = "direct:start2")
    private ProducerTemplate template2;

    @EndpointInject(uri = "mock:result2")
    private MockEndpoint resultEndpoint2;
    
    @Test
    @DirtiesContext
    public void testBodyIsCSV() throws Exception {  
        // 入力データはCSV
        StringBuilder csv = new StringBuilder();
        csv.append("A, 001, User001,Tokyo").append("\n");
        csv.append("U, 002, User002,Chiba").append("\n");
        csv.append("D, 003, User003,Kanagawa").append("\n");

        // 最後にデータを1つ受け取ること
        resultEndpoint1.expectedMessageCount(1);
        
        // 入力データ送信
        template1.sendBody(csv.toString());

        // 検証
        resultEndpoint1.assertIsSatisfied();
    }

    @Test
    @DirtiesContext
    public void testBodyIsCSVGroupSpecified() throws Exception {  
        // 入力データはCSV
        StringBuilder csv = new StringBuilder();
        csv.append("A, 001, User001,Tokyo").append("\n");
        csv.append("U, 002, User002,Chiba").append("\n");
        csv.append("D, 003, User003,Kanagawa");

        // 最後にデータを1つ受け取ること
        resultEndpoint2.expectedMessageCount(1);
        
        // 入力データ送信
        template2.sendBody(csv.toString());

        // 検証
        resultEndpoint2.assertIsSatisfied();
    }

}
