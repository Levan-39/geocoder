package ru.kubsu.geocoder.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestUtilTest {
    @Test
    void unitTest() {
        assertEquals(4, TestUtil.sum(1,3));
        assertEquals(300, TestUtil.sum(200,100));
        assertEquals(90, TestUtil.sum(50,40));
        assertEquals(149, TestUtil.sum(100,49));
    }

}