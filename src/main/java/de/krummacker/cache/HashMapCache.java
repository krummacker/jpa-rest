package de.krummacker.cache;

import java.io.Serializable;
import java.util.HashMap;

/**
 * A HashMapCache retrieves values from an underlying {@link Cache} and then stores them in a {@link HashMap} in local
 * memory. This will improve the retrieval times for subsequent lookups but bears the risk of the values becoming
 * outdated.
 */
public class HashMapCache<T> implements Cache<T> {

    private final HashMap<Serializable, T> map = new HashMap<>();
    private final Cache<T> wrapped;

    /**
     * Creates a new HashMapCache that wraps the specified Cache. Use this constructor if you want to cache the values
     * of this underlying cache.
     *
     * @param wrapped the cache whose values shall be cached
     */
    public HashMapCache(Cache<T> wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public T get(Serializable key) {
        return map.computeIfAbsent(key, wrapped::get);
    }

    @Override
    public void invalidate(Serializable key) {
        map.remove(key);
        wrapped.invalidate(key);
    }
}
