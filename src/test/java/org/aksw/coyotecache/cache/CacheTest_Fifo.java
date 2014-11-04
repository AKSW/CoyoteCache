package org.aksw.coyotecache.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

public class CacheTest_Fifo extends TestCase {
    /** logger */
    public static Logger log4j = Logger.getLogger(CacheTest_Fifo.class);

    public void testSize() {
        log4j.debug("Start test ...");

        CacheTest.testSize(new Fifo(10, 1));
    }

    public void testFifoInteger() {
        log4j.debug("Start test ...");

        final int size = 10;
        CacheInterface cache = new Fifo(size, 1);
        // 1. fill
        for (int i = 0; i < size; i++) {
            assertNull(cache.get(i));
            assertNull(cache.put(i, i));
        }
        // get all in cache
        for (int i = 0; i < size; i++) {
            assertEquals(cache.get(i), i);
            assertEquals(cache.get(i), i);
        }
        // 2. fill new
        for (int i = size; i < 2 * size; i++) {
            assertEquals(cache.get(i - size), i - size);
            assertNull(cache.get(i));
            assertNull(cache.put(i, i));
            assertNull(cache.get(i - size));
        }
        // all of 1. should evicted
        for (int i = 0; i < size; i++) {
            assertEquals(cache.get(i + size), i + size);
            assertNull(cache.get(i));
            assertNull(cache.put(i, i));
            assertNull(cache.get(i + size));
        }
    }

    public void testFifo() {

        CacheInterface cache = new Fifo(10, 1);
        ;
        List<Integer> m_int_list = null;

        m_int_list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++)
            m_int_list.add(i);

        m_int_list.addAll(m_int_list);
        m_int_list.addAll(m_int_list);
        Collections.shuffle(m_int_list);

        for (int i = 0; i < m_int_list.size(); i++)
            if (cache.get(m_int_list.get(i)) == null)
                assertNull(cache.put(m_int_list.get(i), m_int_list.get(i)));

        assertEquals(cache.size(), 10);
        assertNull(cache.put(11, 11));
        assertNull(cache.put(12, 12));
        assertEquals(cache.size(), 10);
        assertEquals(cache.get(11), 11);
        assertEquals(cache.get(12), 12);
        assertEquals(cache.size(), 10);

        for (int j = 100; j < 110; j++)
            assertNull(cache.put(j, j));

        assertEquals(cache.get(100), 100);
        assertNull(cache.put(110, 110));
        assertNull(cache.get(100));
        // System.out.println(cache);
    }
}