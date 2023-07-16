package cs3500.pa01;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a QuestionBank containing Questions.
 */
public class QuestionBank {
  private ArrayList<Question> questions;
  private Random rand;

  /**
   * Represents a constructor for an QuestionBank.
   *
   * @param questions the questions in this question bank.
   * @param rand a random number used to randomize the questions list.
   */
  public QuestionBank(ArrayList<Question> questions, Random rand) {
    this.questions = questions;
    this.rand = rand;
  }

  /**
   * Returns an array list of all questions in this question bank.
   *
   * @return an ArrayList of questions in this question bank.
   */
  public ArrayList<Question> getAllQuestions() {
    return this.questions;
  }

  /**
   * Calls a method to get an arraylist of the questions field in a random order.
   * Creates an ArrayList of questions from this QuestionBank of the specified size and calls the
   * method to sort them by difficulty.
   *
   * @param num the number of questions to return.
   * @return an ArrayList of questions of the specified size.
   */
  public ArrayList<Question> getQuestions(int num) {
    ArrayList<Question> randomQuestions;
    randomQuestions = randomizeQuestions(new ArrayList<>(this.questions));

    ArrayList<Question> studyQuestions = new ArrayList<>();
    for (int i = 0; i < num; i++) {
      studyQuestions.add(randomQuestions.get(i));
    }
    return sortByDifficulty(studyQuestions);
  }

  /**
   * Randomizes the given list of questions.
   *
   * @param questions the list of questions to be randomized.
   * @return an arraylist containing the questions in the given arraylist in a random order.
   */
  private ArrayList<Question> randomizeQuestions(ArrayList<Question> questions) {
    int size = questions.size();

    for (int i = size - 1; i > 0; i--) {
      int j = rand.nextInt(i + 1);

      Question temp = questions.get(i);
      questions.set(i, questions.get(j));
      questions.set(j, temp);
    }
    return questions;
  }

  /**
   * Sorts the given ArrayList of questions by difficulty so hard questions come first followed by
   * easy questions.
   *
   * @param list the array list of questions to sort by difficulty.
   * @return the given array list of questions sorted by difficulty.
   */
  private ArrayList<Question> sortByDifficulty(ArrayList<Question> list) {
    ArrayList<Question> sortedQuestions = new ArrayList<>();
    for (Question q : list) {
      if (q.getHard()) {
        sortedQuestions.add(0, q);
      } else {
        sortedQuestions.add(q);
      }
    }
    return sortedQuestions;
  }

  /**
   * Returns the number of questions in this question bank.
   *
   * @return the size of the list of questions.
   */
  public int getSize() {
    return questions.size();
  }

  /**
   * Gets the number of hard questions in total.
   *
   * @return the number of total hard questions.
   */
  public int getNumHard() {
    int count = 0;
    for (Question q : questions) {
      if (q.getHard()) {
        count++;
      }
    }
    return count;
  }

  /**
   * Gets the number of easy questions in total.
   *
   * @return the number of total easy questions.
   */
  public int getNumEasy() {
    return questions.size() - getNumHard();
  }
}
