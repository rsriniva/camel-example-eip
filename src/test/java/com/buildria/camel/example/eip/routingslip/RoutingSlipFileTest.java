package com.buildria.camel.example.eip.routingslip;

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
public class RoutingSlipFileTest {
    
    @Autowired
    protected CamelContext camelContext;
    
    @Produce(uri = "direct:start")
    private ProducerTemplate template;

    @EndpointInject(uri = "mock:result")
    private MockEndpoint resultEndpoint;
    
    @Test
    @DirtiesContext
    public void testRoute() throws Exception {  
        List<String> prefs = new ArrayList<String>() {
            {
                add("tokyo");
                add("kanagawa");
                add("saitama");
            }
        };
        // ヘッダ"pref"に都道府県を設定        
        for (String pref : prefs) {
            template.sendBodyAndHeader(pref + "のデータ", "pref", pref);
        }
    }

}
