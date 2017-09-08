package de.krummacker.scanner;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Tests the Java Scanner class.
 */
public class ScannerTest {

    /**
     * Make sure that a scanned file is not empty.
     */
    @Test
    public void testScanner() {
        try {
            File source = new File("src/test/java/de/krummacker/scanner/ScannerTest.java");
            String str = new Scanner(source, "UTF-8").useDelimiter("\\A").next();
            Assert.assertNotNull(str);
            Assert.assertNotEquals(str, "");
        } catch (FileNotFoundException e) {
            Assert.fail("not able to open file", e);
        }
    }
}