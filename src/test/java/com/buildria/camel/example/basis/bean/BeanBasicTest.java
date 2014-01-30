package com.buildria.camel.example.basis.bean;

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
public class BeanBasicTest {

    @Autowired
    protected CamelContext camelContext;

    private ProducerTemplate template;

    @Before
    public void setUp() throws Exception {
        template = camelContext.createProducerTemplate();
    }

    @Test
    @DirtiesContext
    public void testBeanTag() throws Exception {
        template.sendBody("direct:beanTag", "sample data");
    }

    @Test
    @DirtiesContext
    public void testBeanComponent() throws Exception {
        template.sendBody("direct:beanComponent", "sample data");
    }

    @Test
    @DirtiesContext
    public void testBeanEndpoint() throws Exception {
        template.sendBody("direct:beanEndpoint", "sample data");
    }

    @Test
    @DirtiesContext
    public void testProcess() throws Exception {
        template.sendBody("direct:process", "sample data");
    }

    @Test
    @DirtiesContext
    public void testProcessTo() throws Exception {
        template.sendBody("direct:process_to", "sample data");
    }
    
}
