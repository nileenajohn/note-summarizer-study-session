package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the MarkdownFile class.
 */
class MarkdownFileTest {
  /**
   * Tests the getName method.
   */
  @Test
  public void testGetName() throws IOException {
    MarkdownFile vectorFile = new MarkdownFile("vectors.md",
        FileTime.from(Instant.parse("2023-04-03T03:01:00Z")),
        FileTime.from(Instant.parse("2023-04-03T03:01:00Z")),
        Files.readAllLines(Path.of("src/test/resources/sampleInputs/directory1/vectors.md"))
    );

    MarkdownFile arrayFile = new MarkdownFile("arrays.md",
        FileTime.from(Instant.parse("2023-03-31T14:53:00Z")),
        FileTime.from(Instant.parse("2023-04-04T11:37:00Z")),
        Files.readAllLines(Path.of("src/test/resources/sampleInputs/arrays.md"))
    );
    assertEquals(vectorFile.getName(), "vectors.md");
    assertEquals(arrayFile.getName(), "arrays.md");
  }

  /**
   * Tests the getCreatedTime method.
   */
  @Test
  public void testGetCreatedTime() throws IOException {
    MarkdownFile vectorFile = new MarkdownFile("vectors.md",
        FileTime.from(Instant.parse("2023-04-03T03:01:00Z")),
        FileTime.from(Instant.parse("2023-04-03T03:01:00Z")),
        Files.readAllLines(Path.of("src/test/resources/sampleInputs/directory1/vectors.md"))
    );

    MarkdownFile arrayFile = new MarkdownFile("arrays.md",
        FileTime.from(Instant.parse("2023-03-31T14:53:00Z")),
        FileTime.from(Instant.parse("2023-04-04T11:37:00Z")),
        Files.readAllLines(Path.of("src/test/resources/sampleInputs/arrays.md"))
    );
    assertEquals(vectorFile.getCreatedTime(),
        FileTime.from(Instant.parse("2023-04-03T03:01:00Z")));
    assertEquals(arrayFile.getCreatedTime(),
        FileTime.from(Instant.parse("2023-03-31T14:53:00Z")));
  }

  /**
   * Tests the getModifiedTime method.
   */
  @Test
  public void testGetModifiedTime() throws IOException {
    MarkdownFile vectorFile = new MarkdownFile("vectors.md",
        FileTime.from(Instant.parse("2023-04-03T03:01:00Z")),
        FileTime.from(Instant.parse("2023-04-03T03:01:00Z")),
        Files.readAllLines(Path.of("src/test/resources/sampleInputs/directory1/vectors.md"))
    );

    MarkdownFile arrayFile = new MarkdownFile("arrays.md",
        FileTime.from(Instant.parse("2023-03-31T14:53:00Z")),
        FileTime.from(Instant.parse("2023-04-04T11:37:00Z")),
        Files.readAllLines(Path.of("src/test/resources/sampleInputs/arrays.md"))
    );
    assertEquals(vectorFile.getModifiedTime(),
        FileTime.from(Instant.parse("2023-04-03T03:01:00Z")));
    assertEquals(arrayFile.getModifiedTime(),
        FileTime.from(Instant.parse("2023-04-04T11:37:00Z")));
  }

  /**
   * Tests the getContent method.
   */
  @Test
  public void testGetContent() throws IOException {
    MarkdownFile vectorFile = new MarkdownFile("vectors.md",
        FileTime.from(Instant.parse("2023-04-03T03:01:00Z")),
        FileTime.from(Instant.parse("2023-04-03T03:01:00Z")),
        Files.readAllLines(Path.of("src/test/resources/sampleInputs/directory1/vectors.md"))
    );

    MarkdownFile arrayFile = new MarkdownFile("arrays.md",
        FileTime.from(Instant.parse("2023-03-31T14:53:00Z")),
        FileTime.from(Instant.parse("2023-04-04T11:37:00Z")),
        Files.readAllLines(Path.of("src/test/resources/sampleInputs/arrays.md"))
    );
    assertEquals(vectorFile.getContent(),
        Files.readAllLines(Path.of("src/test/resources/sampleInputs/directory1/vectors.md")));
    assertEquals(arrayFile.getContent(),
        Files.readAllLines(Path.of("src/test/resources/sampleInputs/arrays.md")));
  }
}