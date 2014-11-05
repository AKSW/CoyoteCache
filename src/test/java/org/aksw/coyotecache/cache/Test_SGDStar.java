package org.aksw.coyotecache.cache;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

public class Test_SGDStar extends TestCase {

    public static Logger log4j = Logger.getLogger(Test_SGDStar.class);

    public void test() {
        log4j.debug("Start test ...");

        CacheInterface cache = new SGDStar(10, 1);

        // 1. cache entry
        Map<Integer, String> map1 = new HashMap<>();
        map1.put(1, "2");

        Element e1 = new Element(map1, 1);

        cache.put(e1, map1.values().size());

        // 2. cache entry
        Map<Integer, String> map2 = new HashMap<>();
        map2.put(1, "2");
        map2.put(2, "1");

        Element e2 = new Element(map2, 1);
        cache.put(e2, map2.values().size());

        // Test
        // hit one 2. entry
        assertEquals(map2.values().size(), cache.get(e2));
        // hit one 1. entry
        assertEquals(map1.values().size(), cache.get(e1));

        // 3. cache entry
        Map<Integer, String> map3 = new HashMap<>();
        map3.put(1, "3");
        map3.put(2, "4");
        Element e3 = new Element(map3, 1);

        cache.put(e3, map3.values().size());

        // here the 2. entry should be gone, lets test
        // assertEquals(null, cache.get(map2));

    }
}
