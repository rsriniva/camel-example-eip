package com.buildria.camel.example.eip.splitter;

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
public class SplitterTokenizeTest {
    
    @Autowired
    protected CamelContext camelContext;

    private ProducerTemplate template;

    @Before
    public void setUp() throws Exception {
        template = camelContext.createProducerTemplate();
    }
    
    @Test
    @DirtiesContext
    public void testTokenize() throws Exception {  
        // 入力データはCSV
        StringBuilder csv = new StringBuilder();
        csv.append("A, 001, User001,Tokyo").append("\n");
        csv.append("U, 002, User002,Chiba").append("\n");
        csv.append("D, 003, User003,Kanagawa");

        // 入力データ送信
        template.sendBody("direct:tokenize", csv.toString());
    }

    @Test
    @DirtiesContext
    public void testTokenizeGroup() throws Exception {  
        // 入力データはCSV
        StringBuilder csv = new StringBuilder();
        csv.append("A, 001, User001,Tokyo").append("\n");
        csv.append("U, 002, User002,Chiba").append("\n");
        csv.append("D, 003, User003,Kanagawa");

        // 入力データ送信
        template.sendBody("direct:tokenize_group", csv.toString());
    }

}
