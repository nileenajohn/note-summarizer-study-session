package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the FileWriter class.
 */
class FileWriterTest {

  MarkdownFile created3rdFile;
  MarkdownFile lastMdFile;
  MarkdownFile testSet;
  MarkdownFile arrayFile;
  MarkdownFile mdAddition;
  ArrayList<MarkdownFile> files;
  Path pathName;

  /**
   * Initialize Markdown files with created date, modified date, and content.
   */
  @BeforeEach
  public void initializeFiles() throws IOException {
    created3rdFile = new MarkdownFile("created3rd.md",
        FileTime.from(Instant.parse("2023-05-19T14:53:00Z")),
        FileTime.from(Instant.parse("2023-12-27T11:37:00Z")),
        Files.readAllLines(
            Path.of("src/test/resources/pa2SampleInputs/directory1/directory2/created3rd.md"))
    );

    lastMdFile = new MarkdownFile("lastMd.md",
        FileTime.from(Instant.parse("2023-05-19T14:53:00Z")),
        FileTime.from(Instant.parse("2023-12-27T11:37:00Z")),
        Files.readAllLines(
            Path.of("src/test/resources/pa2SampleInputs/lastMd.md"))
    );

    testSet = new MarkdownFile("testSet.md",
        FileTime.from(Instant.parse("2023-05-19T14:53:00Z")),
        FileTime.from(Instant.parse("2023-12-27T11:37:00Z")),
        Files.readAllLines(
            Path.of("src/test/resources/pa2SampleInputs/directory1/testSet.md"))
    );

    arrayFile = new MarkdownFile("arrays.md",
        FileTime.from(Instant.parse("2023-03-31T14:53:00Z")),
        FileTime.from(Instant.parse("2023-04-04T11:37:00Z")),
        Files.readAllLines(Path.of("src/test/resources/sampleInputs/arrays.md"))
    );

    mdAddition = new MarkdownFile("sampleQuestions.md",
        FileTime.from(Instant.parse("2023-03-31T14:53:00Z")),
        FileTime.from(Instant.parse("2023-04-04T11:37:00Z")),
        Files.readAllLines(Path.of(
            "src/test/resources/pa2SampleInputs/directory1/sampleQuestions.md")));

    files = new ArrayList<>();
    files.add(arrayFile);
    files.add(created3rdFile);
    files.add(lastMdFile);
    files.add(testSet);
  }

  /**
   * Clear out outputDirectory before each test.
   */
  @BeforeEach
  public void clearOutputDirectory() throws IOException {
    pathName = Path.of("outputDirectory.md");
    Files.write(pathName, new ArrayList<String>()); //clear out outputDirectory
  }

  /**
   * Deletes the output files before each test.
   */
  @BeforeEach
  public void deleteOutputFiles() {
    Path p = Path.of("output.md");
    Path srPath = Path.of("output.sr");

    p.toFile().delete();
    srPath.toFile().delete();
  }

  /**
   * Tests that the method writeToFile throws an exception when a path to a non .md file is given.
   */
  @Test
  public void testWrongOutputPath() {
    Throwable wrongPathException = assertThrows(
        IllegalArgumentException.class,
        () -> new FileWriter(Path.of("invalidPath.sr")).combineFiles(files));
    assertEquals(wrongPathException.getMessage(),
        "The output path entered is not a valid .md file.");

    Throwable wrongPathException1 = assertThrows(
        IllegalArgumentException.class,
        () -> new FileWriter(Path.of("invalidPath")).combineFiles(files));
    assertEquals(wrongPathException1.getMessage(),
        "The output path entered is not a valid .md file.");
  }

  /**
   * Tests the combineFiles method.
   */
  @Test
  public void testCombineFiles() throws IOException {
    // check that outputDirectory has all the content of files combined
    ArrayList<String> allCombined = new ArrayList<>();

    allCombined.add("# Java Arrays");
    allCombined.add("- An **array** is a collection of variables of the same type");
    allCombined.add("");
    allCombined.add("## Declaring an Array");
    allCombined.add("- General Form: type[] arrayName;");
    allCombined.add("- only creates a reference");
    allCombined.add("- no array has  actually been created yet");
    allCombined.add("");
    allCombined.add("## Creating an Array (Instantiation)");
    allCombined.add("- General form:  arrayName = new type[numberOfElements];");
    allCombined.add("- numberOfElements must be a positive Integer.");
    allCombined.add("- Gotcha: Array size is not  modifiable once instantiated.");
    allCombined.add("");
    allCombined.add("# Created Third");
    allCombined.add("- This md file was the 3rd to be created");
    allCombined.add("- follows arrays and vectors");
    allCombined.add("- What if ");
    allCombined.add("- there are ");
    allCombined.add("- three on a line?");
    allCombined.add("- or ");
    allCombined.add("- even ");
    allCombined.add("- four");
    allCombined.add("- ?");
    allCombined.add("");
    allCombined.add("## An Md File");
    allCombined.add("- should be outputted");
    allCombined.add("");
    allCombined.add("# This is the last md file to be created");
    allCombined.add("- should be outputted");
    allCombined.add("- If the sorting flag is created, this should be last.");
    allCombined.add("- There are multiple important phrases ");
    allCombined.add("- on this line");
    allCombined.add("- What if there ");
    allCombined.add("- are interruptions?");
    allCombined.add("");
    allCombined.add("# Another header");
    allCombined.add("");
    allCombined.add("## A Nested Header");
    allCombined.add("- ");
    allCombined.add("- I wonder if the last line was saved...");
    allCombined.add("");
    allCombined.add("# A Test Set of Questions");

    new FileWriter(pathName).combineFiles(files);
    assertEquals(allCombined, Files.readAllLines(pathName));
  }

  /**
   * Tests that the write to files method does not create an .sr file if the list of questions is
   * empty.
   */
  @Test
  public void testWriteToFiles0Questions() throws IOException {
    ArrayList<MarkdownFile> files = new ArrayList<>();
    files.add(arrayFile);
    files.add(created3rdFile);
    files.add(lastMdFile);

    FileWriter fw = new FileWriter(Path.of("output.md"));
    fw.combineFiles(files);

    assertEquals(Files.exists(Path.of("output.sr")), false);
  }

  /**
   * Tests that the write to files method creates an .sr file if the list of questions is
   * not empty.
   */
  @Test
  public void testWriteToFilesQuestions() throws IOException {
    Path srPath = Path.of("output.sr");
    assertEquals(Files.exists(srPath), false);

    Path p = Path.of("output.md");
    FileWriter fw = new FileWriter(p);
    fw.combineFiles(files);

    assertEquals(Files.exists(srPath), true);
  }

  /**
   * Tests that the write to files method creates an .sr file if the list of questions is
   * not empty.
   */
  @Test
  public void testWriteToFilesQuestionContent() throws IOException {
    Path p = Path.of("output.md");
    FileWriter fw = new FileWriter(p);

    files.remove(testSet);
    files.add(mdAddition);

    fw.combineFiles(files);

    ArrayList<String> expectedContent = new ArrayList<>();
    expectedContent.add("When is summer 1 over?:::June 26.{H}");
    expectedContent.add("When is the OOD midterm?:::May 25.{H}");
    expectedContent.add("What type of cheatsheet can you use for the OOD midterm?:::"
        + "An 8.5x5.5 paper on one side.{H}");
    expectedContent.add("What is the class code for OOD?:::CS 3500.{H}");
    expectedContent.add("Who is the professor for OOD?:::Dr. Fontenot.{H}");
    expectedContent.add("What utensil should you use to take the OOD midterm?:::A pencil.{H}");

    Path srPath = Path.of("output.sr");
    assertEquals(Files.readAllLines(srPath), expectedContent);
  }

  /**
   * Tests that the updateSrFile creates an .sr file of the given questions.
   */
  @Test
  public void testUpdateSrFile() throws IOException {
    Question q1 = new Question("What is the capital of Canada?",
        "The capital is Ottawa.", true);
    Question q2 = new Question("Which country is known as the Land of the Rising Sun?",
        "Japan.", false);
    Question q3 = new Question("What is the largest river in Africa?",
        "The largest river is the Nile River.", false);

    ArrayList<Question> questions = new ArrayList<>();
    questions.add(q1);
    questions.add(q2);
    questions.add(q3);

    ArrayList<String> expected = new ArrayList<>();
    expected.add("What is the capital of Canada?:::The capital is Ottawa.{H}");
    expected.add("Which country is known as the Land of the Rising Sun?:::Japan.{E}");
    expected.add("What is the largest river in Africa?:::The largest river is the Nile River.{E}");

    new FileWriter(Path.of("output.md")).updateSrFile(questions);
    assertEquals(Files.readAllLines(Path.of("output.md")), expected);
  }
}