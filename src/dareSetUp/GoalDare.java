package dareSetUp;

/**
 * This class should maybe be abstract? todo if so.
 * Goal dare is a dare, which the user wins if he/she is first to finish/achieve the goal of the dare.
 * Implements Dare, because every dare has a start and a stop.
 */

public class GoalDare implements Dare {
    private Score score;
    private boolean goalAchieved = true;

    @Override
    public void start() {
        if (goalAchieved == false){
            // start
        }
    }
    @Override
    public void stop() {
        if (goalAchieved ==true){
            // stop + winner
            // if about score
        }

    }

    @Override
    public User getInstigator() {
        return null;
    }

    @Override
    public User getChallenged() {
        return null;
    }
}
