package de.krummacker.tools;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ToolsTest {

    @BeforeMethod
    public void setUp() throws Exception {
    }

    @AfterMethod
    public void tearDown() throws Exception {
    }

    /**
     * Make sure that the createRandomList() method returns a random list of the right length (happy case).
     */
    @Test
    public void testCreateRandomList() throws Exception {
        List<Integer> actual = Tools.createRandomList(10);
        Assert.assertEquals(actual.size(), 10);
    }

    /**
     * Make sure that the createRandomList() method returns still works if the argument is zero.
     */
    @Test
    public void testCreateRandomListZero() throws Exception {
        List<Integer> actual = Tools.createRandomList(0);
        Assert.assertEquals(actual.size(), 0);
    }

    /**
     * Make sure that the createRandomList() method fails if the parameter is negative.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateRandomListNegative() {
        Tools.createRandomList(-10);
    }

    /**
     * Make sure that the computeMedian() method works fine in the happy case.
     */
    @Test
    public void testComputeMedianHappyCase() throws Exception {
        Comparable actual = Tools.computeMedian(1, 2, 3);
        Assert.assertEquals(actual, 2);
    }

    /**
     * Make sure that the computeMedian() method works fine if two arguments are equal.
     */
    @Test
    public void testComputeMedianTwoEqual() throws Exception {
        Comparable actual = Tools.computeMedian(42, 17, 42);
        Assert.assertEquals(actual, 42);
    }

    /**
     * Make sure that the computeMedian() method works fine if all arguments are equal.
     */
    @Test
    public void testComputeMedianAllEqual() throws Exception {
        Comparable actual = Tools.computeMedian(99, 99, 99);
        Assert.assertEquals(actual, 99);
    }

    /**
     * Make sure that the computeMedian() method fails if a single parameter is null.
     */
    @Test(expectedExceptions = NullPointerException.class)
    public void testComputeMedianNullSingle() {
        Tools.computeMedian(1, null, 3);
    }

    /**
     * Make sure that the computeMedian() method fails if all parameters are null.
     */
    @Test(expectedExceptions = NullPointerException.class)
    public void testComputeMedianNullAll() {
        Tools.computeMedian(null, null, null);
    }
}