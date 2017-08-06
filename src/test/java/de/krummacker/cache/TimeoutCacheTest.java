package de.krummacker.cache;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class TimeoutCacheTest {

    /**
     * A mock Cache that the TimeoutCache can wrap, and that also allows checking what was invalidated.
     */
    private final class MockCache implements Cache<Serializable> {

        private Set<Serializable> invalidatedKeys = new HashSet<>();

        @Override
        public Serializable get(Serializable key) {
            return key;
        }

        @Override
        public void invalidate(Serializable key) {
            invalidatedKeys.add(key);
        }

        private boolean isInvalidated() {
            return !invalidatedKeys.isEmpty();
        }
    }

    private final static long TIMEOUT_IN_MILLIS = 10;

    private MockCache mockCache;
    private Cache<Serializable> timeoutCache;

    @BeforeMethod
    public void setUp() throws Exception {
        mockCache = new MockCache();
        timeoutCache = new TimeoutCache<>(mockCache, TIMEOUT_IN_MILLIS);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        mockCache = null;
        timeoutCache = null;
    }

    /**
     * Make sure that the underlying cache is invalidated after the timeout once the same object is requested a second
     * time.
     */
    @Test
    public void testWaitGetSameObjectAndCheckInvalidated() throws Exception {

        String id = "id";
        Serializable first = timeoutCache.get(id);

        Assert.assertEquals(id, first);
        Assert.assertFalse(mockCache.isInvalidated());

        Thread.sleep(TIMEOUT_IN_MILLIS + 1);
        Serializable second = timeoutCache.get(id);

        Assert.assertEquals(id, second);
        Assert.assertTrue(mockCache.isInvalidated());
        Assert.assertEquals(mockCache.invalidatedKeys.size(), 1);
        Assert.assertEquals(mockCache.invalidatedKeys.iterator().next(), id);
    }

    /**
     * Make sure that the underlying cache is invalidated after the timeout once a different object is requested.
     */
    @Test
    public void testWaitGetOtherObjectAndCheckInvalidated() throws Exception {

        String firstId = "firstId";
        Object first = timeoutCache.get(firstId);

        Assert.assertEquals(firstId, first);
        Assert.assertFalse(mockCache.isInvalidated());

        Thread.sleep(TIMEOUT_IN_MILLIS + 1);
        String secondId = "secondId";
        Object second = timeoutCache.get(secondId);

        Assert.assertEquals(secondId, second);
        Assert.assertTrue(mockCache.isInvalidated());
        Assert.assertEquals(mockCache.invalidatedKeys.size(), 1);
        Assert.assertEquals(mockCache.invalidatedKeys.iterator().next(), firstId);
    }

    /**
     * Make sure that 3 gets lead to 3 invalidates after the timeout.
     */
    @Test
    public void test3Objects3Invalidates() throws Exception {

        timeoutCache.get("id1");
        timeoutCache.get("id2");
        timeoutCache.get("id3");
        Thread.sleep(TIMEOUT_IN_MILLIS + 1);
        timeoutCache.get("id4");

        Assert.assertTrue(mockCache.isInvalidated());
        Assert.assertEquals(mockCache.invalidatedKeys.size(), 3);
    }
}