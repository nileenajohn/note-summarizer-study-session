package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class StudySessionViewerTest {

  /**
   * Test that printMessage prints the given string to the console.
   */
  @Test
  public void testPrintMessage() throws IOException {
    StringBuilder sb = new StringBuilder();
    new StudySessionViewer(sb).printMessage("Welcome to a study session!");
    assertEquals("Welcome to a study session!\n", sb.toString());
  }
}