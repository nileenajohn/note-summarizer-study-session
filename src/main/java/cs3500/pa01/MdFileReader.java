package cs3500.pa01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Represents a FileReader for md files.
 */
public class MdFileReader implements FileReader {
  public MdFileReader() {
  }

  /**
   * Reads in the files from the given file path and adds it to an ArrayList
   * if it is an .md file.
   *
   * @param p The path of the directory of the files that need to be read.
   * @return An ArrayList of .md files from the given directory.
   * @throws IOException when visiting a file fails
   */
  public ArrayList<MarkdownFile> readFiles(Path p) throws IOException {
    ArrayList<MarkdownFile> files;
    MarkDownCounter mdc = new MarkDownCounter();
    Files.walkFileTree(p, mdc);
    files = mdc.getList();
    return files;
  }
}
