package de.krummacker.streams;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Tests for the stream API. This is more for trying out what this API can do.
 */
public class StreamTest {

    @Test
    public void testIterateAndLimit() throws Exception {
        Stream<Integer> stream = Stream.iterate(0, n -> n + 1)
                .limit(10);
        List<Integer> produced = stream.collect(Collectors.toList());
        List<Integer> expected = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals(expected, produced);
    }

    @Test
    public void testSkip() throws Exception {
        Stream<Integer> stream = Stream.iterate(0, n -> n + 1)
                .limit(10)
                .skip(5);
        List<Integer> produced = stream.collect(Collectors.toList());
        List<Integer> expected = Arrays.asList(5, 6, 7, 8, 9);
        Assert.assertEquals(expected, produced);
    }

    @Test
    public void testMap() throws Exception {
        Stream<Integer> stream = Stream.iterate(0, n -> n + 1)
                .limit(10)
                .map(n -> n * 2);
        List<Integer> produced = stream.collect(Collectors.toList());
        List<Integer> expected = Arrays.asList(0, 2, 4, 6, 8, 10, 12, 14, 16, 18);
        Assert.assertEquals(expected, produced);
    }
}