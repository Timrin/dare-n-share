package dareSetUp;

/**
 * This class should maybe be abstract? todo if so.
 * Time dare is a type of dare, set to be active in a selcted amount of time (days).
 * @author anonymous urox
 * Implements Dare, because every dare has a start and a stop.
 * */

public abstract class TimeDare implements Dare {
    private Score score;
    private int timeOfDare;
    private boolean goalAcheived = true;


    public TimeDare(){

    }

    @Override
    public  void start(){
        if (goalAcheived == false){
            // start
        }
    }

    @Override
    public  void stop(){
        if(goalAcheived == true){
            // stop + winner
        }
    }

    @Override
    public abstract User getInstigator();

    @Override
    public abstract User getChallenged();
}
