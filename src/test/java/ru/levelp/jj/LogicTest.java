package ru.levelp.jj;

import org.junit.Test;

import static org.junit.Assert.*;

public class LogicTest {
    @Test
    public void testImplementation() {
        int result = new Logic().someImplementation();
        assertEquals(777, result);
    }

    @Test
    public void testImplementation2() {
        int result = new Logic().someImplementation();
    }

    @Test
    public void testSum() {
        assertEquals(3, new Logic().sum(1, 2));
        assertEquals(3, new Logic().sum(2, 1));
        assertEquals(0, new Logic().sum(0, 0));
    }
}
