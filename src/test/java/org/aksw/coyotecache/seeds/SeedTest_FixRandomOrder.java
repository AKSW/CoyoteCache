package org.aksw.coyotecache.seeds;

import java.util.LinkedHashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

public class SeedTest_FixRandomOrder extends TestCase {
    /** logger */
    public static Logger log4j = Logger.getLogger(SeedTest_FixRandomOrder.class);

    /**
     * Add seeds and compare seed order of two instances of FixRandomOrder.
     * Order should be the same every time.
     */

    public void testFixRandomOrder() {
        log4j.debug("Start test ...");

        final int LOOP = 1000;

        SeedOrderInterface fixrandOne = new FixRandomOrder();
        SeedOrderInterface fixrandTwo = new FixRandomOrder();
        Set<Integer> list = new LinkedHashSet<Integer>();

        for (int i = 0; i < LOOP; i++) {
            ((FixRandomOrder) fixrandOne).add(i);
            list.add(i);
        }
        fixrandTwo.addAll(list);

        for (int i = 0; i < LOOP; i++)
            assertEquals(fixrandOne.getBestSeed(), fixrandTwo.getBestSeed());
    }
}
