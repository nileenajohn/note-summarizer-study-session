package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the Driver class.
 */
class DriverTest {
  /**
   * Clears out outputDirectory before each test.
   */
  @BeforeEach
  public void clearOutputDirectory() throws IOException {
    Path p = Path.of("outputDirectory.md");
    Files.write(p, new ArrayList<String>()); //clear out outputDirectory
  }

  /**
   * Set all the questions in questions.sr to easy before each test.
   */
  @BeforeEach
  public void setQuestionsFile() throws IOException {
    ArrayList<String> content = new ArrayList<>();
    content.add("When is summer 1 over?:::June 26.{H}");
    content.add("When is the OOD midterm?:::May 25.{E}");
    content.add("What type of cheatsheet can you use for the OOD midterm?:::"
        + "An 8.5x5.5 paper on one side.{H}");
    content.add("What is the class code for OOD?:::CS 3500.{H}");
    content.add("Who is the professor for OOD?:::Dr. Fontenot.{E}");
    content.add("What utensil should you use to take the OOD midterm?:::A pencil.{E}");

    Path p = Path.of(
        "src/test/resources/pa2SampleInputs/directory1/directory2/sampleQuestions.sr");
    Files.write(p, content);
  }

  /**
   * Tests that the main method throws an exception when only 1 argument is given.
   */
  @Test
  public void testMainForOnly1Arg() {
    String[] only1Argument = new String[1];
    only1Argument[0] = "src/test/resources/sampleInputs";
    Throwable only1ArgumentException = assertThrows(
        IllegalArgumentException.class,
        () -> new Driver().main(only1Argument));
    assertEquals(only1ArgumentException.getMessage(), "Please either input the path to the "
        + "directory of markdown files,  the ordering flag, and the output path or no arguments to "
        + "start a study session.");
  }

  /**
   * Tests that the main method throws an exception when only 2 arguments are given.
   */
  @Test
  public void testMainForOnly2Arg() throws IOException {
    String[] only2Args = new String[2];
    only2Args[0] = "src/test/resources/sampleInputs";
    only2Args[1] = "filename";
    Throwable only1ArgumentException = assertThrows(
        IllegalArgumentException.class,
        () -> new Driver().main(only2Args));
    assertEquals(only1ArgumentException.getMessage(), "Please either input the path to the "
        + "directory of markdown files,  the ordering flag, and the output path or no arguments to "
        + "start a study session.");
  }

  /**
   * Tests that the main method throws an exception when 4 (more than 3) arguments are given.
   */
  @Test
  public void testMainFor4Args() throws IOException {
    String[] fourArgs = new String[4];
    fourArgs[0] = "src/test/resources/sampleInputs";
    fourArgs[1] = "filename";
    fourArgs[2] = "outputDirectory.md";
    fourArgs[3] = "outputDirectory.sr";
    Throwable only1ArgumentException = assertThrows(
        IllegalArgumentException.class,
        () -> new Driver().main(fourArgs));
    assertEquals(only1ArgumentException.getMessage(), "Please either input the path to the "
        + "directory of markdown files,  the ordering flag, and the output path or no arguments to "
        + "start a study session.");
  }

  /**
   * Tests that the main method works correctly when 3 correct arguments are inputted.
   */
  @Test
  public void testMainFor3Args() throws IOException {
    String[] args = new String[3];
    args[0] = "src/test/resources/sampleInputs";
    args[1] = "filename";
    args[2] = "outputDirectory.md";

    new Driver().main(args);

    ArrayList<String> filenameExpected = new ArrayList<>();

    filenameExpected.add("# Java Arrays");
    filenameExpected.add("- An **array** is a collection of variables of the same type");
    filenameExpected.add("");
    filenameExpected.add("## Declaring an Array");
    filenameExpected.add("- General Form: type[] arrayName;");
    filenameExpected.add("- only creates a reference");
    filenameExpected.add("- no array has  actually been created yet");
    filenameExpected.add("");
    filenameExpected.add("## Creating an Array (Instantiation)");
    filenameExpected.add("- General form:  arrayName = new type[numberOfElements];");
    filenameExpected.add("- numberOfElements must be a positive Integer.");
    filenameExpected.add("- Gotcha: Array size is not  modifiable once instantiated.");
    filenameExpected.add("");
    filenameExpected.add("# Created Third");
    filenameExpected.add("- This md file was the 3rd to be created");
    filenameExpected.add("- follows arrays and vectors");
    filenameExpected.add("- What if ");
    filenameExpected.add("- there are ");
    filenameExpected.add("- three on a line?");
    filenameExpected.add("- or ");
    filenameExpected.add("- even ");
    filenameExpected.add("- four");
    filenameExpected.add("- ?");
    filenameExpected.add("");
    filenameExpected.add("## An Md File");
    filenameExpected.add("- should be outputted");
    filenameExpected.add("");
    filenameExpected.add("# This is the last md file to be created");
    filenameExpected.add("- should be outputted");
    filenameExpected.add("- If the sorting flag is created, this should be last.");
    filenameExpected.add("- There are multiple important phrases ");
    filenameExpected.add("- on this line");
    filenameExpected.add("- What if there ");
    filenameExpected.add("- are interruptions?");
    filenameExpected.add("");
    filenameExpected.add("# Another header");
    filenameExpected.add("");
    filenameExpected.add("## A Nested Header");
    filenameExpected.add("- ");
    filenameExpected.add("- I wonder if the last line was saved...");
    filenameExpected.add("");
    filenameExpected.add("# Vectors");
    filenameExpected.add("- Vectors act like resizable arrays");
    filenameExpected.add("");
    filenameExpected.add("## Declaring a vector");
    filenameExpected.add("- General Form: Vector<type> v = new Vector();");
    filenameExpected.add("- type needs to be a valid reference type");
    filenameExpected.add("");
    filenameExpected.add("## Adding an element to a vector");
    filenameExpected.add("- v.add(object of type);");

    assertEquals(Files.readAllLines(Path.of("outputDirectory.md")), filenameExpected);
  }

  /**
   * Tests that the main method works correctly when an incorrect input path is given.
   */
  @Test
  public void testMainForWrongInputFile() throws IOException {
    String[] wrongInput = new String[3];
    wrongInput[0] = "src/test/resources/wrongInputFile";
    wrongInput[1] = "filename";
    wrongInput[2] = "outputDirectory.md";
    Throwable wrongInputException = assertThrows(
        IllegalArgumentException.class,
        () -> new Driver().main(wrongInput));
    assertEquals(wrongInputException.getMessage(), "The given input path does not exist.");
  }

  /**
   * Tests that the main method works correctly when an incorrect output path is given.
   */
  @Test
  public void testMainForWrongOutput() {
    String[] wrongInput = new String[3];
    wrongInput[0] = "src/test/resources/sampleInputs";
    wrongInput[1] = "filename";
    wrongInput[2] = "outputDirectory";
    Throwable wrongInputException = assertThrows(
        IllegalArgumentException.class,
        () -> new Driver().main(wrongInput));
    assertEquals(wrongInputException.getMessage(),
        "The output path entered is not a valid .md file.");
  }

  /**
   * Tests that the main method works correctly when an incorrect flag (not filename, modified, or
   * created) is given.
   */
  @Test
  public void testMainForWrongFlag() {
    String[] wrongInput = new String[3];
    wrongInput[0] = "src/test/resources/sampleInputs";
    wrongInput[1] = "createdAndModified";
    wrongInput[2] = "outputDirectory.md";
    Throwable wrongInputException = assertThrows(
        IllegalArgumentException.class,
        () -> new Driver().main(wrongInput));
    assertEquals(wrongInputException.getMessage(),
        "An invalid sorting flag was entered. The sorting flags "
            + "are filename, created, or modified.");
  }

  /**
   * Tests that the main method correctly starts the study session when 0 arguments are inputted.
   */
  @Test
  public void testMain0Args() throws IOException {
    // Create a ByteArrayOutputStream to capture the console output
    /*ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    // Create a PrintStream based on the ByteArrayOutputStream
    PrintStream printStream = new PrintStream(outputStream);

    // Redirect the standard output to the custom PrintStream
    System.setOut(printStream);

    String[] args = new String[0];

    Driver.main(args);

    // Get the captured output as a string
    String consoleOutput = outputStream.toString();

    // Print the captured output
    assertEquals("", consoleOutput);*/
  }
}