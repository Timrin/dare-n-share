package dareSetUp;

/**
 * This class should maybe be abstract? todo if so.
 * YesNoScore creates methods for dares that has a yes or no/ boolean way to settle the score.
 */

public class YesNoScore implements Score {
    private int score;

    public YesNoScore() {
        this.score = 0;

    }

    /**
     * Method to determine yes or no. todo refactor name.
     */
    public boolean didYouFail() {

        return false;
    }

    @Override
    public int getCurrentScore() {
        return score;
    }

    /**
     * Score is initially 0, expects a value, and adds to score.
     */
    @Override
    public void addToScore(int value) {
        score += value;
    }


}
