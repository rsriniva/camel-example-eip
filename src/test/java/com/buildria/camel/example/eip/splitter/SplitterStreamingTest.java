package com.buildria.camel.example.eip.splitter;

import java.io.File;
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
public class SplitterStreamingTest {
    
    @Autowired
    protected CamelContext camelContext;
    
    @Produce(uri = "direct:start")
    private ProducerTemplate template;
    
    @EndpointInject(uri = "mock:result")
    private MockEndpoint resultEndpoint;

    @Test
    @DirtiesContext
    public void testStreaming() throws Exception {  
        // 入力ファイル    
        File csv = new File("src/test/resources/com/buildria/camel/example/eip/splitter/streaming_data.csv");
        
        // 最後にデータを1つ受け取ること
        resultEndpoint.expectedMessageCount(1);
        
        // 入力データ送信
        template.sendBody(csv);

        // 検証
        resultEndpoint.assertIsSatisfied();
    }


}
