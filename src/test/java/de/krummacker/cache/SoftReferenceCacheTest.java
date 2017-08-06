package de.krummacker.cache;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.Serializable;

public class SoftReferenceCacheTest {

    /**
     * Dummy implementation so that we can test the HashMapCache class.
     */
    private final static Cache<String> UNDERLYING_CACHE = new Cache<String>() {

        @Override
        public String get(Serializable key) {

            // This mock implementation returns a huge string that is constructed from 1,000,000 repeated keys.
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 1000000; i++) {
                builder.append(key.toString());
            }
            return builder.toString();
        }

        @Override
        public void invalidate(Serializable key) {
            // do nothing
        }
    };

    private SoftReferenceCache<String> cache;

    @BeforeMethod
    public void setUp() throws Exception {
        cache = new SoftReferenceCache<>(UNDERLYING_CACHE);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        cache = null;
    }

    /**
     * Make sure that a second get returns then same object as the first get has returned before.
     */
    @Test
    public void testGet() throws Exception {

        // Intentionally creating different String instances
        @SuppressWarnings("RedundantStringConstructorCall") String first = new String("id");
        @SuppressWarnings("RedundantStringConstructorCall") String second = new String("id");

        String firstResult = cache.get(first);
        String secondResult = cache.get(second);

        // Intentionally comparing identity, not equality
        //noinspection StringEquality
        Assert.assertTrue(firstResult == secondResult);
    }

    /**
     * Make sure that a cached object is forgotten after invalidate.
     */
    @Test
    public void testInvalidateGet() throws Exception {

        // Intentionally creating different String instances
        @SuppressWarnings("RedundantStringConstructorCall") String first = new String("id");
        @SuppressWarnings("RedundantStringConstructorCall") String second = new String("id");

        // Intentionally comparing identity, not equality
        //noinspection StringEquality
        Assert.assertTrue(first != second);

        String firstResult = cache.get(first);
        cache.invalidate(first);
        String secondResult = cache.get(second);

        // Intentionally comparing identity, not equality
        //noinspection StringEquality
        Assert.assertTrue(firstResult != secondResult);
    }
}