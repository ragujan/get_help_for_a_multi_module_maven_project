package com.module.web;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FakeTest {
    public int add(int a, int b) {
        return a + b;
    }
    @Test
    public void testThis() {
        System.out.println("running the fake test");
        int x = 3;
        int y = 4;
        int expected = 7;

        int result = add(x,y);
        assertEquals(expected,result);
    }
    @Test
    public void doThisBro(){
        System.out.println("hey hey");
    }
}
