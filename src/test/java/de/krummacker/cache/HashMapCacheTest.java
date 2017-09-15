package de.krummacker.cache;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.Serializable;

public class HashMapCacheTest {

    /**
     * Dummy implementation so that we can test the HashMapCache class.
     */
    private final static Cache<Serializable> UNDERLYING_CACHE = new Cache<Serializable>() {

        @Override
        public Serializable get(Serializable key) {
            return key;
        }

        @Override
        public void invalidate(Serializable key) {
            // do nothing
        }
    };

    private Cache<Serializable> cache;

    @BeforeMethod
    public void setUp() throws Exception {
        cache = new HashMapCache<>(UNDERLYING_CACHE);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        cache = null;
    }

    /**
     * Make sure that a second get returns the same object as the first get has returned before.
     */
    @Test
    public void testGet() throws Exception {

        //noinspection RedundantStringConstructorCall
        String first = new String("id");
        //noinspection RedundantStringConstructorCall
        String second = new String("id");

        Serializable firstResult = cache.get(first);
        Serializable secondResult = cache.get(second);
        Assert.assertTrue(firstResult == secondResult);
    }

    /**
     * Make sure that a cached object is forgotten after invalidate.
     */
    @Test
    public void testInvalidateGet() throws Exception {

        //noinspection RedundantStringConstructorCall
        String first = new String("id");
        //noinspection RedundantStringConstructorCall
        String second = new String("id");

        //noinspection StringEquality
        Assert.assertTrue(first != second);
        Serializable firstResult = cache.get(first);
        cache.invalidate(first);
        Serializable secondResult = cache.get(second);
        Assert.assertTrue(firstResult != secondResult);
    }
}