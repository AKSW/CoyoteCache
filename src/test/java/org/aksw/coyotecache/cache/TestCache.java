package org.aksw.coyotecache.cache;

import junit.framework.Assert;

import org.apache.log4j.Logger;

public class TestCache {
    /** logger */
    public static Logger log4j = Logger.getLogger(TestCache.class);

    public static void sizeTest(CacheInterface cache) {

        log4j.debug("Start test ...");

        final int size = cache.maxSize();

        // fill
        for (int i = 0; i < 2 * size; i++) {
            Assert.assertNull(cache.get(i));
            Assert.assertNull(cache.put(i, i));
        }
        Assert.assertEquals(cache.size(), 10);
    }
}