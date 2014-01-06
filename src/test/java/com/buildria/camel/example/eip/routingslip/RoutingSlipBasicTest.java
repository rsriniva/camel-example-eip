package com.buildria.camel.example.eip.routingslip;

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
public class RoutingSlipBasicTest {
    
    @Autowired
    protected CamelContext camelContext;
    
    @Produce(uri = "direct:start")
    private ProducerTemplate template;

    @EndpointInject(uri = "mock:result")
    private MockEndpoint resultEndpoint;
    
    @Test
    @DirtiesContext
    public void testRoute() throws Exception {  
        template.sendBodyAndHeader("Data", "routingSlipHeader", "direct:route_a");
    }

    @Test
    @DirtiesContext
    public void testRoutes() throws Exception {  
        template.sendBodyAndHeader("Data", "routingSlipHeader", "direct:route_a#direct:route_b#direct:route_c");
    }

}
