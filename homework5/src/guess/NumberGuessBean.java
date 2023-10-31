package guess;

import java.io.Serializable;
import java.util.Random;

public class NumberGuessBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private int ans;
    private String tip;
    private int GuessNum;
    private boolean correct;
    private final Random randomNum = new Random();

    public NumberGuessBean() {
        reset();
    }

    public int getAns() {
        return ans;
    }

    public void setAns(int answer) {
        this.ans = answer;
    }

    public String getTip() {
        return "" + tip;
    }

    public void setTip(String hint) {
        this.tip = hint;
    }

    public void setGuessNum(int GuessesNum) {
        this.GuessNum = GuessesNum;
    }

    public int getGuessNum() {
        return GuessNum;
    }

    public boolean getCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public void setGuess(String guess) throws NumberFormatException {
        GuessNum++;

        int InGuess;
        InGuess = Integer.parseInt(guess);

        if (InGuess == ans) {
            correct = true;
        } else if (InGuess == -1) {
            tip = "please type in a number next time";
        } else if (InGuess > ans) {
            tip = "lt's higher than the target";
        } else if (InGuess < ans) {
            tip = "lt's lower than the target";
        }
    }

    public void reset() {
        ans = Math.abs(randomNum.nextInt() % 100) + 1;
        correct = false;
        GuessNum = 0;
    }
}
