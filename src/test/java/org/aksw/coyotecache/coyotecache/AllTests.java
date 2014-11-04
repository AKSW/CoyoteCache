package org.aksw.coyotecache.coyotecache;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.aksw.coyotecache.cache.CacheTestAll;
import org.aksw.coyotecache.seeds.SeedTestAll;
import org.apache.log4j.PropertyConfigurator;

public class AllTests {

    static {
        PropertyConfigurator.configure("Log4j.properties");
    }

    public static Test suite() {

        TestSuite suite = new TestSuite();

        suite.addTest(CacheTestAll.suite());
        suite.addTest(SeedTestAll.suite());

        suite.addTestSuite(SeedCacheTest.class);

        return suite;
    }
}
