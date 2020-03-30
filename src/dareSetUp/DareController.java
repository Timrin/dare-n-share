package dareSetUp;

import Client.ClientController;
import gui.LoginPanel;
import model.Participant;
import model.User;


/***
 * Finn en løsning på score.
 * **/

public class DareController {
	private LoginPanel loginPanel;

	private User user;
	private Dare dare;
	private DontEatMeat dontEatMeat;

	private ClientController clientController;

	public DareController(LoginPanel loginPanel) {
		this.loginPanel = loginPanel;
	}

	// Starts the dare after pushing the button "its on like donkey kong" in gui
	public void setUpDareAfterGUI() {
		User anders = new User();
		anders.setName("Anders Tegnell"); //TODO: get user from gui instead;

		// create a new dare  + assign users to dare
		dontEatMeat = new DontEatMeat(this.user, anders);
		dontEatMeat.setTimeOfDare(3);

		//send dare to server via client
		sendDareToClient(dontEatMeat);
	}

	//FIXME
	// Skal ta imot yes or no enum fra GUI, og legge til score deretter.
	public void updateScore(YesOrNo yesOrNo) {

		if (yesOrNo.equals("Yes")) {
			dontEatMeat.setScore(YesOrNo.Yes);
		}
		if (yesOrNo.equals("No")) {
			dontEatMeat.setScore(YesOrNo.No);
		}
	}


	public void sendDareToClient(Dare toSend) {
		clientController.sendCreatedDare(toSend);
	}

	//Login mock, set active user and sends user to server
	public void loginUser(String name) {
		this.user = new User(name);
		clientController = new ClientController(this.user, this);
	}

	//FIXME
	// GUI setter challengede participant fra JComboBox fra gui når start button er aktivert
	public Participant setChallengedParticipant(String opponent) {
		user = new User(opponent);
		return new Participant(this.user, dare);
	}

	public void setDarePanelData(Dare dare) {
		//Work out which user is the opponent by comparing with the logged in user
		String competitor = user.getName().equalsIgnoreCase(dare.getInstigator().getUser().getName()) ? dare.getChallenged().getUser().getName() : dare.getInstigator().getUser().getName();

		//Add dare information in GUI
		loginPanel.getInfodare().setLblHeaderDareName(dare.toString());
		loginPanel.getInfodare().setLblGetOpponent(competitor);
	}
}

