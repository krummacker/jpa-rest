package de.krummacker.cache;

import java.io.Serializable;

/**
 * A class implementing the Cache interface returns values that are identified by keys. While doing so, implementations
 * are encouraged to follow the Decorator pattern for adding functionality, e.g. the MongoDBCache retrieves values
 * from a MongoDB database, the {@link HashMapCache} wraps a MongoDBCache and stores retrieved values also in memory,
 * and the {@link TimeoutCache} invalidates values after a certain time has passed after their last retrieval.
 * <p>
 * Keys are {@link Serializable} so that they can be sent across the network to a JMS server.
 */
public interface Cache<T> {

    /**
     * Returns a cached value.
     *
     * @param key identifies the value to be returned
     * @return the cached value
     */
    T get(Serializable key);

    /**
     * Removes any saved state pertaining to this key from this class. This method can be used to remove invalid
     * values from the cache. If this {@link Cache} wraps another Cache then it also calls the other Cache's
     * invalidate() method.
     *
     * @param key identifies the value to be invalidated
     */
    void invalidate(Serializable key);
}
