package de.krummacker.cache;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 * A Cache that invalidates previously retrieved values after a certain timeout period.
 *
 * @param <T> the type of values to be cached
 */
public class TimeoutCache<T> implements Cache<T> {

    /**
     * We store the timestamp for each requested key, as well as the key itself.
     */
    private static class TimeKeyEntry {

        final long timestamp;
        final Serializable key;

        TimeKeyEntry(long timestamp, Serializable key) {
            this.timestamp = timestamp;
            this.key = key;
        }
    }

    /**
     * A history of which key was called when.
     */
    private final LinkedList<TimeKeyEntry> accessEntries = new LinkedList<>();

    /**
     * Needed for quick decision-making whether a certain key was already retrieved during the timeout period.
     */
    private final Set<Serializable> accessKeys = new HashSet<>();

    private final Cache<T> wrapped;
    private final long timeoutInMillis;

    public TimeoutCache(Cache<T> wrapped, long timeoutInMillis) {
        this.wrapped = wrapped;
        this.timeoutInMillis = timeoutInMillis;
    }

    /**
     * Inspects the access collections and removes entries that are older than the timeout, thereby invalidating them.
     */
    private void invalidateOldEntries() {
        long now = System.currentTimeMillis();
        Iterator<TimeKeyEntry> iterator = accessEntries.iterator();
        while (iterator.hasNext()) {
            TimeKeyEntry next = iterator.next();
            if (next.timestamp + timeoutInMillis < now) {
                wrapped.invalidate(next.key);
                accessKeys.remove(next.key);
                iterator.remove();
            } else {
                break;
            }
        }
    }

    @Override
    public T get(Serializable key) {

        invalidateOldEntries();

        // Only add a key to the entry list if it is not there yet. If there is already one then we keep using the
        // timeout of the existing one.
        if (!accessKeys.contains(key)) {
            accessKeys.add(key);
            accessEntries.addLast(new TimeKeyEntry(System.currentTimeMillis(), key));
        }

        return wrapped.get(key);
    }

    @Override
    public void invalidate(Serializable key) {
        accessKeys.remove(key);
        Iterator<TimeKeyEntry> iterator = accessEntries.iterator();
        while (iterator.hasNext()) {
            TimeKeyEntry next = iterator.next();
            if (next.key.equals(key)) {
                iterator.remove();
                break;
            }
        }
        wrapped.invalidate(key);

        invalidateOldEntries();
    }
}
