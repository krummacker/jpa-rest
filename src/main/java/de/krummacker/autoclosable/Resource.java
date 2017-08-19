package de.krummacker.autoclosable;

/**
 * A {@Resource} is something that can do something and that can be closed.
 */
public class Resource implements AutoCloseable {

    /**
     * Creates a new {@Resource}.
     *
     * @throws Exception if something went wrong. Should not happen.
     */
    public Resource() throws Exception {
    }

    /**
     * Attempts to do something but always fails.
     *
     * @throws Exception always thrown
     */
    public void someAction() throws Exception {
        throw new Exception();
    }

    @Override
    public void close() {
        ;
    }
}