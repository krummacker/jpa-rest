package de.krummacker.exceptions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RethrowExceptionsTypeCheckingTest {

    static class FirstException extends Exception {
    }

    static class SecondException extends Exception {
    }

    public void rethrowException(String exceptionName) throws FirstException, SecondException {
        try {
            if (exceptionName.equals("First")) {
                throw new FirstException();
            } else {
                throw new SecondException();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Test
    public void testRethrowExceptionsTypeCheckingTest() {
        try {
            rethrowException("First");
        } catch (Exception e) {
            return;
        }
        Assert.fail();
    }
}