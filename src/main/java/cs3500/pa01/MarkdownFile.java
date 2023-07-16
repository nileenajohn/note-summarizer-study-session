package cs3500.pa01;

import java.nio.file.attribute.FileTime;
import java.util.List;

/**
 * Represents a MarkdownFile with a filename, creation time, modified time, and content in the file.
 */
public class MarkdownFile {
  private String filename;
  private FileTime created;
  private FileTime modified;
  private List<String> content;

  /**
   * Represents a constructor for a MarkdownFile.
   *
   * @param f a filename for the MarkdownFile
   * @param c a creation time for the MarkdownFile
   * @param m a modified time for the MarkdownFile
   * @param content the content contained in the MarkdownFile
   */
  public MarkdownFile(String f, FileTime c, FileTime m, List<String> content) {
    filename = f;
    created = c;
    modified = m;
    this.content = content;
  }

  /**
   * Returns the name of the MarkdownFile.
   *
   * @return the name of the MarkdownFile.
   */
  public String getName() {
    return filename;
  }

  /**
   * Returns the created time of the MarkdownFile.
   *
   * @return the created time of the MarkdownFile.
   */
  public FileTime getCreatedTime() {
    return created;
  }

  /**
   * Returns the modified time of the MarkdownFile.
   *
   * @return the modified time of the MarkdownFile.
   */
  public FileTime getModifiedTime() {
    return modified;
  }

  /**
   * Returns the content in the MarkdownFile as an ArrayList.
   *
   * @return the content in the MarkdownFile.
   */
  public List<String> getContent() {
    return content;
  }
}