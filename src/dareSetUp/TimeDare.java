package dareSetUp;



/**
 * This class should maybe be abstract? todo if so.
 * Time dare is a type of dare, set to be active in a selcted amount of time (days).
 * @author anonymous urox
 * Implements Dare, because every dare has a start and a stop.
 * */

public class TimeDare implements Dare {
    private Score score;
    private int timeOfDare;
    private boolean goalAcheived = true;


    public TimeDare(){

    }

    @Override
    public  boolean start(){
        return goalAcheived = false;
    }

    @Override
    public boolean stop(){
        return goalAcheived=true;
    }


    public int getTimeOfDare() {
        return timeOfDare;
    }

    public void setTimeOfDare(int timeOfDare) {
        this.timeOfDare = timeOfDare;
    }

    @Override
    public User getInstigator(){
        return null;
    }

    @Override
    public User getChallenged(){
        return null;
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
