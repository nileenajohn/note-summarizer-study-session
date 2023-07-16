package cs3500.pa01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Represents a FileSorter that sorts files based on the sorting flag.
 */
public class FileSorter {
  private String flag;

  /**
   * Represents a constructor for a FileSorter.
   *
   * @param f the ordering flag that this FileSorter should sort by.
   */
  public FileSorter(String f) {
    if (f.equals("filename") || f.equals("created") || f.equals("modified")) {
      flag = f;
    } else {
      throw new IllegalArgumentException("An invalid sorting flag was entered. The sorting flags "
          + "are filename, created, or modified.");
    }
  }

  /**
   * Sorts the given ArrayList of files based on the sorting flag.
   *
   * @param files The list of files that need to be sorted.
   * @return a sorted list of files.
   */
  public ArrayList<MarkdownFile> sort(ArrayList<MarkdownFile> files) {
    if (flag.equals("filename")) {
      Collections.sort(files, new Comparator<MarkdownFile>() {
        @Override
        public int compare(MarkdownFile f1, MarkdownFile f2) {
          return f1.getName().compareTo(f2.getName());
        }
      });
    } else if (flag.equals("created")) {
      Collections.sort(files, new Comparator<MarkdownFile>() {
        @Override
        public int compare(MarkdownFile f1, MarkdownFile f2) {
          return f1.getCreatedTime().compareTo(f2.getCreatedTime());
        }
      });
    } else if (flag.equals("modified")) {
      Collections.sort(files, new Comparator<MarkdownFile>() {
        @Override
        public int compare(MarkdownFile f1, MarkdownFile f2) {
          return f1.getModifiedTime().compareTo(f2.getModifiedTime());
        }
      });
    }
    return files;
  }
}
