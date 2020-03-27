package dareSetUp;

import Client.ClientController;
import gui.DarePanel;
import gui.InfoDarePanel;
import gui.LoginPanel;
import model.Participant;
import model.User;


/***
 * Finn en løsning på score.
 * **/

public class DareController {
	private LoginPanel loginPanel;
	private DarePanel darePanel;
	private InfoDarePanel infoDarePanel;
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


	public DareController(LoginPanel loginPanel) {
		this.loginPanel = loginPanel;
	}


	// Starts the dare after pushing the button "its on like donkey kong" in gui
	public void setUpDareAfterGUI() {
		User anders = new User();
		anders.setName("Anders Tegnell");

		// create a new dare  + assign users to dare
		dontEatMeat = new DontEatMeat(this.user, anders);
		dontEatMeat.setTimeOfDare(3);

		//send dare to server via client
		sendToClient(dontEatMeat);

	}

	// Skal ta imot yes or no enum fra GUI, og legge til score deretter.
	public void updateScore(YesOrNo yesOrNo) {

		if (yesOrNo.equals("Yes")) {
			dontEatMeat.setScore(YesOrNo.Yes);
		}
		if (yesOrNo.equals("No")) {
			dontEatMeat.setScore(YesOrNo.No);
		}
	}


	// Bør kalles på i GUI
	public Dare getCategoryFromGUI(Challenges enumChallenges) {
		User anders = new User();
		anders.setName("Anders Tegnell");

		switch (enumChallenges) {
			case DontEatMeat:
				dare = new DontEatMeat(user, anders);
				dontEatMeat.setTimeOfDare(3);  // prototypen
				break;
			case FeedTheDucks:
				//dare= new TimeAddUpScore();
				break;
         /*   case GoalAddup:
               dare = (Dare) new GoalDareAddUpScore();
                break;
            case GoalYesNo:
               dare = (Dare) new GoalYesNo();
                break;   */
           /* case TimesYesNo:
                dare = new TimeYesNoDare(user,anders);
                break;*/
		}
		return dare;
	}

	public void sendToClient(Dare toSend) {
		clientController.sendDare(toSend);
	}

	// gets the Challenger user from gui - prototype
	public void loginUser(String name) {
		this.user = new User(name);
		//todo legg til i actionlistener for login-button
		clientController = new ClientController(this.user, this);
		// send til clienten
	}

	// GUI setter challengede participant fra JComboBox fra gui når start button er aktivert
	public Participant setChallengedParticipant(String opponent) {
		user = new User(opponent);
		return new Participant(this.user, dare);
	}

	public void getDareFromClient(Dare dare) {

		String competitor = user.getName().equalsIgnoreCase(dare.getInstigator().getUser().getName()) ? dare.getChallenged().getUser().getName() : dare.getInstigator().getUser().getName();
		// set in gui dare.toString
		// Sette skriftlig informasjon om current dare i GUI.
		loginPanel.getInfodare().setLblHeaderDareName(dare.toString());
		loginPanel.getInfodare().setLblGetOpponent(competitor);
	}

	public void getOpponent(User user) {
		// Get opponent user from client
		// set in gui
	}

    /*
     -metod som tar emot en dare från clientontroller, skickar till GUI.
     - metod som skickar motståndare till GUI
     - skickar poäng till GUI

     */
}

