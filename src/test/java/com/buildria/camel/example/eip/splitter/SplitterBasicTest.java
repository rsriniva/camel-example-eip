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
public class SplitterBasicTest {
    
    @Autowired
    protected CamelContext camelContext;
    
    @Produce(uri = "direct:start")
    private ProducerTemplate template;

    @EndpointInject(uri = "mock:result")
    private MockEndpoint resultEndpoint;
    
    @Test
    @DirtiesContext
    public void testBodyIsList() throws Exception {  
        // 入力データはList
        List<User> users = new ArrayList<User>();
        users.add(new User("001", "User01", "Tokyo"));
        users.add(new User("002", "User02", "Chiba"));
        users.add(new User("003", "User03", "Kanagawa"));

        // 最後にデータを1つ受け取ること
        resultEndpoint.expectedMessageCount(1);
        
        // 入力データ送信
        template.sendBody(users);

        // 検証
        resultEndpoint.assertIsSatisfied();
    }
}
