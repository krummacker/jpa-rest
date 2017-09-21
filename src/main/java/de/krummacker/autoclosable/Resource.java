package de.krummacker.autoclosable;

import java.io.IOException;

/**
 * A Resource is something that can do something and that can be closed.
 */
@SuppressWarnings("WeakerAccess")
public class Resource implements AutoCloseable {

    /**
     * Creates a new Resource.
     *
     * @throws IOException if something went wrong. Should not happen.
     */
    @SuppressWarnings("RedundantThrows")
    public Resource() throws IOException {
        // intentionally doing nothing
    }

    /**
     * Attempts to do something but always fails.
     *
     * @throws IOException always thrown
     */
    public void someAction() throws IOException {
        throw new IOException();
    }

    @Override
    public void close() {
        // do  nothing
    }
}