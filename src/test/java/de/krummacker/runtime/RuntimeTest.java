package de.krummacker.runtime;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests how many processors this laptop has.
 */
public class RuntimeTest {

    @Test
    public void testAvailableProcessors() {
        int procs = Runtime.getRuntime().availableProcessors();

        // My laptop has 8 cores but CircleCI apparently runs on 32 cores.
        Assert.assertTrue(procs == 8 || procs == 32);
    }
}