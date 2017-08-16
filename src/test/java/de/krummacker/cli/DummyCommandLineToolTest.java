package de.krummacker.cli;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DummyCommandLineToolTest {

    /**
     * Make sure that the help text contains usage instructions.
     */
    @Test
    public void testHelp() throws Exception {
        String[] args = {"--help"};
        String output = DummyCommandLineTool.determineOutput(args);
        Assert.assertTrue(output.startsWith("usage"));
    }

    /**
     * Make sure that the target value is rendered correctly.
     */
    @Test
    public void testOnlyTarget() throws Exception {
        String[] args = {"--target", "foobar"};
        String expected = "Verbose: false\nTarget: foobar\n";
        String output = DummyCommandLineTool.determineOutput(args);
        Assert.assertEquals(output, expected);
    }

    /**
     * Make sure that both target value and verbose are rendered correctly.
     */
    @Test
    public void testTargetAndVerbose() throws Exception {
        String[] args = {"--target", "foobar", "--verbose"};
        String expected = "Verbose: true\nTarget: foobar\n";
        String output = DummyCommandLineTool.determineOutput(args);
        Assert.assertEquals(output, expected);
    }

    /**
     * Make sure that short options work too.
     */
    @Test
    public void testShortOptions() throws Exception {
        String[] args = {"-t", "foobar", "-v"};
        String expected = "Verbose: true\nTarget: foobar\n";
        String output = DummyCommandLineTool.determineOutput(args);
        Assert.assertEquals(output, expected);
    }

    /**
     * Make sure illegal arguments cause an error.
     */
    @Test
    public void testIllegalArguments() throws Exception {
        String[] args = {"-x", "y", "-z"};
        String expected = "Unrecognized option: -x\n";
        String output = DummyCommandLineTool.determineOutput(args);
        Assert.assertEquals(output, expected);
    }
}