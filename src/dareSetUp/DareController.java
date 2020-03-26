package dareSetUp;

import Client.ClientController;
import gui.DarePanel;
import gui.LoginPanel;
import model.Participant;
import model.User;


/***
 * Finn en løsning på score.
 *
 * **/

public class DareController {
    private LoginPanel loginPanel;
    private DarePanel darePanel;
    private Dare dare;

    private TimeYesNoDare timeYesNoDare;
    private TimeDare TimeDare;
    //private TimeAddUpScore timeAddUpScore;

    private GoalDare goalDare;
    private GoalDareAddUpScore goalDareAddUpScore;
    private GoalYesNo goalYesNo;
    private Challenges enumChallenges;

    private Participant participant;
    private User user;

    private DontEatMeat dontEatMeat;
    private ClientController clientController;


    public DareController() {


    }


    // Starts the dare after pushing the button "its on like donkey kong" in gui
    public void setUpDareAfterGUI() {
        User anders = new User();
        anders.setName("Anders Tegnell");
         // create a new dare  + assign users to dare
        dontEatMeat = new DontEatMeat(user,anders);
        dontEatMeat.setTimeOfDare(3);

        //send dare to server via client
        sendToClient(dontEatMeat);

    }

    public void updateScore(YesOrNo yesOrNo){

        if (yesOrNo.equals("Yes")){
            dontEatMeat.setScore(YesOrNo.Yes);
        }
        if (yesOrNo.equals("No")){
            dontEatMeat.setScore(YesOrNo.No);
        }

    }

    




    // Bør kalles på i GUI
    public Dare getCategoryFromGUI(Challenges enumChallenges) {
        User anders = new User();
        anders.setName("Anders Tegnell");

        switch (enumChallenges) {
            case DontEatMeat:
                dare =new DontEatMeat(user, anders);
                dontEatMeat.setTimeOfDare(3);  // prototypen
                break;
            case TimesAddUp:
               //dare= new TimeAddUpScore();
                break;
         /*   case GoalAddup:
               dare = (Dare) new GoalDareAddUpScore();
                break;
            case GoalYesNo:
               dare = (Dare) new GoalYesNo();
                break;   */
            case TimesYesNo:
                dare = new TimeYesNoDare(user,anders);
                break;
        }
        return dare;
    }

    public void sendToClient(Dare toSend){
        clientController.sendDare(toSend);
    }

    // gets the Challenger user from gui - prototype
    public void loginUser(String name) {
        name = loginPanel.getNameTxt(); //??
        this.user = new User(name);
         //todo legg til i actionlistener for login-button
        clientController = new ClientController(user);
        // send til clienten
    }

    // GUI setter challengede participant fra JComboBox fra gui når start button er aktivert
    public Participant setChallengedParticipant(String opponent) {
        user = new User(opponent);
        return new Participant(this.user, dare);
    }

    public void getTimeOfDare() {

    }
}
