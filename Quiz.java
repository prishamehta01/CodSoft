import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Quiz {
    private ArrayList<Question> questions;
    private int currentQuestionIndex;
    private int score;
    private Scanner scanner;
    private Timer questionTimer;
    private final int QUESTION_TIME_LIMIT = 15; // Time limit for each question in seconds

    public Quiz(ArrayList<Question> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
        this.scanner = new Scanner(System.in);
        this.questionTimer = new Timer();
    }

    public void startQuiz() {
        displayQuestion();
    }

    private void displayQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        System.out.println("Question: " + currentQuestion.getQuestionText());
        currentQuestion.displayOptions();

       
        startTimer();

        
        waitForAnswer();
    }

    private void startTimer() {
        questionTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up!");
                stopTimer(); 
                handleAnswer(-1); 
            }
        }, QUESTION_TIME_LIMIT * 1000); // Converting seconds to milliseconds
    }

    private void stopTimer() {
        questionTimer.cancel();
        questionTimer.purge();
    }

    private void waitForAnswer() {
        // Read user input for answer
        int selectedOption = -1; // Default to -1 if no valid input received
        try {
            String input = scanner.nextLine().trim();
            selectedOption = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            // Invalid input format, do nothing and selectedOption remains -1
        }

        handleAnswer(selectedOption);
    }

    private void handleAnswer(int selectedOption) {
        Question currentQuestion = questions.get(currentQuestionIndex);

        // Validate and process answer
        if (selectedOption >= 1 && selectedOption <= currentQuestion.getOptions().length) {
            if (currentQuestion.isCorrect(selectedOption)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. Correct answer is: " + (currentQuestion.getCorrectAnswerIndex() + 1));
            }
        } else if (selectedOption == -1) {
            System.out.println("Time's up! Moving to the next question.");
        } else {
            System.out.println("Invalid option. Please try again.");
            displayQuestion();
            return;
        }

        goToNextQuestion();
    }

    private void goToNextQuestion() {
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            displayQuestion();
        } else {
            endQuiz();
        }
    }

    private void endQuiz() {
        System.out.println("\nQuiz ended!");
        System.out.println("Your score: " + score + "/" + questions.size());
        scanner.close();
    }

    public static void main(String[] args) {
        // Example usage: create questions and start quiz
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?",
                new String[]{"1. Paris", "2. London", "3. Rome", "4. Berlin"},
                0));
        questions.add(new Question("What is the largest planet in our solar system?",
                new String[]{"1. Jupiter", "2. Saturn", "3. Earth", "4. Mars"},
                0));

        Quiz quiz = new Quiz(questions);
        quiz.startQuiz();
    }
}
