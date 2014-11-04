package org.aksw.coyotecache.cache;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class CacheTest_Cost extends TestCase {
    /** logger */

    static {
        PropertyConfigurator.configure("Log4j.properties");
    }
    public static Logger log4j = Logger.getLogger(CacheTest_Cost.class);

    public void testSize() {
        log4j.debug("Start test ...");
        CacheTest.testSize(new Cost(10, 1));
    }

    public void testCostElement() {
        log4j.debug("Start test ...");
        int size = 10;
        CacheInterface cache = new Cost(size, 1);

        List<Element> elements = new ArrayList<Element>();
        for (int i = 0; i < 2 * size; i++)
            elements.add(Element.getRandomElement(i));

        // fill cache
        for (int i = 0; i < size; i++)
            assertEquals(cache.put(elements.get(i), i), null);

        // get all elements in cache
        for (int i = 0; i < size; i++)
            assertEquals(cache.get(elements.get(i)), i);

        // add elements
        for (int i = size; i < 2 * size; i++)
            assertEquals(cache.put(elements.get(i), i), null);

        // get elements
        for (int i = 0; i < size - 1; i++)
            assertEquals(cache.get(elements.get(i)), i);

        // thats bad!
        assertEquals(cache.get(elements.get(2 * size - 1)), 2 * size - 1);
    }
}