package org.aksw.coyotecache.cache;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

public class Test_Mixed extends TestCase {
    public void testLRUtwo() {

        CacheInterface cache = new Lru(2, 1);

        // 1. cache entry
        Map<Integer, String> map1 = new HashMap<>();
        map1.put(1, "2");

        cache.put(map1, map1.values().size());

        // 2. cache entry
        Map<Integer, String> map2 = new HashMap<>();
        map2.put(1, "2");
        map2.put(2, "1");
        cache.put(map2, map2.values().size());

        // Test
        // hit one 2. entry
        assertEquals(map2.values().size(), cache.get(map2));
        // hit one 1. entry
        assertEquals(map1.values().size(), cache.get(map1));

        // 3. cache entry
        Map<Integer, String> map3 = new HashMap<>();
        map3.put(1, "3");
        map3.put(2, "4");
        cache.put(map3, map3.values().size());

        // here the 2. entry should be gone, lets test
        assertEquals(null, cache.get(map2));
    }
}
