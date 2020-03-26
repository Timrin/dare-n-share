package dareSetUp;

import gui.DarePanel;
import gui.LoginPanel;
import model.Participant;
import model.User;

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
    public void setGoalDare(TimeDare timeDare) {

    }


    // Bør kalles på i GUI
    public Dare getCategoryFromGUI(Challenges enumChallenges) {

        switch (enumChallenges) {
            case DontEatMeat:
                new DontEatMeat();
                dontEatMeat.setTimeOfDare(3);  // prototypen
                break;
            case TimesAddUp:
                new TimeAddUpScore();
                break;
            case GoalAddup:
                new GoalDareAddUpScore();
                break;
            case GoalYesNo:
                new GoalYesNo();
                break;
            case TimesYesNo:
                new TimeYesNoDare();
                break;
        }
        return dare;
    }
     // gets the Challenger user from gui - prototype
    public void getUserFromGUI(String name) {
        name = loginPanel.getNameTxt();
        new User(name);
        //user.setName(loginPanel.getNameTxt());
        // send til clienten
    }


    // GUI setter challengede participant fra JComboBox fra gui når start button er aktivert
    public Participant setChallengedParticipant(String opponent) {
        user = new User(opponent);  //??
        //this.user.setName(opponent);
        return new Participant(this.user, dare);
    }

    public void getTimeOfDare() {

    }
}
