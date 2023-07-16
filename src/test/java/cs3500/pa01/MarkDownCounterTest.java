package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the MarkDownCounter class.
 */
class MarkDownCounterTest {
  /**
   * Tests the getList and visitFiles methods.
   */
  @Test
  public void testGetListVisitFiles() throws IOException {
    MarkDownCounter mdc = new MarkDownCounter();
    ArrayList<MarkdownFile> files = new ArrayList<>();
    assertEquals(mdc.getList(), files);

    Path p1 = Path.of("src/test/resources/sampleInputs/arrays.md");
    assertEquals(mdc.visitFile(p1, Files.readAttributes(p1, BasicFileAttributes.class)),
        FileVisitResult.CONTINUE);

    assertEquals(mdc.getList().size(), 1);
    assertEquals(mdc.getList().get(0).getName(), "arrays.md");
    assertEquals(mdc.getList().get(0).getContent(),
        Files.readAllLines(Path.of("src/test/resources/sampleInputs/arrays.md")));

    Path p2 = Path.of("src/test/resources/sampleInputs/directory1/vectors.md");
    assertEquals(mdc.visitFile(p2, Files.readAttributes(p2, BasicFileAttributes.class)),
        FileVisitResult.CONTINUE);

    assertEquals(mdc.getList().size(), 2);
    assertEquals(mdc.getList().get(0).getName(), "arrays.md");
    assertEquals(mdc.getList().get(0).getContent(),
        Files.readAllLines(Path.of("src/test/resources/sampleInputs/arrays.md")));
    assertEquals(mdc.getList().get(1).getName(), "vectors.md");
    assertEquals(mdc.getList().get(1).getContent(),
        Files.readAllLines(Path.of("src/test/resources/sampleInputs/directory1/vectors.md")));

    Path p3 = Path.of("src/test/resources/sampleInputs/ignoreMe.txt");
    assertEquals(mdc.visitFile(p3, Files.readAttributes(p3, BasicFileAttributes.class)),
        FileVisitResult.CONTINUE);

    assertEquals(mdc.getList().size(), 2);
    assertEquals(mdc.getList().get(0).getName(), "arrays.md");
    assertEquals(mdc.getList().get(0).getContent(),
        Files.readAllLines(Path.of("src/test/resources/sampleInputs/arrays.md")));
    assertEquals(mdc.getList().get(1).getName(), "vectors.md");
    assertEquals(mdc.getList().get(1).getContent(),
        Files.readAllLines(Path.of("src/test/resources/sampleInputs/directory1/vectors.md")));
  }
}