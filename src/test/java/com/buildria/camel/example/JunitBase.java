package com.buildria.camel.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JunitBase {

    @Rule
    public TestName name = new TestName();
    
    private final Logger log = LoggerFactory.getLogger(getClass());
    
    @Before
    public void setUp() throws Exception {
        log.debug("================================================");
        log.debug(getClass().getSimpleName() + "#" + name.getMethodName() + " START");
        log.debug("================================================");
    }
    
    @After
    public void tearDown() throws Exception {
        log.debug("================================================");
        log.debug(getClass().getSimpleName() + "#" + name.getMethodName() + " END");
        log.debug("================================================");
    }    
}
