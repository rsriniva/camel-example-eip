package com.buildria.camel.example.eip.routingslip;

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
public class RoutingSlipFileTest extends JunitBase {
    
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
            template.sendBodyAndHeader("direct:start", pref + "のデータ", "pref", pref);
        }
    }

}
