import java.util.HashSet;
import java.util.Set;

public class GuessTheMovie {
    private static final int MAX_WRONG_GUESSES = 10;
    private String movieTitle;
    private char[] guessedTitle;
    private Set<Character> wrongGuesses = new HashSet<>();
    private int wrongAttempts = 0;

    public GuessTheMovie(String movieTitle){
        this.movieTitle = movieTitle.toLowerCase();
        guessedTitle    = new char[movieTitle.length()];

        for (int i = 0; i < movieTitle.length(); i++) {
            if (movieTitle.charAt(i) == ' ') {
                guessedTitle[i] = ' '; // keep spaces
            }else{
                guessedTitle[i] = '_';
            }
        }
    }

    public boolean guessLetter(char letter){
        letter = Character.toLowerCase(letter);
        boolean found = false;

        for (int i = 0; i < movieTitle.length(); i++) {
            if (movieTitle.charAt(i) == letter) {
                guessedTitle[i] = letter;
                found = true;
            }
        }

        if (!found) {
            if (!wrongGuesses.contains(letter)) {
                wrongGuesses.add(letter);
                wrongAttempts++;
            }
        }

        return found;
    }

    public boolean isWon(){
        return movieTitle.equals(new String(guessedTitle));
    }

    public boolean isLost(){
        return wrongAttempts >= MAX_WRONG_GUESSES;
    }
}
