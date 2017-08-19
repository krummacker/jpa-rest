package de.krummacker.prime;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OddNumberTesterTest {

    private OddNumberTester oddNumberTester;

    @BeforeMethod
    public void setUp() throws Exception {
        oddNumberTester = new OddNumberTester();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        oddNumberTester = null;
    }

    /**
     * Make sure that -1 is considered odd.
     */
    @Test
    public void testMinusOneOdd() throws Exception {
        Assert.assertTrue(oddNumberTester.isOdd(-1));
    }

    /**
     * Make sure that 0 is considered even.
     */
    @Test
    public void testZeroNotOdd() throws Exception {
        Assert.assertFalse(oddNumberTester.isOdd(0));
    }

    /**
     * Make sure that 1 is considered odd.
     */
    @Test
    public void testOneOdd() throws Exception {
        Assert.assertTrue(oddNumberTester.isOdd(1));
    }

    /**
     * Make sure that 2 is considered even.
     */
    @Test
    public void testTwoNotOdd() throws Exception {
        Assert.assertFalse(oddNumberTester.isOdd(2));
    }

    /**
     * Make sure that Integer.MAX_VALUE is considered odd.
     */
    @Test
    public void testIntegerMAX_VALUEPrime() throws Exception {
        Assert.assertTrue(oddNumberTester.isOdd(Integer.MAX_VALUE));
    }

    /**
     * Make sure that Integer.MIN_VALUE is considered odd.
     */
    @Test
    public void testIntegerMIN_VALUENotOdd() throws Exception {
        Assert.assertFalse(oddNumberTester.isOdd(Integer.MIN_VALUE));
    }
}