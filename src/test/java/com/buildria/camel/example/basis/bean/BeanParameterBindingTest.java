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
public class BeanParameterBindingTest extends JunitBase {

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
    public void testBindExchange() throws Exception {
        template.sendBodyAndHeader("direct:exchange", "This is a BODY", "key", "value");
    }

    @Test
    @DirtiesContext
    public void testBindMessage() throws Exception {
        template.sendBodyAndHeader("direct:message", "This is a BODY", "key", "value");
    }

    @Test
    @DirtiesContext
    public void testBindAnnotation() throws Exception {
        template.sendBodyAndHeader("direct:annotation", "This is a BODY", "type", "TYPE A");
    }
    
}
