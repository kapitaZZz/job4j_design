package ru.job4j.map;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void testPutAndTrue() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.put("John", 47));
    }

    @Test
    public void testPutAndFalse() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.put("John", 47));
        assertFalse(simpleMap.put("John", 47));
    }

    @Test
    public void testRemoveAndTrue() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("1", 1);
        simpleMap.put("2", 2);
        simpleMap.put("3", 3);
        assertTrue(simpleMap.remove("3"));

    }

    @Test
    public void testRemoveAndFalse() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("1", 1);
        simpleMap.put("2", 2);
        simpleMap.put("3", 3);
        assertFalse(simpleMap.remove("5"));
    }

    @Test
    public void testGetAndTrue() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("1", 1);
        assertNotNull(simpleMap.get("1"));
    }

    @Test
    public void testGetAndFalse() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("1", 1);
        assertNull(simpleMap.get("2"));
    }
}