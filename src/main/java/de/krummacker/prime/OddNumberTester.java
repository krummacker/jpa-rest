package de.krummacker.prime;

/**
 * An OddNumberTester can find out if a given number is odd.
 */
public class OddNumberTester {

    /**
     * Creates an OddNumberTester.
     */
    public OddNumberTester() {
        // Intentionally left empty
    }

    /**
     * Finds out if the specified number is odd.
     *
     * @param n the number to be tested
     * @return true if the number is odd
     */
    public boolean isOdd(int n) {
        return n % 2 != 0;
    }
}
