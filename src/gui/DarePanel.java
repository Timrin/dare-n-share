/**
 * GUI for prototype
 * @author: Julia Björnberg
 */
package gui;

import dareSetUp.DareController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DarePanel extends JPanel implements ActionListener {
  //  private LoginPanel loginPanel;
    private JPanel popUpPanel = new JPanel(new GridLayout(4,1));
    private JPanel headerPanel = new JPanel(new BorderLayout());
    private JLabel dareHeader = new JLabel();

    private JPanel dareInfoPanel = new JPanel(new FlowLayout());
    private JLabel txtExplainingDare = new JLabel();
    private JCheckBox yes = new JCheckBox("Yes");

    private JPanel friendsPanel = new JPanel(new FlowLayout());
    private JLabel addingFriend = new JLabel();
    private JComboBox friends = new JComboBox();

    private JPanel btnPanel = new JPanel(new BorderLayout());
    private JButton startDareBtn = new JButton("IT´S ON LIKE DONKEY KONG");
    private DareController dareController;
    private LoginPanel loginPanel;



    public DarePanel (){
        setUpDare();
        setHeaderPanel();
        setDareInfoPanel();
        setFriendsPanel();
        setBtnPanel();
        yes.addActionListener(this);
        friends.addActionListener(this);
        startDareBtn.addActionListener(this);

    }

    public JCheckBox getYes() {
        return yes;
    }
    /**
     * Midlertidig metode, for hardkoding av dontEatMeat challenge.
     * -- Prototype kode
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
      /*  if (e.getSource()==yes){
            dareController.setUpDareAfterGUI();
            dareController.setOpponent(getFriends());
        }

       */
        if(startDareBtn.isSelected()){
            new InfoDarePanel();

        }

    }

    public void setUpDare(){
        add(popUpPanel);
        popUpPanel.setBackground(Color.MAGENTA);
        popUpPanel.setPreferredSize(new Dimension(390,400));

    }

    public String getFriends() {
        return (String) friends.getSelectedItem();
    }


    public void setHeaderPanel(){
        popUpPanel.add(headerPanel);
        headerPanel.setBackground(Color.PINK);
        headerPanel.add(dareHeader);
        dareHeader.setText(" NEW DARE");
    }

    public void setDareInfoPanel(){
        popUpPanel.add(dareInfoPanel);
        dareInfoPanel.setBackground(Color.ORANGE);
        dareInfoPanel.add(txtExplainingDare);
        txtExplainingDare.setText("No meat for 3 whole days my friend");
        dareInfoPanel.add(yes);
        yes.setBorderPainted(true);
        yes.addActionListener(this);
    }

    public void setFriendsPanel(){
        popUpPanel.add(friendsPanel);
        friendsPanel.setBackground(Color.WHITE);
        friendsPanel.add(addingFriend);
        addingFriend.setText("Choose a friend to dare");
        friendsPanel.add(friends);
        friends.getSelectedIndex();
        friends.addItem("The Donald");
        friends.addItem("Anders Tegnell");
    }

    public void setBtnPanel(){
        popUpPanel.add(btnPanel);
        btnPanel.setBackground(Color.ORANGE);
        btnPanel.add(startDareBtn);
        startDareBtn.addActionListener(this);
        startDareBtn.setBorderPainted(true);
        startDareBtn.setBackground(Color.GREEN);
    }

    public JButton getStartDareBtn(){
        return getStartDareBtn();
    }





}
