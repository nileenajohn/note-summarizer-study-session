package cs3500.pa01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a FileReader for sr files.
 */
public class SrFileReader implements FileReader {
  private Random rand;

  public SrFileReader(Random rand) {
    this.rand = rand;
  }

  /**
   * Reads in an .sr file and creates a question bank containing all the questions in the file.
   *
   * @param p The path of the .sr file.
   * @return A QuestionBank containing all the questions in the .sr file.
   * @throws IOException when visiting a file fails
   */
  public QuestionBank readFile(Path p) throws IOException {
    List<String> content = Files.readAllLines(p);
    ArrayList<Question> questions = new ArrayList<>();
    for (String s : content) {
      String question = s.substring(0, s.indexOf(":::"));
      String answer = s.substring(s.indexOf(":::") + 3, s.indexOf("{"));
      Boolean hard;
      if (s.substring(s.indexOf("{") + 1, s.indexOf("}")).equals("H")) {
        hard = true;
      } else {
        hard = false;
      }
      questions.add(new Question(question, answer, hard));
    }
    QuestionBank qb = new QuestionBank(questions, this.rand);
    return qb;
  }
}
