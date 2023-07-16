package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SrFileReaderTest {

  ArrayList<Question> expectedQuestions;
  Question q1;
  Question q2;
  Question q3;
  Question q4;
  Question q5;
  Question q6;
  QuestionBank expected;
  Path pathName;
  SrFileReader srReader;

  /**
   * Set up the expected questions that should match the questions in the QuestionBank created
   * by readFiles.
   */
  @BeforeEach
  public void setUpQuestions() {
    expectedQuestions = new ArrayList<>();
    q1 = new Question("When is summer 1 over?", "June 26.", true);
    q2 = new Question("When is the OOD midterm?", "May 25.", false);
    q3 = new Question("What type of cheatsheet can you use for the OOD midterm?",
        "An 8.5x5.5 paper on one side.", true);
    q4 = new Question("What is the class code for OOD?", "CS 3500.",
        true);
    q5 = new Question("Who is the professor for OOD?", "Dr. Fontenot.",
        false);
    q6 = new Question("What utensil should you use to take the OOD midterm?",
        "A pencil.", false);
    expectedQuestions.add(q1);
    expectedQuestions.add(q2);
    expectedQuestions.add(q3);
    expectedQuestions.add(q4);
    expectedQuestions.add(q5);
    expectedQuestions.add(q6);
    expected = new QuestionBank(expectedQuestions, new Random(4));
    pathName = Path.of(
        "src/test/resources/pa2SampleInputs/directory1/directory2/sampleQuestions.sr");
    srReader = new SrFileReader(new Random(4));
  }

  /**
   * Test that readFile correctly takes a path to an .sr file and creates a question bank of the
   * size of the number of questions in the file.
   */
  @Test
  public void testReadFileCheckSize() throws IOException {
    // Check that the size of the output is 6 questions which is how many questions in .sr file
    assertEquals(srReader.readFile(pathName).getAllQuestions().size(),
        6);
  }

  /**
   * Test that readFile correctly takes a path to an .sr file and creates a question bank with all
   * the correct questions corresponding to the questions contained in the file.
   */
  @Test
  public void testReadFileCheckQuestions() throws IOException {
    assertEquals(srReader.readFile(pathName).getAllQuestions().get(0).getQuestion(),
        expectedQuestions.get(0).getQuestion());
    assertEquals(srReader.readFile(pathName).getAllQuestions().get(1).getQuestion(),
        expectedQuestions.get(1).getQuestion());
    assertEquals(srReader.readFile(pathName).getAllQuestions().get(2).getQuestion(),
        expectedQuestions.get(2).getQuestion());
    assertEquals(srReader.readFile(pathName).getAllQuestions().get(3).getQuestion(),
        expectedQuestions.get(3).getQuestion());
    assertEquals(srReader.readFile(pathName).getAllQuestions().get(4).getQuestion(),
        expectedQuestions.get(4).getQuestion());
    assertEquals(srReader.readFile(pathName).getAllQuestions().get(5).getQuestion(),
        expectedQuestions.get(5).getQuestion());
  }

  /**
   * Test that readFile correctly takes a path to an .sr file and creates a question bank with all
   * the correct answers corresponding to the questions in the .sr file.
   */
  @Test
  public void testReadFileCheckAnswers() throws IOException {
    assertEquals(srReader.readFile(pathName).getAllQuestions().get(0).getAnswer(),
        expectedQuestions.get(0).getAnswer());
    assertEquals(srReader.readFile(pathName).getAllQuestions().get(1).getAnswer(),
        expectedQuestions.get(1).getAnswer());
    assertEquals(srReader.readFile(pathName).getAllQuestions().get(2).getAnswer(),
        expectedQuestions.get(2).getAnswer());
    assertEquals(srReader.readFile(pathName).getAllQuestions().get(3).getAnswer(),
        expectedQuestions.get(3).getAnswer());
    assertEquals(srReader.readFile(pathName).getAllQuestions().get(4).getAnswer(),
        expectedQuestions.get(4).getAnswer());
    assertEquals(srReader.readFile(pathName).getAllQuestions().get(5).getAnswer(),
        expectedQuestions.get(5).getAnswer());
  }

  /**
   * Test that readFile correctly takes a path to an .sr file and creates a question bank with all
   * the correct difficulty levels corresponding to the questions in the .sr file.
   */
  @Test
  public void testReadFileCheckDifficulty() throws IOException {
    assertEquals(srReader.readFile(pathName).getAllQuestions().get(0).getHard(),
        expectedQuestions.get(0).getHard());
    assertEquals(srReader.readFile(pathName).getAllQuestions().get(1).getHard(),
        expectedQuestions.get(1).getHard());
    assertEquals(srReader.readFile(pathName).getAllQuestions().get(2).getHard(),
        expectedQuestions.get(2).getHard());
    assertEquals(srReader.readFile(pathName).getAllQuestions().get(3).getHard(),
        expectedQuestions.get(3).getHard());
    assertEquals(srReader.readFile(pathName).getAllQuestions().get(4).getHard(),
        expectedQuestions.get(4).getHard());
    assertEquals(srReader.readFile(pathName).getAllQuestions().get(5).getHard(),
        expectedQuestions.get(5).getHard());
  }
}