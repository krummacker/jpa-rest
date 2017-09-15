package de.krummacker.collections;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

/**
 * Tests the Bag class.
 */
public class BagTest {

    /**
     * Make sure that we can add strings and that they are there.
     */
    @Test
    public void testAddStringsAndTestContains() {

        Bag<String> bag = new Bag<>();
        Assert.assertTrue(bag.isEmpty());
        Assert.assertEquals(bag.size(), 0);

        String example = "kobylamamalybok";
        bag.add(example);
        Assert.assertFalse(bag.isEmpty());
        Assert.assertEquals(bag.size(), 1);
        Assert.assertTrue(bag.contains(example));
    }

    /**
     * Make sure that we can add the same object twice.
     */
    @Test
    public void testAddTwoObjects() {

        Bag<Object> bag = new Bag<>();
        Object example = new Object();
        bag.add(example);
        bag.add(example);

        Assert.assertFalse(bag.isEmpty());
        Assert.assertEquals(bag.size(), 2);
        Assert.assertTrue(bag.contains(example));
    }

    /**
     * Make sure that we can use foreach with Bags.
     */
    @Test
    public void testForEach() {

        Bag<Integer> bag = new Bag<>();
        bag.add(1);
        bag.add(2);
        bag.add(3);

        for (Integer i : bag) {
            Assert.assertNotNull(i);
        }
    }

    /**
     * Make sure that we can use an iterator of a Bag.
     */
    @Test
    public void testIteratorHappyCase() {

        List<String> source = new ArrayList<>(4);
        source.add("one");
        source.add("two");
        source.add("two");
        source.add("three");

        Bag<String> bag = new Bag<>();
        bag.addAll(source);
        Iterator<String> iterator = bag.iterator();
        Assert.assertNotNull(iterator);

        while (iterator.hasNext()) {
            String n = iterator.next();
            Assert.assertTrue(source.contains(n));
        }
        Assert.assertFalse(iterator.hasNext());
    }

    /**
     * Make sure that we can use an iterator of a Bag to remove elements.
     */
    @Test
    public void testIteratorRemove() {

        Bag<String> bag = new Bag<>();
        bag.add("one");
        bag.add("two");
        bag.add("two");
        bag.add("three");
        Assert.assertEquals(bag.size(), 4);

        Iterator<String> iterator = bag.iterator();
        Assert.assertNotNull(iterator.next());
        Assert.assertNotNull(iterator.next());
        iterator.remove();
        Assert.assertEquals(bag.size(), 3);
    }

    /**
     * Make sure that a Bag iterator fails correctly when used too long.
     */
    @Test
    public void testIteratorTooFar() {

        Bag<String> bag = new Bag<>();
        bag.add("foo");

        Iterator<String> iterator = bag.iterator();
        Assert.assertNotNull(iterator);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertNotNull(iterator.next());
        Assert.assertFalse(iterator.hasNext());
        try {
            iterator.next();
            Assert.fail("expected NoSuchElementException but was not thrown");
        } catch (NoSuchElementException e) {
            // This is expected.
        }
    }

    /**
     * Make sure that a Bag iterator fails correctly when the Bag is modified in-between.
     */
    @Test
    public void testIteratorConcurrentModification() {

        Bag<String> bag = new Bag<>();
        bag.add("foo");
        bag.add("bar");

        Iterator<String> iterator = bag.iterator();
        Assert.assertNotNull(iterator.next());

        bag.add("baz");

        try {
            iterator.next();
            Assert.fail("expected ConcurrentModificationException but was not thrown");
        } catch (ConcurrentModificationException e) {
            // This is expected.
        }
    }
}
