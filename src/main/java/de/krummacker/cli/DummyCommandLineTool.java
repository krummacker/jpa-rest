package de.krummacker.cli;

import org.apache.commons.cli.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Optional;

/**
 * For testing the Commons CLI library. We want to have a tool that accepts the options "--verbose" and
 * "--target [value]" and prints out the choices in the form:
 * <p>
 * Verbose: yes/no
 * Target: [value]
 * <p>
 * It should also print out help if needed.
 */
public class DummyCommandLineTool {

    /**
     * Set to private in order to prevent instantiation.
     */
    private DummyCommandLineTool() {
        // Intentionally left empty
    }

    /**
     * Called by the VM on program start.
     *
     * @param args the arguments specified on the command line
     */
    public static void main(String[] args) {
        System.out.println(determineOutput(args));
    }

    /**
     * The actual computation of the text is factored out into this method to make it testable. Made package-private
     * for the same reason.
     *
     * @param args the arguments passed on the command line
     * @return the text to be printed out on stdout, including line breaks
     */
    static String determineOutput(String[] args) {
        Options options = defineOptions();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Optional<CommandLine> cmd = parseCommandLine(args, options, output);
        //noinspection OptionalIsPresent
        if (cmd.isPresent()) {
            if (cmd.get().hasOption("h")) {
                outputHelp(options, output);
            } else {
                outputResults(cmd.get(), output);
            }
        }
        return output.toString();
    }

    /**
     * Returns an Options instance that describes which command line options this tool has.
     *
     * @return the Options instance
     */
    private static Options defineOptions() {
        Options options = new Options();
        options.addOption("v", "verbose", false, "print out diagnostic messages");
        options.addOption("t", "target", true, "specifies the target");
        options.addOption("h", "help", false, "prints out this help");
        return options;
    }

    /**
     * Parses the specified arguments and returns a CommandLine object; or Optional.empty() if an error occurred.
     *
     * @param args    the arguments that were specified on the command line
     * @param options an object describing the possible options
     * @param stream  a stream to write an error message to, if any
     * @return the command line object
     */
    private static Optional<CommandLine> parseCommandLine(String[] args, Options options, ByteArrayOutputStream stream) {
        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);
            return Optional.of(cmd);
        } catch (ParseException e) {
            PrintStream printer = new PrintStream(stream);
            printer.println(e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * Prints usage instructions into the specified stream.
     *
     * @param options the options of this command line tool
     * @param stream  the stream to write the output to
     */
    private static void outputHelp(Options options, ByteArrayOutputStream stream) {
        PrintWriter printer = new PrintWriter(stream);
        String header = "Evaluate command line options and print out the result\n\n";
        String footer = "\nPlease report issues at http://example.com/issues";
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(printer, 100, DummyCommandLineTool.class.getSimpleName(), header, options,
                5, 0, footer, true);
        printer.flush();
    }

    /**
     * Prints the execution results into the specified stream.
     *
     * @param cmd    the parsed command line object
     * @param stream the stream to write the output to
     */
    private static void outputResults(CommandLine cmd, ByteArrayOutputStream stream) {
        PrintStream printer = new PrintStream(stream);
        printer.println("Verbose: " + cmd.hasOption("v"));
        printer.println("Target: " + cmd.getOptionValue("t"));
    }
}
