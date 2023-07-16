package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudySessionControllerTest {
  /**
   * Set all the questions in questions.sr to easy before each test.
   */
  @BeforeEach
  public void setQuestionsFile() throws IOException {
    Path p = Path.of("questions.sr");
    ArrayList<String> content = new ArrayList<>();
    content.add("When is the OOD midterm?:::May 25.{E}");
    content.add("What type of cheatsheet can you use for the OOD midterm?:::"
        + "An 8.5x5.5 paper on one side.{E}");
    Files.write(p, content);
  }

  /**
   * Test that startStudySession prints the welcome message and a prompt to input path to .sr file.
   */
  @Test
  public void testStartStudySession() throws IOException {
    StringBuilder sb = new StringBuilder();
    StudySessionController ssc = new StudySessionController(
        new StringReader("questions.sr\n10\n1\n3"), sb, new Random(4));
    ssc.startStudySession();
    assertEquals("Welcome to a study session.\n"
            + "Please input a path to .sr file containing questions you want to study.\n"
            + "How many questions do you want to study in this session?\n"
            + "When is the OOD midterm?\n"
            + "Please input the number to the next step you would like to take: \n"
            + "1. Mark easy, 2. Mark hard, 3. Show Answer, 4. Exit\n"
            + "The question was marked as easy.\n"
            + "What type of cheatsheet can you use for the OOD midterm?\n"
            + "Please input the number to the next step you would like to take: \n"
            + "1. Mark easy, 2. Mark hard, 3. Show Answer, 4. Exit\n"
            + "Answer: An 8.5x5.5 paper on one side.\n"
            + "This is the end of your study session.\n"
            + "You studied 2 questions.\n"
            + "During your session, 0 questions changed from easy to hard and 0 questions changed "
            + "from hard to easy. \n"
            + "You now have 0 total hard questions and 2 total easy questions.\n",
        sb.toString());
  }

  /**
   * Tests that startStudySessioon method prompts user to reenter a path when they enter an invalid
   * path.
   */
  @Test
  public void testStartStudySessionInvalidPath() throws IOException {
    StringBuilder sb = new StringBuilder();
    StudySessionController ssc = new StudySessionController(
        new StringReader("questions.md\nquestions.sr\n10\n1\n3"), sb, new Random(4));
    ssc.startStudySession();
    assertEquals("Welcome to a study session.\n"
            + "Please input a path to .sr file containing questions you want to study.\n"
            + "Your response is invalid. Please re-enter.\n"
            + "Please input a path to .sr file containing questions you want to study.\n"
            + "How many questions do you want to study in this session?\n"
            + "When is the OOD midterm?\n"
            + "Please input the number to the next step you would like to take: \n"
            + "1. Mark easy, 2. Mark hard, 3. Show Answer, 4. Exit\n"
            + "The question was marked as easy.\n"
            + "What type of cheatsheet can you use for the OOD midterm?\n"
            + "Please input the number to the next step you would like to take: \n"
            + "1. Mark easy, 2. Mark hard, 3. Show Answer, 4. Exit\n"
            + "Answer: An 8.5x5.5 paper on one side.\n"
            + "This is the end of your study session.\n"
            + "You studied 2 questions.\n"
            + "During your session, 0 questions changed from easy to hard and 0 questions changed "
            + "from hard to easy. \n"
            + "You now have 0 total hard questions and 2 total easy questions.\n",
        sb.toString());
  }

  /**
   * Tests that startStudySession prompts user to reenter a number when they enter an invalid number
   * of questions to study.
   */
  @Test
  public void testStartStudySessionInvalidNumQuestions() throws IOException {
    StringBuilder sb = new StringBuilder();
    StudySessionController ssc = new StudySessionController(
        new StringReader("questions.sr\nall\n4\n1\n3"), sb, new Random(4));
    ssc.startStudySession();
    assertEquals("Welcome to a study session.\n"
            + "Please input a path to .sr file containing questions you want to study.\n"
            + "How many questions do you want to study in this session?\n"
            + "Your response is invalid. Please re-enter.\n"
            + "How many questions do you want to study in this session?\n"
            + "When is the OOD midterm?\n"
            + "Please input the number to the next step you would like to take: \n"
            + "1. Mark easy, 2. Mark hard, 3. Show Answer, 4. Exit\n"
            + "The question was marked as easy.\n"
            + "What type of cheatsheet can you use for the OOD midterm?\n"
            + "Please input the number to the next step you would like to take: \n"
            + "1. Mark easy, 2. Mark hard, 3. Show Answer, 4. Exit\n"
            + "Answer: An 8.5x5.5 paper on one side.\n"
            + "This is the end of your study session.\n"
            + "You studied 2 questions.\n"
            + "During your session, 0 questions changed from easy to hard and 0 questions changed "
            + "from hard to easy. \n"
            + "You now have 0 total hard questions and 2 total easy questions.\n",
        sb.toString());
  }

  /**
   * Tests that startStudySession prompts user to reenter a number when they enter 0 questions to
   * study.
   */
  @Test
  public void testStartStudySession0Questions() throws IOException {
    StringBuilder sb = new StringBuilder();
    StudySessionController ssc = new StudySessionController(
        new StringReader("questions.sr\n0\n4\n1\n3"), sb, new Random(4));
    ssc.startStudySession();
    assertEquals("Welcome to a study session.\n"
            + "Please input a path to .sr file containing questions you want to study.\n"
            + "How many questions do you want to study in this session?\n"
            + "Your response is invalid. Please re-enter.\n"
            + "How many questions do you want to study in this session?\n"
            + "When is the OOD midterm?\n"
            + "Please input the number to the next step you would like to take: \n"
            + "1. Mark easy, 2. Mark hard, 3. Show Answer, 4. Exit\n"
            + "The question was marked as easy.\n"
            + "What type of cheatsheet can you use for the OOD midterm?\n"
            + "Please input the number to the next step you would like to take: \n"
            + "1. Mark easy, 2. Mark hard, 3. Show Answer, 4. Exit\n"
            + "Answer: An 8.5x5.5 paper on one side.\n"
            + "This is the end of your study session.\n"
            + "You studied 2 questions.\n"
            + "During your session, 0 questions changed from easy to hard and 0 questions changed "
            + "from hard to easy. \n"
            + "You now have 0 total hard questions and 2 total easy questions.\n",
        sb.toString());
  }

  /**
   * Tests that startStudySession prompts user to reenter a number if they enter a number not
   * associated with an option. (Not 1, 2, 3, 4)
   */
  @Test
  public void testStartStudySessionInvalidOptionNumber() throws IOException {
    StringBuilder sb = new StringBuilder();
    StudySessionController ssc = new StudySessionController(
        new StringReader("questions.sr\n4\n6\n1\n3"), sb, new Random(4));
    ssc.startStudySession();
    assertEquals("Welcome to a study session.\n"
            + "Please input a path to .sr file containing questions you want to study.\n"
            + "How many questions do you want to study in this session?\n"
            + "When is the OOD midterm?\n"
            + "Please input the number to the next step you would like to take: \n"
            + "1. Mark easy, 2. Mark hard, 3. Show Answer, 4. Exit\n"
            + "Your response is invalid. Please re-enter.\n"
            + "Please input the number to the next step you would like to take: \n"
            + "1. Mark easy, 2. Mark hard, 3. Show Answer, 4. Exit\n"
            + "The question was marked as easy.\n"
            + "What type of cheatsheet can you use for the OOD midterm?\n"
            + "Please input the number to the next step you would like to take: \n"
            + "1. Mark easy, 2. Mark hard, 3. Show Answer, 4. Exit\n"
            + "Answer: An 8.5x5.5 paper on one side.\n"
            + "This is the end of your study session.\n"
            + "You studied 2 questions.\n"
            + "During your session, 0 questions changed from easy to hard and 0 questions changed "
            + "from hard to easy. \n"
            + "You now have 0 total hard questions and 2 total easy questions.\n",
        sb.toString());
  }


  /**
   * Tests that startStudySession prompts user to reenter a number if they enter a non-number.
   */
  @Test
  public void testStartStudySessionInvalidOption() throws IOException {
    StringBuilder sb = new StringBuilder();
    StudySessionController ssc = new StudySessionController(
        new StringReader("questions.sr\n4\nidk\n1\n3"), sb, new Random(4));
    ssc.startStudySession();
    assertEquals("Welcome to a study session.\n"
            + "Please input a path to .sr file containing questions you want to study.\n"
            + "How many questions do you want to study in this session?\n"
            + "When is the OOD midterm?\n"
            + "Please input the number to the next step you would like to take: \n"
            + "1. Mark easy, 2. Mark hard, 3. Show Answer, 4. Exit\n"
            + "Your response is invalid. Please re-enter.\n"
            + "Please input the number to the next step you would like to take: \n"
            + "1. Mark easy, 2. Mark hard, 3. Show Answer, 4. Exit\n"
            + "The question was marked as easy.\n"
            + "What type of cheatsheet can you use for the OOD midterm?\n"
            + "Please input the number to the next step you would like to take: \n"
            + "1. Mark easy, 2. Mark hard, 3. Show Answer, 4. Exit\n"
            + "Answer: An 8.5x5.5 paper on one side.\n"
            + "This is the end of your study session.\n"
            + "You studied 2 questions.\n"
            + "During your session, 0 questions changed from easy to hard and 0 questions changed "
            + "from hard to easy. \n"
            + "You now have 0 total hard questions and 2 total easy questions.\n",
        sb.toString());
  }


  /**
   * Test that startStudySession prints that the question was marked as easy if the user inputs
   * option 1. Checks that the ending message reflects the no change since question was already
   * easy so there is still is 0 total hard questions and 2 total easy questions.
   */
  @Test
  public void testRespondToUserOption1() throws IOException {
    StringBuilder sb = new StringBuilder();
    StudySessionController ssc = new StudySessionController(
        new StringReader("questions.sr\n10\n1\n3"), sb, new Random(4));
    ssc.startStudySession();
    assertEquals("Welcome to a study session.\n"
            + "Please input a path to .sr file containing questions you want to study.\n"
            + "How many questions do you want to study in this session?\n"
            + "When is the OOD midterm?\n"
            + "Please input the number to the next step you would like to take: \n"
            + "1. Mark easy, 2. Mark hard, 3. Show Answer, 4. Exit\n"
            + "The question was marked as easy.\n"
            + "What type of cheatsheet can you use for the OOD midterm?\n"
            + "Please input the number to the next step you would like to take: \n"
            + "1. Mark easy, 2. Mark hard, 3. Show Answer, 4. Exit\n"
            + "Answer: An 8.5x5.5 paper on one side.\n"
            + "This is the end of your study session.\n"
            + "You studied 2 questions.\n"
            + "During your session, 0 questions changed from easy to hard and 0 questions changed"
            + " from hard to easy. \n"
            + "You now have 0 total hard questions and 2 total easy questions.\n",
        sb.toString());
  }

  /**
   * Test that startStudySession prints that the question was marked as hard if the user inputs
   * option 2. Checks that the ending message reflects the change from hard to easy so now there
   * is 1 total hard question and 1 total easy question.
   */
  @Test
  public void testRespondToUserOption2() throws IOException {
    StringBuilder sb = new StringBuilder();
    StudySessionController ssc = new StudySessionController(
        new StringReader("questions.sr\n10\n2\n3"), sb, new Random(4));
    ssc.startStudySession();
    assertEquals("Welcome to a study session.\n"
            + "Please input a path to .sr file containing questions you want to study.\n"
            + "How many questions do you want to study in this session?\n"
            + "When is the OOD midterm?\n"
            + "Please input the number to the next step you would like to take: \n"
            + "1. Mark easy, 2. Mark hard, 3. Show Answer, 4. Exit\n"
            + "The question was marked as hard.\n"
            + "What type of cheatsheet can you use for the OOD midterm?\n"
            + "Please input the number to the next step you would like to take: \n"
            + "1. Mark easy, 2. Mark hard, 3. Show Answer, 4. Exit\n"
            + "Answer: An 8.5x5.5 paper on one side.\n"
            + "This is the end of your study session.\n"
            + "You studied 2 questions.\n"
            + "During your session, 1 questions changed from easy to hard and 0 questions changed "
            + "from hard to easy. \n"
            + "You now have 1 total hard questions and 1 total easy questions.\n",
        sb.toString());
  }

  /**
   * Test that startStudySession quits the program if the user inputs option 4.
   */
  @Test
  public void testRespondToUserOption4() throws IOException {
    StringBuilder sb = new StringBuilder();
    StudySessionController ssc = new StudySessionController(
        new StringReader("questions.sr\n10\n4"), sb, new Random(4));
    ssc.startStudySession();
    assertEquals("Welcome to a study session.\n"
            + "Please input a path to .sr file containing questions you want to study.\n"
            + "How many questions do you want to study in this session?\n"
            + "When is the OOD midterm?\n"
            + "Please input the number to the next step you would like to take: \n"
            + "1. Mark easy, 2. Mark hard, 3. Show Answer, 4. Exit\n"
            + "This is the end of your study session.\n"
            + "You studied 1 questions.\n"
            + "During your session, 0 questions changed from easy to hard and 0 questions changed "
            + "from hard to easy. \n"
            + "You now have 0 total hard questions and 2 total easy questions.\n",
        sb.toString());
  }
}