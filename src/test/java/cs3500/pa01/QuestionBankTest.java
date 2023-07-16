package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuestionBankTest {
  Question q1;
  Question q2;
  Question q3;
  Question q4;
  Question q5;
  Question q6;
  ArrayList<Question> questions;
  QuestionBank qb;
  QuestionBank qb2;
  QuestionBank qb3;


  /**
   * Initializes example questions and QuestionBank for testing.
   */
  @BeforeEach
  public void createQuestionBank() throws IOException {
    q1 = new Question("When is summer 1 over?", "June 26.", true);
    q2 = new Question("When is the OOD midterm?", "May 25.", false);
    q3 = new Question("What type of cheatsheet can you use for the OOD midterm?",
        "An 8.5x5.5 paper on one side.", true);
    q4 = new Question("What is the class code for OOD?", "CS 3500.",
        true);
    q5 = new Question("Who is the professor for OOD?", "Dr. Fontenot.",
        true);
    q6 = new Question("What utensil should you use to take the OOD midterm?",
        "A pencil.", false);
    questions = new ArrayList<>();
    questions.add(q1);
    questions.add(q2);
    questions.add(q3);
    questions.add(q4);
    questions.add(q5);
    questions.add(q6);
    qb = new QuestionBank(questions, new Random(4));
    qb2 = new SrFileReader(new Random(4)).readFile(Path.of(
        "src/test/resources/pa2SampleInputs/directory1/directory2/sampleQuestions.sr"));
    qb3 = new SrFileReader(new Random(4)).readFile(Path.of(
        "src/test/resources/pa2SampleInputs/directory1/outputDirectory.sr"));
  }

  /**
   * Tests that getAllQuestions() returns a list of questions in the QuestionBank.
   */
  @Test
  public void testGetAllQuestions() {
    assertEquals(qb.getAllQuestions(), questions);
  }

  /**
   * Tests that getQuestions() returns a list of questions of the specified size.
   */
  @Test
  public void testGetQuestionsSize() {
    assertEquals(qb.getQuestions(1).size(), 1);
    assertEquals(qb.getQuestions(3).size(), 3);
    assertEquals(qb.getQuestions(6).size(), 6);
  }

  /**
   * Tests that getQuestions() returns a list of questions in the QuestionBank.
   */
  @Test
  public void testGetQuestions() {
    assertEquals(questions.containsAll(qb.getQuestions(6)), true);
    assertEquals(questions.containsAll(qb.getQuestions(1)), true);
    assertEquals(questions.containsAll(qb.getQuestions(4)), true);
  }

  /**
   * Tests that getSize() returns the length of the list of questions in the QuestionBank.
   */
  @Test
  public void testGetSize() throws IOException {
    assertEquals(qb.getSize(), 6);
    assertEquals(qb2.getSize(),
        6);
    assertEquals(qb3.getSize(),
        29);
  }

  /**
   * Tests that getNumHard() returns the number of hard questions in the QuestionBank.
   */
  @Test
  public void testGetNumHard()  {
    assertEquals(qb.getNumHard(), 4);
    assertEquals(qb2.getNumHard(), 3);
    assertEquals(qb3.getNumHard(), 17);
  }

  /**
   * Tests that getNumHard() returns the number of hard questions in the QuestionBank.
   */
  @Test
  public void testGetNumEasy()  {
    assertEquals(qb.getNumEasy(), 2);
    assertEquals(qb2.getNumEasy(), 3);
    assertEquals(qb3.getNumEasy(), 12);
  }
}