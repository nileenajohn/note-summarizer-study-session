package cs3500.pa01;

import java.io.IOException;

/**
 * Represents an implementation of the IViewer interface. Responsible for printing information
 * and sending flashcards to the user in the console.
 */
public class StudySessionViewer implements Viewer {
  private Appendable appendable;

  public StudySessionViewer(Appendable appendable) {
    this.appendable = appendable;
  }

  /**
   * Prints the given string to the user in the console.
   */
  public void printMessage(String s) throws IOException {
    appendable.append(s);
    appendable.append(System.lineSeparator());
  }
}