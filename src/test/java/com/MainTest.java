//Author - Prashit Patel
package com;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MainTest {
    @Test
    public void notNullTest() {
        Main m = new Main();
        assertNotNull(m);
    }
}