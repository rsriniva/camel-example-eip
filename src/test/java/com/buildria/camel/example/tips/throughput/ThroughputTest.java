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

    /**
     * 送信するデータサイズ。
     */
    private static final int DATA_SIZE = 100;
    
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
        
        // <to uri="mock:result" />に送信したメッセージがすべて完了すること
        NotifyBuilder notify = new NotifyBuilder(camelContext).wereSentTo("mock:result")
                .whenDone(DATA_SIZE).create();
        
        // 50個のUserをDATA_SIZE回非同期で送信する
        for (int loop = 0; loop < DATA_SIZE; loop++) {
            List<User> users = new ArrayList<User>();
            for (int i = 0; i < 50; i++) {
                String no = String.format("%03d", i);
                users.add(new User(no, "User" + no, "Tokyo" + no));
            }
            template.sendBody("seda:throughput", users);
        }
        
        // すべてが完了するまで待ち合わせる
        boolean done = notify.matches(180, TimeUnit.SECONDS);
    }

}
