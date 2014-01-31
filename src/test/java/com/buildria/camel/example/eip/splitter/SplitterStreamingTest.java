package com.buildria.camel.example.eip.splitter;

import java.io.File;
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
public class SplitterStreamingTest {
    
    @Autowired
    protected CamelContext camelContext;
    
    private ProducerTemplate template;

    @Before
    public void setUp() throws Exception {
        template = camelContext.createProducerTemplate();
    }
    
    @Test
    @DirtiesContext
    public void testStreaming() throws Exception {  
        // 入力ファイル    
        File csv = new File("src/test/resources/com/buildria/camel/example/eip/splitter/streaming_data.csv");
        
        // 入力データ送信
        template.sendBody("direct:streaming", csv);
    }

}
