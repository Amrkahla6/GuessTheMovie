import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        List<String> movies = Files.readAllLines(Paths.get("movies.txt"));

        Random random = new Random();
        String randomMovie = movies.get(random.nextInt(movies.size()));

        GuessTheMovie game = new GuessTheMovie(randomMovie);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Guess The Movie!");
        System.out.println("Try to guess the movie title. You have " + GuessTheMovie.MAX_WRONG_GUESSES + " chances.");

        while (!game.isWon() && !game.isLost()) {
            System.out.println("\nMovie: " + game.getGuessedTitle());
            System.out.println("Wrong guesses: " + game.getWrongGuesses());
            System.out.println("Remaining chances: " + game.getRemainingChances());

            System.out.print("Enter a letter: ");
            String input = scanner.nextLine();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Please enter a single valid letter.");
                continue;
            }

            char guess = input.charAt(0);
            boolean correct = game.guessLetter(guess);

            if (correct) {
                System.out.println("Good guess!");
            } else {
                System.out.println("Wrong guess!");
            }
        }

        if (game.isWon()) {
            System.out.println("\nCongratulations! You guessed the movie: " + randomMovie);
        } else {
            System.out.println("\nGame Over! The movie was: " + randomMovie);
        }
    }
}