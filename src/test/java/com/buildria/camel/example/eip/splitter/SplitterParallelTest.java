package com.buildria.camel.example.eip.splitter;

import com.buildria.camel.example.JunitBase;
import com.buildria.camel.example.eip.splitter.model.User;
import java.util.ArrayList;
import java.util.List;
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
public class SplitterParallelTest extends JunitBase {
    
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
    public void testParallelProcessing() throws Exception {  
        // 入力データはList
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 100; i++) {
            users.add(new User(String.valueOf(i), "User" + i, "Tokyo"));
        }
        
        // 入力データ送信
        template.sendBody("direct:parallelProcessing", users);
    }
    
    @Test
    @DirtiesContext
    public void testExecutorServiceRef() throws Exception {  
        // 入力データはList
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 100; i++) {
            users.add(new User(String.valueOf(i), "User" + i, "Tokyo"));
        }
        
        // 入力データ送信
        template.sendBody("direct:executorServiceRef", users);
    }
    
}
