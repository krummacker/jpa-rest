package de.krummacker.cache;

import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * Works like a HashMapCache, but the values are wrapped in SoftReferences, causing them to be cleared automatically
 * if memory is low. This is particularly useful if a relatively small number of large objects is to be cached.
 */
public class SoftReferenceCache<T> implements Cache<T> {

    private final HashMap<Serializable, SoftReference<T>> map = new HashMap<>();
    private final Cache<T> wrapped;

    /**
     * Creates a new SoftReferenceCache that wraps the specified Cache. This Cache implementation works like the
     * HashMapCache but uses SoftReferences underneath, causing the values to be automatically removed if the system
     * memory goes low.
     *
     * @param wrapped the cache whose values shall be cached
     */
    public SoftReferenceCache(Cache<T> wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public T get(Serializable key) {

        SoftReference<T> ref = map.get(key);
        if (ref == null) {

            // Value was never requested or has been invalidated meanwhile.
            T value = wrapped.get(key);
            ref = new SoftReference<>(value);
            map.put(key, ref);
        }

        T result = ref.get();
        if (result == null) {

            // Value has been cleared due to low memory meanwhile.
            result = wrapped.get(key);
            ref = new SoftReference<>(result);
            map.put(key, ref);
        }

        return result;
    }

    @Override
    public void invalidate(Serializable key) {
        map.remove(key);
        wrapped.invalidate(key);
    }
}
