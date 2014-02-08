package com.buildria.camel.example.basis.thread;

import com.buildria.camel.example.JunitBase;
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
public class ThreadProfileTest extends JunitBase {
    
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
    public void testMyDefaultThreadPoolProfile() throws Exception {  
        // 入力データはList
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 100; i++) {
            String no = String.format("%03d", i);
            users.add(new User(no, "User" + no, "Tokyo" + no));
        }
        
        // 入力データ送信
        template.sendBody("direct:MyDefaultThreadPoolProfile", users);
    }

    @Test
    @DirtiesContext
    public void testMyCustomThreadPoolProfile() throws Exception {  
        // 入力データはList
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 100; i++) {
            String no = String.format("%03d", i);
            users.add(new User(no, "User" + no, "Tokyo" + no));
        }
        
        // 入力データ送信
        template.sendBody("direct:MyCustomThreadPoolProfile", users);
    }

    @Test
    @DirtiesContext
    public void testMyThreadPool() throws Exception {  
        // 入力データはList
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 10000; i++) {
            String no = String.format("%03d", i);
            users.add(new User(no, "User" + no, "Tokyo" + no));
        }
        
        // 入力データ送信
        template.sendBody("direct:MyThreadPool", users);
    }
    
}
