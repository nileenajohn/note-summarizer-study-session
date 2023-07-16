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
 * Represents tests for the FileSorter class.
 */
class FileSorterTest {
  ArrayList<MarkdownFile> files;
  MarkdownFile vectorFile;
  MarkdownFile arrayFile;

  /**
   * Initialize files with a creation time, modified time, and content.
   */
  @BeforeEach
  public void initializeFiles() throws IOException {
    files = new ArrayList<>();

    vectorFile = new MarkdownFile("vectors.md",
        FileTime.from(Instant.parse("2023-04-03T03:01:00Z")),
        FileTime.from(Instant.parse("2023-04-03T03:01:00Z")),
        Files.readAllLines(Path.of("src/test/resources/sampleInputs/directory1/vectors.md"))
    );

    arrayFile = new MarkdownFile("arrays.md",
        FileTime.from(Instant.parse("2023-03-31T14:53:00Z")),
        FileTime.from(Instant.parse("2023-04-04T11:37:00Z")),
        Files.readAllLines(Path.of("src/test/resources/sampleInputs/arrays.md"))
    );

    files.add(vectorFile);
    files.add(arrayFile);
  }

  /**
   * Tests that the constructor throws an exception when an invalid flag is inputted.
   */
  @Test
  public void testFileSorterConstructorInvalidFlag() {
    Throwable wrongFlagException = assertThrows(
        IllegalArgumentException.class,
        () -> new FileSorter("invalidFlag"));
    assertEquals(wrongFlagException.getMessage(), "An invalid sorting flag was entered. "
        + "The sorting flags are filename, created, or modified.");
  }

  /**
   * Tests that the sort method sorts the files correctly by filename.
   */
  @Test
  public void testSortByFilename() {
    ArrayList<MarkdownFile> sortedByFilenameOrCreated = new ArrayList<>();
    sortedByFilenameOrCreated.add(arrayFile);
    sortedByFilenameOrCreated.add(vectorFile);

    assertEquals(new FileSorter("filename").sort(files), sortedByFilenameOrCreated);
  }

  /**
   * Tests that the sort method sorts the files correctly by created date.
   */
  @Test
  public void testSortByCreated() {
    ArrayList<MarkdownFile> sortedByFilenameOrCreated = new ArrayList<>();
    sortedByFilenameOrCreated.add(arrayFile);
    sortedByFilenameOrCreated.add(vectorFile);

    assertEquals(new FileSorter("created").sort(files), sortedByFilenameOrCreated);
  }

  /**
   * Tests that the sort method sorts the files correctly by modified date.
   */
  @Test
  public void testSortByModified() {
    ArrayList<MarkdownFile> sortedByModified = new ArrayList<>();
    sortedByModified.add(vectorFile);
    sortedByModified.add(arrayFile);

    assertEquals(new FileSorter("modified").sort(files), sortedByModified);
  }

  /**
   * Tests that the sort method throws an exception when an invalid sorting flag is entered.
   */
  @Test
  public void testSortByInvalidFlag() {
    Throwable wrongFlagException = assertThrows(
        IllegalArgumentException.class,
        () -> new FileSorter("invalidFlag").sort(files));
    assertEquals(wrongFlagException.getMessage(), "An invalid sorting flag was entered. "
        + "The sorting flags are filename, created, or modified.");
  }
}