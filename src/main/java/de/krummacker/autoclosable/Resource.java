package de.krummacker.autoclosable;

public class Resource implements AutoCloseable {

    public Resource() throws Exception {
        ;
    }

    public void someAction() throws Exception {
        throw new Exception();
    }

    @Override
    public void close() {
        ;
    }
}