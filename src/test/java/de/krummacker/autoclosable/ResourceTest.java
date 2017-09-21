package de.krummacker.autoclosable;

import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Tests the Resource class.
 */
public class ResourceTest {

    /**
     * Uses the Resource class in the old Java style.
     */
    @Test
    public void testOldStyle() {
        Resource resource = null;
        try {
            resource = new Resource();
            resource.someAction();
        } catch (IOException e) {
            // do  nothing
        } finally {
            if (resource != null) {
                resource.close();
            }
        }
    }

    /**
     * Uses the Resource class in a try-with-resources block.
     */
    @Test
    public void testNewStyle() {
        try (Resource resource = new Resource()) {
            resource.someAction();
        } catch (IOException e) {
            // do  nothing
        }
    }
}
