package dareSetUp;

import gui.DarePanel;
import gui.LoginPanel;
import model.Participant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DareController implements ActionListener {
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



    public DareController() {
        darePanel.getStartDareBtn().addActionListener(this);
        timeYesNoDare.setTimeOfDare(3);

    }

    // Starts the dare after pushing the button "its on like donkey kong" in gui
    public void setUpDareAfterGUI(){
        if (TimeDare instanceof TimeYesNoDare) {
            new DontEatMeat(participant,user);

        }
        if (TimeDare instanceof TimeAddUpScore) {

        }
    }

    public void setOpponent(String opponent){
        new User();
        user.setName(opponent);
    }
    public void getTimeOfDare(){

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (darePanel.getStartDareBtn().isSelected()) {

            if (TimeDare instanceof TimeYesNoDare) {
                timeYesNoDare.start();
            }
            if (TimeDare instanceof TimeAddUpScore) {
                timeAddUpScore.start();
            }


        }

    }
}
