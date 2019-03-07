package com.lifeshs.app.api.wearterminal;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class TestHello{

    @Test
    public void testHello(){
        Hello h = new Hello();
        assertEquals("yue say hello",h.sayHello("yue"));
    }
}