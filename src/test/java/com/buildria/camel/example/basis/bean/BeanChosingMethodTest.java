package com.buildria.camel.example.basis.bean;

import com.buildria.camel.example.JunitBase;
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
public class BeanChosingMethodTest extends JunitBase {

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
    public void testConvertBody() throws Exception {
        template.sendBodyAndHeader("direct:convertBody", "This is a BODY", "type", "Type A");
    }

    @Test
    @DirtiesContext
    public void testConvertBodyString() throws Exception {
        template.sendBodyAndHeader("direct:convertBodyString", "This is a BODY", "type", "Type A");
    }

    @Test
    @DirtiesContext
    public void testConvertBodyStringString() throws Exception {
        template.sendBodyAndHeader("direct:convertBodyStringString", "This is a BODY", "type", "Type A");
    }
    
}
