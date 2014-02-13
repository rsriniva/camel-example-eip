package com.buildria.camel.example.tips.throughput;

import com.buildria.camel.example.JunitBase;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.NotifyBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ThroughputTest extends JunitBase {

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
    public void testThroughput() throws Exception {
        
        NotifyBuilder notify = new NotifyBuilder(camelContext).wereSentTo("mock:result").whenDone(100).create();
        
        for (int loop = 0; loop < 100; loop++) {
            List<User> users = new ArrayList<User>();
            for (int i = 0; i < 50; i++) {
                String no = String.format("%03d", i);
                users.add(new User(no, "User" + no, "Tokyo" + no));
            }
            // 入力データ送信
            template.sendBody("seda:throughput", users);
        }
        
        boolean done = notify.matches(180, TimeUnit.SECONDS);
    }

}
