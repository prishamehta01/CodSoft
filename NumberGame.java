/*NUMBER GUESSING GAME IMPLEMENTED USING JAVA*/
import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int lowerBound = 1;
        int upperBound = 100;
        int maxAttempts = 5;
        int score = 0; 
        
        System.out.println("WELCOME TO THE NUMBER GUESSING GAME!");
        boolean playAgain = true;
        
        while (playAgain) {
            int targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            System.out.println("Welcome to the challenge! I've selected a number between " + lowerBound + " and " + upperBound + ".Your mission: guess it!");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");

            int attempts = 0;
            boolean guessedCorrectly = false;
            
            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int guess = sc.nextInt();
                
                if (guess < lowerBound || guess > upperBound) {
                    System.out.println("Your guess is out of the valid range!");
                    continue;
                }
                
                attempts++;
                
                if (guess == targetNumber) {
                    System.out.println("Congratulations! You've guessed the correct number (" + targetNumber + ") in " + attempts + " attempts.❤️");
                    guessedCorrectly = true;
                    score++;
                } else if (guess < targetNumber) {
                    System.out.println("Almost there! Aim higher for the jackpot!");
                } else {
                    System.out.println("Whoa, too sky-high!Let's bring it down and give it another go");
                }
            }
            
            if (!guessedCorrectly) {
                System.out.println("Oops! Looks like you've exhausted your attempts. The magical number you were looking for was: " + targetNumber);
            }
            
            System.out.println("Would you like another round? (yes/no):  ");
            String playAgainResponse = sc.next().toLowerCase();
            if (!playAgainResponse.equals("yes")) {
                playAgain = false;
            }
        }
        
        System.out.println("Game Over! Your score is: " + score);
        sc.close();
    }
}
