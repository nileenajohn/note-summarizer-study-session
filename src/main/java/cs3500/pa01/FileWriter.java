package cs3500.pa01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Represents a FileWriter that takes in files and writes them to the output path.
 */
public class FileWriter {
  private Path outputPath;

  /**
   * Represents a constructor for a FileWriter.
   *
   * @param p the output path where the files should be written to.
   */
  public FileWriter(Path p) {
    outputPath = p;
  }

  /**
   * Takes in a list of sorted markdown files and parses all the files. Combines all the headings
   * and important information to an ArrayList of Strings. Combines all the questions and adds it
   * to an ArrayList of Strings. Calls the writeToFiles method so the list of combined content and
   * questions can be written to the specified output path md file and sr file.
   *
   * @param files The sorted list of files that need to be combined.
   * @throws IOException when the given outputPath can not be written to.
   */
  public void combineFiles(ArrayList<MarkdownFile> files) throws IOException {
    String allText = "";
    ArrayList<String> headings = new ArrayList<>();
    ArrayList<String> questions = new ArrayList<>();
    for (MarkdownFile f : files) {
      for (String s : f.getContent()) {
        allText += s;
        if (s.startsWith("#")) {
          headings.add("");
          headings.add(s);
        }
        while (allText.contains("[[") && allText.contains("]]")) {
          String content = allText.substring(allText.indexOf("[[") + 2, allText.indexOf("]]"));
          if (allText.contains(":::")) {
            String question = allText.substring(allText.indexOf("[[") + 2, allText.indexOf(":::"));
            String answer = allText.substring(allText.indexOf(":::") + 3, allText.indexOf("]]"));
            questions.add(question.trim() + ":::" + answer.trim() + "{H}");
          } else {
            headings.add("- " + content);
          }
          allText = allText.replace(allText.substring(allText.indexOf("[["),
              allText.indexOf("]]") + 2), "");
        }
      }
    }
    writeToFile(headings, questions);
  }

  /**
   * Takes in an ArrayList of Strings that represents the combined notes and an ArrayList of Strings
   * that represents the combined questions. Writes the combined notes to the specified output md
   * file and the combined questions to the specified output sr file.
   *
   * @param notes An ArrayList of the combined notes.
   * @param questions An ArrayList of the combined questions.
   * @throws IOException when the given outputPath can not be written to.
   */
  private void writeToFile(ArrayList<String> notes, ArrayList<String> questions)
      throws IOException {
    notes.remove(0);
    if (this.outputPath.toString().endsWith(".md")) {
      if (questions.size() > 0) {
        String pathName = outputPath.toString();
        Files.write(Path.of(pathName.substring(0, pathName.length() - 3) + ".sr"), questions);
      }
      Files.write(Path.of(outputPath.toString()), notes);
    } else {
      throw new IllegalArgumentException("The output path entered is not a valid .md file.");
    }
  }

  /**
   * Takes in a list of questions and writes all the questions to a sr file.
   *
   * @param questions The list of questions to be outputted to a sr file.
   * @throws IOException when the given outputPath can not be written to
   */
  public void updateSrFile(ArrayList<Question> questions) throws IOException {
    ArrayList<String> questionsAsString = new ArrayList<>();
    for (Question q : questions) {
      String difficultyLetter;
      if (q.getHard()) {
        difficultyLetter = "H";
      } else {
        difficultyLetter = "E";
      }
      questionsAsString.add(q.getQuestion() + ":::" + q.getAnswer() + "{" + difficultyLetter + "}");
    }
    Files.write(outputPath, questionsAsString);
  }
}