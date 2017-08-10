package de.krummacker.optional;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

public class EntityRetrieverTest {

    private EntityRetriever entityRetriever;

    @BeforeMethod
    public void setUp() throws Exception {
        entityRetriever = new EntityRetriever();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        entityRetriever = null;
    }

    @Test
    public void testCreateRandomStringHappyCase() {
        Optional<String> result = entityRetriever.createRandomString(5);
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(result.get().length(), 5);
    }

    @Test
    public void testCreateRandomStringNegative() {
        Optional<String> result = entityRetriever.createRandomString(-5);
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void testCreateRandomStringTooLarge() {
        Optional<String> result = entityRetriever.createRandomString(Integer.MAX_VALUE);
        Assert.assertFalse(result.isPresent());
    }
}
