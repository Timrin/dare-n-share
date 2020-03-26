package dareSetUp;

import gui.DarePanel;
import gui.LoginPanel;
import model.Participant;
import model.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DareController {
    private LoginPanel loginPanel;
    private DarePanel darePanel;
    private Dare dare;

    private TimeYesNoDare timeYesNoDare;
    private TimeDare TimeDare;
    private TimeAddUpScore timeAddUpScore;

    private GoalDare goalDare;
    private GoalDareAddUpScore goalDareAddUpScore;
    private GoalYesNo goalYesNo;
    private Challenges enumChallenges;

    private Participant participant;
    private User user;

    private DontEatMeat dontEatMeat;


    public DareController() {
        timeYesNoDare.setTimeOfDare(3);

    }

    // Starts the dare after pushing the button "its on like donkey kong" in gui
    public void setUpDareAfterGUI() {
        dontEatMeat = new DontEatMeat();
        dontEatMeat.setTimeOfDare(3);

      /*  if (TimeDare instanceof TimeYesNoDare) {
            new DontEatMeat(participant,user);
        }
        if (TimeDare instanceof TimeAddUpScore) {

        }*/
    }



    public void setGoalDare(TimeDare timeDare){

    }

    public void setChallengedParticipant(String opponent) {
        user = new User();
        this.user.setName(opponent);
    }

    public void getTimeOfDare() {

    }



}
