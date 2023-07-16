package cs3500.pa01;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents an implementation of the IModel interface. Responsible for the functionality of
 * studying flashcards.
 */
public class StudySessionModel implements Model {
  private Path srPath;
  private int questionsAnswered;
  private int numQuestions;
  private QuestionBank allQuestions;
  private ArrayList<Question> questionsToStudy;
  private int hardToEasy;
  private int easyToHard;
  private boolean sessionOver;
  Random rand;

  /**
   * Represents a StudySessionModel.
   *
   * @param srPath The path to the sr file that this StudySessionModel will use to create questions.
   * @param numQuestions The number of questions the user wants to study in this study session.
   * @param rand the random used to initialize the SrFileReader.
   */
  public StudySessionModel(Path srPath, int numQuestions, Random rand) {
    this.srPath = srPath;
    this.questionsAnswered = 0;
    this.numQuestions = numQuestions;
    this.hardToEasy = 0;
    this.easyToHard = 0;
    this.sessionOver = false;
    this.rand = rand;

  }

  /**
   * Initializes questions for this Model for the study session.
   */
  public void createQuestions() throws IOException {
    allQuestions = new SrFileReader(this.rand).readFile(srPath);
    if (numQuestions > allQuestions.getSize()) {
      this.numQuestions = allQuestions.getSize();
    }
    questionsToStudy = allQuestions.getQuestions(numQuestions);
  }

  /**
   * Gets the answer to the current question.
   *
   * @return the answer to the current question.
   */
  public String getAnswer() {
    return questionsToStudy.get(questionsAnswered - 1).getAnswer();
  }

  /**
   * Increments count and returns the question corresponding to the index of count in the list of
   * questionsToStudy.
   *
   * @return the next question to study.
   */
  public Question getNextQuestion() {
    questionsAnswered++;;
    sessionOver = (questionsAnswered >= numQuestions);
    return questionsToStudy.get(questionsAnswered - 1);
  }

  /**
   * Returns the number of questions studied so far in the study session.
   *
   * @return the number of questions studied.
   */
  public int getNumAnswered() {
    return questionsAnswered;
  }

  /**
   * Returns the number of questions that changed difficulty from hard to easy in this session.
   *
   * @return the number of questions that changed from hard to easy.
   */
  public int getHardToEasy() {
    return hardToEasy;
  }

  /**
   * Returns the number of questions that changed difficulty from easy to hard in this session.
   *
   * @return the number of questions that changed from easy to hard.
   */
  public int getEasyToHard() {
    return easyToHard;
  }

  /**
   * Returns whether the session is over either because the user has studied the number of questions
   * they wanted to study or session was terminated.
   *
   * @return whether the session is over or not.
   */
  public boolean isSessionOver() {
    return sessionOver;
  }

  /**
   * Changes the difficulty of the given question based on the given boolean.
   *
   * @param toHard whether the question should be changed to hard or not.
   */
  public void updateQuestion(boolean toHard) {
    if (questionsToStudy.get(questionsAnswered - 1).getHard() && !toHard) {
      this.hardToEasy++;
      questionsToStudy.get(questionsAnswered - 1).updateDifficulty(false);
    }
    if (!questionsToStudy.get(questionsAnswered - 1).getHard() && toHard) {
      this.easyToHard++;
      questionsToStudy.get(questionsAnswered - 1).updateDifficulty(true);
    }
  }

  /**
   * Gets the number of hard questions in total.
   *
   * @return the number of total hard questions.
   */
  public int getNumHard() {
    return allQuestions.getNumHard();
  }

  /**
   * Gets the number of easy questions in total.
   *
   * @return the number of total easy questions.
   */
  public int getNumEasy() {
    return allQuestions.getNumEasy();
  }

  /**
   * Updates the study session to be over.
   */
  public void endSession() {
    this.sessionOver = true;
  }

  /**
   * Updates the sr file to reflect the new difficulties of questions.
   */
  public void updateSrFile() throws IOException {
    new FileWriter(srPath).updateSrFile(allQuestions.getAllQuestions());
  }
}
