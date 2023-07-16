package cs3500.pa01;

import java.io.IOException;

/**
 * Represents an interface for the Viewer of the StudySession aspect of the program.
 */
public interface Viewer {
  /**
   * Prints the given string to the user in the console.
   */
  void printMessage(String s) throws IOException;
}