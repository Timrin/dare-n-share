package dareSetUp;
/**
 * This class should maybe be abstract? todo if so.
 * YesNoScore creates methods for dares that has a yes or no/ boolean way to settle the score.
 * */

public class YesNoScore implements Score {


    /**
     * Method to determine yes or no. todo refactor name.
     * */
    public boolean didYouFail(){
        return false;
    }

    @Override
    public int getCurrentScore() {
        return 0;
    }

    @Override
    public void addToScore() {

    }
}
