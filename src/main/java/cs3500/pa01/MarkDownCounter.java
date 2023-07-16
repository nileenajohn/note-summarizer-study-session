package cs3500.pa01;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * Represents a FileVisitor that visits files and checks whether it is an .md file.
 */
public class MarkDownCounter implements FileVisitor<Path> {
  private ArrayList<MarkdownFile> files;

  public MarkDownCounter() {
    files = new ArrayList<MarkdownFile>();
  }

  /**
   * Returns the list of .md files that have been visited.
   *
   * @return List of current .md files.
   */
  public ArrayList<MarkdownFile> getList() {
    return files;
  }

  /**
   * Determines whether a given file that has been visited is an .md file.
   *
   * @param file The path of the file that has been visited.
   * @param attr The attributes of the file that has been visited.
   * @return a FileVisitResult
   */
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attr) throws IOException {
    if (file.getFileName().toString().endsWith(".md")) {
      files.add(new MarkdownFile(file.getFileName().toString(), attr.creationTime(),
          attr.lastModifiedTime(), Files.readAllLines(file)));
    }
    return CONTINUE;
  }

  /**
   * Prints the directory that was visited.
   *
   * @param dir The path of the directory that has been visited.
   * @param exec The exception that has occurred.
   * @return a FileVisitResult
   */
  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exec) {
    System.out.println("Finishing Directory: " + dir);
    return CONTINUE;
  }

  /**
   * Prints the directory that is going to be visited.
   *
   * @param dir The path of the directory that is going to be visited.
   * @param attrs The attributes of the file that is going to be visited.
   * @return a FileVisitResult
   */
  @Override
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
    System.out.println("Starting Directory: " + dir);
    return CONTINUE;
  }

  /**
   * Prints an exception when visiting a file fails.
   *
   * @param file The path of the file that failed to be visited.
   * @param exc The exception that should be printed.
   * @return a FileVisitResult
   */
  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) {
    System.err.println(exc);
    return CONTINUE;
  }
}