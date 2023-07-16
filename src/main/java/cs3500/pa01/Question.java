package cs3500.pa01;

/**
 * Represents a Question in a QuestionBank with an associated answer, student response, and
 * difficulty level.
 */
public class Question {
  private String question;
  private String answer;
  private boolean hard;

  /**
   * Represents a question in a QuestionBank.
   *
   * @param question The question associated with this question.
   * @param answer The answer associated with this question.
   * @param hard Whether this question is hard or not.
   */
  public Question(String question, String answer, boolean hard) {
    this.question = question;
    this.answer = answer;
    this.hard = hard;
  }

  /**
   * Returns the question portion of this question.
   *
   * @return the question field of this question.
   */
  public String getQuestion() {
    return question;
  }

  /**
   * Returns the answer portion of this question.
   *
   * @return the answer field of this question.
   */
  public String getAnswer() {
    return answer;
  }

  /**
   * Returns whether this question is hard.
   *
   * @return the hard field of this question.
   */
  public boolean getHard() {
    return hard;
  }

  /**
   * Changes the difficulty of the question to hard or not based on the given boolean.
   */
  public void updateDifficulty(boolean toHard) {
    this.hard = toHard;
  }
}
