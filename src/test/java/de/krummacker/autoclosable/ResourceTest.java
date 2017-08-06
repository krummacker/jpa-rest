package de.krummacker.autoclosable;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ResourceTest {

    @BeforeMethod
    public void setUp() throws Exception {
    }

    @AfterMethod
    public void tearDown() throws Exception {
    }

    @Test
    public void testOldStyle() {

        Resource resource = null;
        try {
            resource = new Resource();
            resource.someAction();
        } catch (Exception e) {
            ;
        } finally {
            resource.close();
        }
    }

    @Test
    public void testNewStyle() {

        try (Resource resource = new Resource()) {
            resource.someAction();
        }
        catch (Exception e) {
            ;
        }
    }
}
