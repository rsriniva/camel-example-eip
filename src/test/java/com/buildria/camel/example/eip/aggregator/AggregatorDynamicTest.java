package com.buildria.camel.example.eip.aggregator;

import java.util.HashMap;
import java.util.Map;
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
public class AggregatorDynamicTest {

    @Autowired
    protected CamelContext camelContext;

    @Produce(uri = "direct:start")
    private ProducerTemplate template;

    @EndpointInject(uri = "mock:result")
    private MockEndpoint resultEndpoint;

    @Test
    @DirtiesContext
    public void testAggregate() throws Exception {
        // ヘッダ
        Map<String, Object> headerA = new HashMap<String, Object>() {
            private static final long serialVersionUID = 1L;

            {
                put("type", "A");
                put("completionSize", 3);
            }
        };
        Map<String, Object> headerB = new HashMap<String, Object>() {
            private static final long serialVersionUID = 1L;

            {
                put("type", "B");
                put("completionSize", 2);
            }
        };

        template.sendBodyAndHeaders("No.1", headerA);
        template.sendBodyAndHeaders("No.2", headerB);
        template.sendBodyAndHeaders("No.3", headerA);
        template.sendBodyAndHeaders("No.4", headerB);
        template.sendBodyAndHeaders("No.5", headerA);
        template.sendBodyAndHeaders("No.6", headerB);

//[main] (1) before: A : No.1
//[main] (3) end: A - No.1
//[main] (1) before: B : No.2
//[main] (3) end: B - No.2
//[main] (1) before: A : No.3
//[main] (3) end: A - No.3
//[main] (1) before: B : No.4
//[main] (2) aggregated: No.2,No.4
//[main] (3) end: B - No.4
//[main] (1) before: A : No.5
//[main] (2) aggregated: No.1,No.3,No.5
//[main] (3) end: A - No.5
//[main] (1) before: B : No.6
//[main] (3) end: B - No.6    
        
    }
}
