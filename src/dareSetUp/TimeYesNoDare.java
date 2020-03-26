package dareSetUp;

import model.Participant;
import model.User;

import java.io.Serializable;

/**
 * Time dares with a yes/no answer, is a type of dare that goes on a selected amount of time, and the user
 * answers yes or no, if they achieved what the dare implies.
 *
 * @see TimeDare
 */

public class TimeYesNoDare extends TimeDare  {
    private YesNoScore yesNoScore;
    private int value;
    private Participant participant;


    public TimeYesNoDare(User user1, User user2) {
        super(user1,user2);
    }

    public void setDare() {
        start();
        yesNoScore.addToScore(value);

    }

    public void endDare(){
        stop();
        yesNoScore.getCurrentScore();
    }

    public void addToScore(int value){
        yesNoScore.addToScore(value);
    }




}
