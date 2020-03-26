package dareSetUp;

/**
 * This class should maybe be abstract? todo if so.
 * YesNoScore creates methods for dares that has a yes or no/ boolean way to settle the score.
 */

public class YesNoScore implements Score {
    private int score;
    private YesOrNo yesOrNo;

    public YesNoScore() {
        this.score = 0;

    }

    /**
     * Method to determine yes or no. todo refactor name.
     */
   /* public int didYouFail(YesOrNo yesOrNo) {
        if (yesOrNo.equals("Yes")){
            score+=1000;
        }
        if (yesOrNo.equals("No")){
            score-=1000;
        }
        return score;
    }*/

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
