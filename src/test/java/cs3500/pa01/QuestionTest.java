package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuestionTest {
  Question q1;
  Question q2;
  Question q3;
  Question q4;
  Question q5;
  Question q6;

  /**
   * Initializes example questions for testing.
   */
  @BeforeEach
  public void createQuestions() {
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
  }

  /**
   * Test that getQuestion returns the question portion of a Question.
   */
  @Test
  public void testGetQuestion() {
    assertEquals(q1.getQuestion(), "When is summer 1 over?");
    assertEquals(q2.getQuestion(), "When is the OOD midterm?");
    assertEquals(q3.getQuestion(), "What type of cheatsheet can you use for the OOD midterm?");
    assertEquals(q4.getQuestion(), "What is the class code for OOD?");
    assertEquals(q5.getQuestion(), "Who is the professor for OOD?");
    assertEquals(q6.getQuestion(), "What utensil should you use to take the OOD midterm?");
  }

  /**
   * Test that getAnswer returns the answer portion of a Question.
   */
  @Test
  public void testGetAnswer() {
    assertEquals(q1.getAnswer(), "June 26.");
    assertEquals(q2.getAnswer(), "May 25.");
    assertEquals(q3.getAnswer(), "An 8.5x5.5 paper on one side.");
    assertEquals(q4.getAnswer(), "CS 3500.");
    assertEquals(q5.getAnswer(), "Dr. Fontenot.");
    assertEquals(q6.getAnswer(), "A pencil.");
  }

  /**
   * Test that getHard returns whether a Question is hard.
   */
  @Test
  public void testGetHard() {
    assertEquals(q1.getHard(), true);
    assertEquals(q2.getHard(), false);
    assertEquals(q3.getHard(), true);
    assertEquals(q4.getHard(), true);
    assertEquals(q5.getHard(), false);
    assertEquals(q6.getHard(), false);
  }

  /**
   * Test that updateDifficulty changes whether a Question is hard.
   */
  @Test
  public void testUpdateDifficulty() {
    // changes a question difficulty from hard to easy
    assertEquals(q1.getHard(), true);
    q1.updateDifficulty(false);
    assertEquals(q1.getHard(), false);

    // changes a question difficulty from easy to hard
    assertEquals(q2.getHard(), false);
    q2.updateDifficulty(true);
    assertEquals(q2.getHard(), true);

    // changes a question difficulty from hard to hard (no change)
    assertEquals(q3.getHard(), true);
    q3.updateDifficulty(true);
    assertEquals(q3.getHard(), true);

    // changes a question difficulty from easy to easy (no change)
    assertEquals(q5.getHard(), false);
    q5.updateDifficulty(false);
    assertEquals(q5.getHard(), false);
  }

}