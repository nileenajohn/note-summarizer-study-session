package cs3500.pa01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents an implementation of the IController interface. Responsible for receiving input from
 * user from console and calling functions on the StudySessionModel and telling StudySessionViewer
 * what to print to the user.
 */
public class StudySessionController implements Controller {
  private StudySessionViewer ssv;
  private StudySessionModel ssm;
  private Readable readable;
  private Scanner in;
  Random rand;

  /**
   * Represents a constructor for a StudySessionController.
   *
   * @param readable the readable input from the console.
   * @param appendable the output to the console.
   * @param rand the random number used to create a StudySessionModel object.
   */
  public StudySessionController(Readable readable, Appendable appendable, Random rand) {
    this.ssv = new StudySessionViewer(appendable);
    this.readable = readable;
    this.in = new Scanner(this.readable);
    this.rand = rand;
  }

  /**
   * Starts the studySession aspect of the program by welcoming user and prompting them to input a
   * path to a .sr file and number of questions to practice from user in console.
   * Creates a studySessionModel object giving it the number of questions and path to .sr file.
   * Calls startFlashcards method to create a question set and start showing flashcards to user.
   */
  @Override
  public void startStudySession() throws IOException {
    this.ssv.printMessage("Welcome to a study session.");
    this.ssv.printMessage("Please input a path to .sr file "
        + "containing questions you want to study.");
    Path srPath = getSrPath();
    this.ssv.printMessage("How many questions do you want to study in this session?");
    int numQuestions = getNumQuestions();
    initializeStudySessionModel(srPath, numQuestions);
    startFlashcards();
  }

  /**
   * Gets the sr path from the user in console and ensures that it is a valid path to an .sr file.
   * Otherwise, sends a prompt to reenter to the viewer.
   *
   * @return the path of the sr file.
   */
  private Path getSrPath() throws IOException {
    Path p = Path.of(in.nextLine());
    File f = p.toFile();
    if (f.isFile() && f.exists() && p.toString().endsWith(".sr")) {
      return p;
    } else {
      this.ssv.printMessage("Your response is invalid. Please re-enter.");
      this.ssv.printMessage("Please input a path to .sr file containing questions you want to "
          + "study.");
      return getSrPath();
    }
  }

  /**
   * Gets the number of questions to study in the study session from the user in the console
   * and checks that it is a valid number. Otherwise, sends a prompt to reenter to the viewer.
   *
   * @return the number of questions to study.
   */
  private int getNumQuestions() throws IOException {
    String numString = in.nextLine();
    if (numString.matches("-?\\d+")) {
      int n = Integer.parseInt(numString);
      if (n > 0) {
        return n;
      } else {
        this.ssv.printMessage("Your response is invalid. Please re-enter.");
        this.ssv.printMessage("How many questions do you want to study in this session?");
        return getNumQuestions();
      }
    } else {
      this.ssv.printMessage("Your response is invalid. Please re-enter.");
      this.ssv.printMessage("How many questions do you want to study in this session?");
      return getNumQuestions();
    }
  }

  /**
   * Initializes a StudySessionModel with the given path to sr file and a specified number of
   * questions from the user.
   *
   * @param srPath the path that the study session model should use to get questions from.
   * @param numQuestions the number of questions that should be studied in the session.
   */
  private void initializeStudySessionModel(Path srPath, int numQuestions) throws IOException {
    this.ssm = new StudySessionModel(srPath, numQuestions, rand);
    this.ssm.createQuestions();
  }

  /**
   * Checks whether the studySession is still continuing. If it is, gets the next question and
   * tells viewer to print the question and options to the user. Takes in the userResponse and
   * calls itself. If not, ends the study session.
   */
  private void startFlashcards() throws IOException {
    if (ssm.isSessionOver()) {
      endStudySession();
    } else {
      this.ssv.printMessage(ssm.getNextQuestion().getQuestion());
      this.ssv.printMessage("Please input the number to the next step you would like to take: ");
      this.ssv.printMessage("1. Mark easy, 2. Mark hard, 3. Show Answer, 4. Exit");
      getUserResponse();
    }
  }

  /**
   * Reads in the user response and checks whether it is a number. If it is, calls respondToUser
   * otherwise prints options again.
   */
  private void getUserResponse() throws IOException {
    String userResponse = in.nextLine();
    int optionNumber;
    if (userResponse.matches("-?\\d+")) {
      optionNumber = Integer.parseInt(userResponse);
      respondToUser(optionNumber);
    } else {
      this.ssv.printMessage("Your response is invalid. Please re-enter.");
      this.ssv.printMessage("Please input the number to the next step you would like to take: ");
      this.ssv.printMessage("1. Mark easy, 2. Mark hard, 3. Show Answer, 4. Exit");
      getUserResponse();
    }
  }

  /**
   * Correctly updates the StudySessionModel based on the users selected option number. Prints the
   * answer to the user or whether the question was marked as hard/easy based on their input.
   * If they input a number not given, print options again.
   */
  private void respondToUser(int optionNumber) throws IOException {
    if (optionNumber == 1) {
      ssm.updateQuestion(false);
      ssv.printMessage("The question was marked as easy.");
      startFlashcards();
    } else if (optionNumber == 2) {
      ssm.updateQuestion(true);
      ssv.printMessage("The question was marked as hard.");
      startFlashcards();
    } else if (optionNumber == 3) {
      ssv.printMessage("Answer: " + ssm.getAnswer());
      startFlashcards();
    } else if (optionNumber == 4) {
      ssm.endSession();
      startFlashcards();
    } else {
      this.ssv.printMessage("Your response is invalid. Please re-enter.");
      this.ssv.printMessage("Please input the number to the next step you would like to take: ");
      this.ssv.printMessage("1. Mark easy, 2. Mark hard, 3. Show Answer, 4. Exit");
      getUserResponse();
    }
  }

  /**
   *  Ends the study session and sends strings about the session statistics to the viewer to output.
   *  Updates the .sr file with the new question difficulty levels.
   */
  private void endStudySession() throws IOException {
    this.ssv.printMessage("This is the end of your study session.");
    this.ssv.printMessage("You studied " + ssm.getNumAnswered() + " questions.");
    this.ssv.printMessage("During your session, " + ssm.getEasyToHard()
        + " questions changed from easy to hard and " + ssm.getHardToEasy()
        + " questions changed from hard to easy. ");
    this.ssv.printMessage("You now have " + ssm.getNumHard() + " total hard questions and "
        + ssm.getNumEasy() + " total easy questions.");
    ssm.updateSrFile();
  }
}
