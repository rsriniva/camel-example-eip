package com.buildria.camel.example.eip.splitter;

import com.buildria.camel.example.eip.splitter.model.User;
import java.util.ArrayList;
import java.util.List;
import org.apache.camel.CamelContext;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SplitterStopOnExceptionTest {
    
    @Autowired
    protected CamelContext camelContext;
    
    @Produce(uri = "direct:start_a")
    private ProducerTemplate templateA;

    @Produce(uri = "direct:start_b")
    private ProducerTemplate templateB;
    
    @Test(expected = RuntimeException.class)
    @DirtiesContext
    public void testNoStopOnException() throws Exception {  
        // 入力データはList
        List<User> users = new ArrayList<User>();
        users.add(new User("001", "User01", "Tokyo"));
        users.add(new User("002", "User02", "Chiba"));
        users.add(new User("003", "User03", "Kanagawa"));

        // 入力データ送信
        templateA.sendBody(users);
        
        fail();
    }

    @Test(expected = RuntimeException.class)
    @DirtiesContext
    public void testStopOnException() throws Exception {  
        // 入力データはList
        List<User> users = new ArrayList<User>();
        users.add(new User("001", "User01", "Tokyo"));
        users.add(new User("002", "User02", "Chiba"));
        users.add(new User("003", "User03", "Kanagawa"));

        // 入力データ送信
        templateB.sendBody(users);
        
        fail();
    }

}
