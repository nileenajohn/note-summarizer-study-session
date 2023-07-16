package cs3500.pa01;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

/**
 * Responsible for reading in command line arguments, starting the appropriate functionality:
 * either the note condensing or study session aspect of the program, and saving the inputPath,
 * flag, and outputPath arguments into fields to be used throughout the program.
 */
public class Driver {
  /**
   * Reads in command line arguments (input path to notes folder, flag to indicate how combined the
   * notes should be sorted, and output path to write combined notes). Based on whether 0 or 3 args
   * are inputted, determines whether the note condensing or study session aspect of the program
   * should be used. If 3 args, note condensing program begins: creates a new FileReader
   * and calls readFiles method giving it the inputPath, creates a fileSorter object using the given
   * flag and calls sort() giving it the files field of markDownCounter, creates a new fileWriter
   * object and calls combineFiles giving it the output of sort(). If 0 args, create a new
   * StudySessionController and call startStudySession().
   *
   * @param args The list of command line arguments.
   * @throws IOException when readFiles fails to visit a file
   */
  public static void main(String[] args) throws IOException {
    if (!(args.length == 3 || args.length == 0)) {
      throw new IllegalArgumentException(
          "Please either input the path to the directory of markdown files,  the ordering flag, "
              + "and the output path or no arguments to start a study session.");
    } else if (args.length == 3) {
      Path inputPath = Path.of(args[0]);
      String flag = args[1];
      Path outputPath = Path.of(args[2]);

      if (!Files.exists(inputPath)) {
        throw new IllegalArgumentException("The given input path does not exist.");
      } else {
        FileSorter fs = new FileSorter(flag);
        FileWriter fw = new FileWriter(outputPath);
        fw.combineFiles(fs.sort(new MdFileReader().readFiles(inputPath)));
      }
    } else if (args.length == 0) {
      new StudySessionController(new InputStreamReader(System.in), System.out,
          new Random()).startStudySession();
    }
  }
}