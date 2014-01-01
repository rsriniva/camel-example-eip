package com.buildria.camel.example.eip.splitter;

import com.buildria.camel.example.eip.splitter.model.User;
import java.util.ArrayList;
import java.util.List;
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
public class SplitterParallelTest {
    
    @Autowired
    protected CamelContext camelContext;
    
    @Produce(uri = "direct:start_a")
    private ProducerTemplate templateA;

    @EndpointInject(uri = "mock:result_a")
    private MockEndpoint resultEndpointA;

    @Produce(uri = "direct:start_b")
    private ProducerTemplate templateB;

    @EndpointInject(uri = "mock:result_b")
    private MockEndpoint resultEndpointB;
    
    @Test
    @DirtiesContext
    public void testParallelProcessing() throws Exception {  
        // 入力データはList
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 100; i++) {
            users.add(new User(String.valueOf(i), "User" + i, "Tokyo"));
        }
        
        // 最後にデータを1つ受け取ること
        resultEndpointA.expectedMessageCount(1);
        
        // 入力データ送信
        templateA.sendBody(users);

        // 検証
        resultEndpointA.assertIsSatisfied();
    }
    
    @Test
    @DirtiesContext
    public void testExecutorServiceRef() throws Exception {  
        // 入力データはList
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 100; i++) {
            users.add(new User(String.valueOf(i), "User" + i, "Tokyo"));
        }
        
        // 最後にデータを1つ受け取ること
        resultEndpointB.expectedMessageCount(1);
        
        // 入力データ送信
        templateB.sendBody(users);

        // 検証
        resultEndpointB.assertIsSatisfied();
    }
    
}
