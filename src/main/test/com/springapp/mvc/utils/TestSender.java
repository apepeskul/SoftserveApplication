package com.springapp.mvc.utils;

import static org.junit.Assert.*;
import org.junit.*;

public class TestSender {

    private Sender sender;

    @Before
    public void setUp() throws Exception {
        sender = new Sender();
    }

    @Test
    public void testSendersUser() {
        assertEquals("itasoftservejavaapp@gmail.com", sender.getUser());
    }

    @Test
    public void testSendersPassword() {
        assertEquals("java2013", sender.getPass());
    }

    @Test
    public void testSend() {
        assertEquals("java2013", sender.getPass());
    }

}

