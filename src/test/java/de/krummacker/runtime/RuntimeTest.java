package de.krummacker.runtime;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests how many processors this laptop has.
 */
public class RuntimeTest {

    @Test
    public void testAvailableProcessors() {
        Assert.assertEquals(Runtime.getRuntime().availableProcessors(), 8);
    }
}