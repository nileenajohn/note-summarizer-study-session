package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudySessionModelTest {

  StudySessionModel ssm;
  Question current;

  /**
   * Initialize a StudySessionModel with questions.
   */
  @BeforeEach
  public void initializeStudySessionModel() throws IOException {
    ssm = new StudySessionModel(Path.of(
        "src/test/resources/pa2SampleInputs/directory1/directory2/sampleQuestions.sr"),
        6, new Random(4));
    ssm.createQuestions();
    current = ssm.getNextQuestion();
  }

  /**
   * Test that updateQuestion changes whether the current question is hard to the given boolean.
   */
  @Test
  public void testGetEasyToHardAndHardToEasy() {
    assertEquals(ssm.getEasyToHard(), 0);
    assertEquals(ssm.getHardToEasy(), 0);
    ssm.updateQuestion(false);
    assertEquals(ssm.getEasyToHard(), 0);
    assertEquals(ssm.getHardToEasy(), 1);
    ssm.updateQuestion(false);
    assertEquals(ssm.getEasyToHard(), 0);
    assertEquals(ssm.getHardToEasy(), 1);
    ssm.updateQuestion(true);
    assertEquals(ssm.getEasyToHard(), 1);
    assertEquals(ssm.getHardToEasy(), 1);
    ssm.updateQuestion(true);
    assertEquals(ssm.getEasyToHard(), 1);
    assertEquals(ssm.getHardToEasy(), 1);
  }

  /**
   * Test that getHardToEasy and getEasyToHard update when a question
   * changes from hard to easy and easy to hard.
   */
  @Test
  public void testUpdateQuestion() {
    ssm.updateQuestion(false);
    assertEquals(current.getHard(), false);
    ssm.updateQuestion(false);
    assertEquals(current.getHard(), false); // no change
    ssm.updateQuestion(true);
    assertEquals(current.getHard(), true);
    ssm.updateQuestion(true);
    assertEquals(current.getHard(), true); // no change
  }

}