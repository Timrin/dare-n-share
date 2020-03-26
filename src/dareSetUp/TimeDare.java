package dareSetUp;


import model.User;

import java.io.Serializable;

/**
 * This class should maybe be abstract? todo if so.
 * Time dare is a type of dare, set to be active in a selcted amount of time (days).
 * @author anonymous urox
 * Implements Dare, because every dare has a start and a stop.
 * */

public class TimeDare extends Dare {
    private Score score;
    private int timeOfDare;
    private boolean goalAcheived = true;



    public TimeDare(User user1, User user2){
        super(user1,user2);

    }

    @Override
    public String toString() {
        return null;
    }

    public int getTimeOfDare() {
        return timeOfDare;
    }

    public void setTimeOfDare(int timeOfDare) {
        this.timeOfDare = timeOfDare;
    }


}
