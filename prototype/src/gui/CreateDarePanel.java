/**
 * GUI for prototype where user can create a new dare.
 */
package gui;

import dareSetUp.Challenges;
import dareSetUp.DareController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateDarePanel extends JPanel {

	private JPanel popUpPanel = new JPanel(new GridLayout(4, 1));
	private JPanel headerPanel = new JPanel(new BorderLayout());
	private JLabel dareHeader = new JLabel();

	private JPanel dareInfoPanel = new JPanel(new FlowLayout());
	private JLabel txtExplainingDare = new JLabel();
	private JComboBox<Challenges> dares = new JComboBox();

	private JPanel friendsPanel = new JPanel(new FlowLayout());
	private JLabel addingFriend = new JLabel();
	private JComboBox<String> friends = new JComboBox();

	private JPanel btnPanel = new JPanel(new BorderLayout());
	private JButton startDareBtn = new JButton("IT´S ON LIKE DONKEY KONG");
	private JPanel panelForActiveDare = new JPanel(new GridLayout(4, 1));

	private DareController dareController;
	private LoginPanel loginPanelRef;

	public CreateDarePanel(LoginPanel loginPanelRef, DareController dareController) {
		this.loginPanelRef = loginPanelRef;
		this.dareController = dareController;
		setUpDare();
		setHeaderPanel();
		setDareInfoPanel();
		setFriendsPanel();
		setBtnPanel();
		//dares.addActionListener(this);
		//friends.addActionListener(this);
		startDareBtn.addActionListener(e -> {
			//loginPanelRef.setFrame();
			//  new InfoDarePanel();
			loginPanelRef.showHomeScreen();
			popUpPanel.setVisible(false);
			dareController.setUpDareAfterGUI();
			dareController.setChallengedParticipant(String.valueOf(friends));
		});
	}

	public JComboBox getDares() {
		return dares;
	}

	/**
	 * Midlertidig metode, for hardkoding av dontEatMeat challenge.
	 * -- Prototype kode
	 * */

	public void setUpDare() {
		add(popUpPanel);
		popUpPanel.setBackground(Color.MAGENTA);
		popUpPanel.setPreferredSize(new Dimension(390, 400));

	}

	/**
	 * This reads the selected friend in the friends list and returns the username of that friend
	 *
	 * @return username of selected friend
	 */
	public String getSelectedFriend() {
		return (String) friends.getSelectedItem();
	}

	public void setHeaderPanel() {
		popUpPanel.add(headerPanel);
		headerPanel.setBackground(Color.PINK);
		headerPanel.add(dareHeader);
		dareHeader.setText("NEW DARE");
	}

	/**
	 * panel where user can choose type of dare (in this prototype only no meat will be used)
	 */
	public void setDareInfoPanel() {
		popUpPanel.add(dareInfoPanel);
		dareInfoPanel.setBackground(Color.ORANGE);
		dareInfoPanel.add(txtExplainingDare);
		txtExplainingDare.setText("Choose your dare");
		dareInfoPanel.add(dares);
		dares.addItem(Challenges.DontEatMeat);
		dares.addItem(Challenges.FeedTheDucks);
		//dares.addActionListener(this);
	}

	/**
	 * panel where user can choose friend from list to dare.
	 */
	public void setFriendsPanel() {
		popUpPanel.add(friendsPanel);
		friendsPanel.setBackground(Color.WHITE);
		friendsPanel.add(addingFriend);
		addingFriend.setText("Choose a friend to dare");
		friendsPanel.add(friends);
		friends.getSelectedIndex();

		//Hardcoded values for the friends list.
		friends.addItem("The Donald");
		friends.addItem("Anders Tegnell");
	}

	public void setBtnPanel() {
		popUpPanel.add(btnPanel);
		btnPanel.setBackground(Color.ORANGE);
		btnPanel.add(startDareBtn);

		startDareBtn.setBorderPainted(true);
		startDareBtn.setBackground(Color.GREEN);
	}

}
