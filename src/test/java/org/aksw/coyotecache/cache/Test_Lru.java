package org.aksw.coyotecache.cache;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

/**
 * Test Lru with integer.
 * 
 * @author rspeck
 * 
 */
public class Test_Lru extends TestCase {
    /** logger */
    public static Logger log4j = Logger.getLogger(Test_Lru.class);

    public void testSize() {
        log4j.debug("Start test ...");

        TestCache.sizeTest(new Lru(10, 1));
    }

    // test one

    public void testLRUone() {
        log4j.debug("Start test ...");

        // lru size 10 and evic 1
        CacheInterface cache = new Lru(10, 1);
        // put 20 elements
        for (int i = 0; i < 20; i++)
            cache.put(i, String.valueOf(i));
        // size 10
        assertEquals(cache.size(), 10);
        // test should be true
        assertEquals(true, cache.test());
        // cache contains last 10 elements
        for (int i = 10; i < 20; i++)
            assertEquals(String.valueOf(i), cache.get(i));
        // and again
        for (int i = 10; i < 20; i++)
            assertEquals(String.valueOf(i), cache.get(i));
        // put 5 new elements
        for (int i = 20; i < 25; i++) {
            cache.put(i, String.valueOf(i));
            assertEquals(null, cache.get(i - 10));
        }
        for (int i = 20; i < 25; i++)
            assertEquals(String.valueOf(i), cache.get(i));
        // size 10
        assertEquals(cache.size(), 10);
        // test should be true
        assertEquals(true, cache.test());
    }

    // test two

    public void testLRUtwo() {
        CacheInterface cache = new Lru(2, 1);
        Element e = Element.getRandomElement();
        Element ee = new Element(e.getObject(), 2);
        Element eee = Element.getRandomElement();
        Element eeee = Element.getRandomElement();

        int id = 1;
        assertNull(cache.get(e));
        assertNull(cache.get(eee));
        assertEquals(cache.size(), 0);

        assertEquals(cache.put(e, id), null);
        assertEquals(cache.put(eee, id), null);
        assertEquals(cache.size(), 2);

        assertEquals(cache.get(e), id);
        assertEquals(cache.size(), 2);
        // ee was not put in the cache, but is same as e
        assertEquals(cache.get(ee), id);
        assertEquals(cache.size(), 2);
        assertEquals(cache.get(eee), id);
        assertEquals(cache.size(), 2);

        assertEquals(cache.get(eeee), null);
        assertEquals(cache.size(), 2);
        assertEquals(cache.put(eeee, id), null);
        assertEquals(cache.size(), 2);
        // ee should be removes
        assertNull(cache.get(ee));
        assertEquals(cache.size(), 2);
    }
}