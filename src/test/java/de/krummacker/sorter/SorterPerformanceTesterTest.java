package de.krummacker.sorter;

import org.testng.annotations.Test;

/**
 * Tests the SorterPerformanceTester class.
 */
public class SorterPerformanceTesterTest {

    /**
     * Make sure that the main method can be called (happy case).
     */
    @Test
    public void testMain() {
        String[] args = new String[]{"-m", "100", "-s", "10"};
        SorterPerformanceTester.main(args);
    }
}