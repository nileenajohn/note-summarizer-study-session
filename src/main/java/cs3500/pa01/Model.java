package cs3500.pa01;

import java.io.IOException;

/**
 * Represents an interface for the Model of the StudySession aspect of the program.
 */
public interface Model {
  /**
   * Initializes questions for this Model for the study session.
   */
  void createQuestions() throws IOException;

  /**
   * Gets the answer to the current question.
   *
   * @return the answer to the current question.
   */
  String getAnswer();

  /**
   * Increments count and returns the question corresponding to the index of count in the list of
   * questionsToStudy.
   *
   * @return the next question to study.
   */
  Question getNextQuestion();

  /**
   * Returns the number of questions studied so far in the study session.
   *
   * @return the number of questions studied.
   */
  int getNumAnswered();

  /**
   * Returns the number of questions that changed difficulty from hard to easy in this session.
   *
   * @return the number of questions that changed from hard to easy.
   */
  int getHardToEasy();

  /**
   * Returns the number of questions that changed difficulty from easy to hard in this session.
   *
   * @return the number of questions that changed from easy to hard.
   */
  int getEasyToHard();

  /**
   * Returns whether the session is over either because the user has studied the number of questions
   * they wanted to study or session was terminated.
   *
   * @return whether the session is over or not.
   */
  boolean isSessionOver();

  /**
   * Changes the difficulty of the given question based on the given boolean.
   *
   * @param toHard whether the question should be changed to hard or not.
   */
  void updateQuestion(boolean toHard);

  /**
   * Gets the number of hard questions in total.
   *
   * @return the number of total hard questions.
   */
  int getNumHard();

  /**
   * Gets the number of easy questions in total.
   *
   * @return the number of total easy questions.
   */
  int getNumEasy();

  /**
   * Updates the study session to be over.
   */
  void endSession();

  /**
   * Updates the sr file to reflect the new difficulties of questions.
   */
  void updateSrFile() throws IOException;
}