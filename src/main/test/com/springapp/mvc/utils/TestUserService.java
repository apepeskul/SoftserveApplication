package com.springapp.mvc.utils;

import static org.junit.Assert.*;
import org.junit.*;
public class TestUserService {

    private UserService userService;

    @Before
    public void setUp() throws Exception {
         userService = new UserService();
    }

    @Test
    public void testToHash() {
        assertEquals("50bed5b8ed1fb80e48a354df6c89944a", userService.toHash("Login"));
        assertEquals("b40b846a3dc0f4a776e860e510c0e580", userService.toHash(""));
        assertEquals(32, userService.toHash("").length());
    }

}

