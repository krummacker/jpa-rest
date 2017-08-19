package de.krummacker.autoboxing;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AutoboxingTest {

    @Test
    public void testIntAutoboxing() throws Exception {
        List<Integer> li = new ArrayList<>();
        for (int i = 1; i < 50; i += 2)
            li.add(i);
        Assert.assertEquals(li.size(), 25);
    }

    @Test
    public void testDoubleAutoboxing() throws Exception {
        Set<Double> doubles = new HashSet<>();
        for (double d = 1.0; d < 50.0; d += 2.0)
            doubles.add(d);
        Assert.assertEquals(doubles.size(), 25);
    }
}