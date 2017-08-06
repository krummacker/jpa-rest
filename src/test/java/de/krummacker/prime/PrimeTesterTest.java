package de.krummacker.prime;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PrimeTesterTest {

    private PrimeTester primeTester;

    @BeforeMethod
    public void setUp() throws Exception {
        primeTester = new PrimeTester();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        primeTester = null;
    }

    /**
     * Make sure that -1 is not considered a prime.
     */
    @Test
    public void testMinusOneNotPrime() throws Exception {
        Assert.assertFalse(primeTester.isPrime(-1));
    }

    /**
     * Make sure that 0 is not considered a prime.
     */
    @Test
    public void testZeroNotPrime() throws Exception {
        Assert.assertFalse(primeTester.isPrime(0));
    }

    /**
     * Make sure that 1 is not considered a prime.
     */
    @Test
    public void testOneNotPrime() throws Exception {
        Assert.assertFalse(primeTester.isPrime(1));
    }

    /**
     * Make sure that 2 is considered a prime.
     */
    @Test
    public void testTwoPrime() throws Exception {
        Assert.assertTrue(primeTester.isPrime(2));
    }

    /**
     * Make sure that 3 is considered a prime.
     */
    @Test
    public void testThreePrime() throws Exception {
        Assert.assertTrue(primeTester.isPrime(3));
    }

    /**
     * Make sure that 4 is not considered a prime.
     */
    @Test
    public void testFourNotPrime() throws Exception {
        Assert.assertFalse(primeTester.isPrime(4));
    }

    /**
     * Make sure that 5 is considered a prime.
     */
    @Test
    public void testFivePrime() throws Exception {
        Assert.assertTrue(primeTester.isPrime(5));
    }

    /**
     * Make sure that 91 is not considered a prime.
     */
    @Test
    public void test91NotPrime() throws Exception {
        Assert.assertFalse(primeTester.isPrime(91));
    }

    /**
     * Make sure that 97 is considered a prime.
     */
    @Test
    public void test97Prime() throws Exception {
        Assert.assertTrue(primeTester.isPrime(97));
    }

    /**
     * Make sure that Integer.MAX_VALUE is considered a prime, Joshua Bloch stated it so it must be true.
     */
    // @Test not executed, takes 6 seconds and thus too long
    public void testIntegerMAX_VALUEPrime() throws Exception {
        Assert.assertTrue(primeTester.isPrime(Integer.MAX_VALUE));
    }

    /**
     * Make sure that Integer.MIN_VALUE is not considered a prime.
     */
    @Test
    public void testIntegerMIN_VALUENotPrime() throws Exception {
        Assert.assertFalse(primeTester.isPrime(Integer.MIN_VALUE));
    }
}