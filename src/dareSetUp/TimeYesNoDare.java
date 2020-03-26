package dareSetUp;

import model.Participant;

/**
 * Time dares with a yes/no answer, is a type of dare that goes on a selected amount of time, and the user
 * answers yes or no, if they achieved what the dare implies.
 *
 * @see TimeDare
 */

public class TimeYesNoDare extends TimeDare {
    private YesNoScore yesNoScore;
    private int value;
    private Participant participant;


    public TimeYesNoDare() {
        super();
    }


    public void setDare() {
        start();
        yesNoScore.addToScore(value);


    }


}
