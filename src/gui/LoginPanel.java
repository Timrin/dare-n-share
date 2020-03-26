package gui;

import dareSetUp.DareController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

    private JPanel bkgroundPanel = new JPanel(new BorderLayout());
    private JPanel frontPanel = new JPanel();



    private JTextField nameTxt = new JTextField();
    private JLabel nameLbl = new JLabel("Username");
    private JButton loginBtn = new JButton("Login");
    private JPanel popupPanel = new JPanel(new GridLayout(4,1));
    private JLabel welcomeText = new JLabel("Welcome");



    private JLabel welcomeName = new JLabel();
    private JLabel numberOfWins = new JLabel("Winners");
    private JLabel getNumberOfWins = new JLabel();
    private JLabel daresInProgress = new JLabel("Active Dares");


    private JButton seeDare = new JButton("See dare info");
    private JLabel changeToSomethingElse = new JLabel("Your active dares");
    private JButton newDare = new JButton("NEW DARE");

    private DarePanel darePanel;

    private DareController dareController;
    private InfoDarePanel infoDarePanel;

    public LoginPanel (){
        dareController = new DareController(this);
        setBkgroundPanel();
        setFrontPanel();

        darePanel = new DarePanel(this,dareController);
        darePanel.setVisible(false);

        infoDarePanel = new InfoDarePanel(this);

    }

    public String getWelcomeName() {
        return welcomeName.getText();
    }

    public void setWelcomeName(String welcomeName) {
        this.welcomeName.setText(welcomeName);
    }

    public String getChangeToSomethingElse() {
        return changeToSomethingElse.getText();
    }

    public void setChangeToSomethingElse(String changeToSomethingElse) {
        this.changeToSomethingElse.setText(changeToSomethingElse);
    }

    public void setNameOfDare (String changeToSomethingElse) {
        this.changeToSomethingElse.setText(changeToSomethingElse);
    }

    public void setBkgroundPanel (){
        add(bkgroundPanel);
        bkgroundPanel.setBackground(Color.PINK);
        bkgroundPanel.add(frontPanel);
    }

    public void setFrontPanel(){
        add(frontPanel);//oklart om detta behövs
        frontPanel.setPreferredSize(new Dimension(400,400));
        frontPanel.add(nameLbl);
        frontPanel.add(nameTxt);
        nameTxt.setPreferredSize(new Dimension(100,30));
        frontPanel.add(loginBtn);
        loginBtn.addActionListener(e -> {

            frontPanel.setVisible(false);
            popupPanel.setVisible(true);
            bkgroundPanel.add(popupPanel);
            setPopupPanel();
           dareController.loginUser(nameTxt.getText());

        });
        frontPanel.setBackground(Color.PINK);


    }


    public void setPopupPanel(){
        add(popupPanel);
        popupPanel.setBackground(Color.CYAN);
        popupPanel.setPreferredSize(new Dimension(400,500));
        setUpWelcomePanel();
        setUpDarePanel();
        setUpWinnersPanel();
        popupPanel.add(newDare);
        newDare.setBackground(Color.ORANGE);
        TitledBorder title;
        title = BorderFactory.createTitledBorder("New Dare");
        newDare.setBorder(title);
        title.setTitleJustification(TitledBorder.LEFT);
        title.setTitleColor(Color.BLACK);
        newDare.addActionListener(e -> {
            frontPanel.setVisible(false);
            popupPanel.setVisible(false);
            darePanel.setVisible(true);
            bkgroundPanel.add(darePanel);

        });


    }
    public String getNameTxt() {
        return nameTxt.getText();
    }


    /**
     * setUpPanel for Welcome
     */
    public void setUpWelcomePanel(){
        JPanel welcomePanel = new JPanel(new BorderLayout());
        popupPanel.add(welcomePanel);
        welcomePanel.add(welcomeText);
        welcomeName = new JLabel(getNameTxt());
        welcomePanel.setPreferredSize(new Dimension(200,200));
        welcomePanel.add(welcomeName);

        welcomePanel.setBackground(Color.PINK);
        TitledBorder title;
        title = BorderFactory.createTitledBorder("Welcome Panel");
        welcomePanel.setBorder(title);
        title.setTitleJustification(TitledBorder.LEFT);

    }

    /**
     * Panel for one part of gridlayout that displays button to view active dares
     */
    public void setUpDarePanel(){
        JPanel darePanel = new JPanel(new BorderLayout());
        popupPanel.add(darePanel);
        darePanel.add(daresInProgress);
        darePanel.add(changeToSomethingElse);
        darePanel.setBackground(Color.GREEN);
        TitledBorder title;

        title = BorderFactory.createTitledBorder("Dare Panel");
        darePanel.setBorder(title);
        title.setTitleJustification(TitledBorder.LEFT);
        darePanel.add(seeDare);
        seeDare.setBackground(Color.GREEN);

        seeDare.addActionListener(e -> {
            bkgroundPanel.add(infoDarePanel);
            darePanel.setVisible(false);
            frontPanel.setVisible(false);
            popupPanel.setVisible(false);

        });


    }

    /**
     * Panel for one part of gridLayout that displays numer of wins (image of medals)
     */
    public void setUpWinnersPanel(){
        JPanel winnerPanel = new JPanel(new BorderLayout());
        popupPanel.add(winnerPanel);
        winnerPanel.setBackground(Color.WHITE);
        TitledBorder title;
        title = BorderFactory.createTitledBorder("YOUR MEDAL PLAZA");
        winnerPanel.setBorder(title);
        title.setTitleJustification(TitledBorder.LEFT);
        ImageIcon medalIcon = new ImageIcon("images/iconMedals.jpg");
        Image image = medalIcon.getImage();
        image = image.getScaledInstance(130,130, Image.SCALE_AREA_AVERAGING);
        medalIcon = new ImageIcon(image);
        JLabel iconLbl = new JLabel(medalIcon);
        winnerPanel.add(iconLbl);


    }

    public void setFrame(){
        JFrame frame = new JFrame("Challenged");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.PINK);
        frame.setPreferredSize(new Dimension(400,520));
        frame.pack();
        frame.add(new LoginPanel());
        frame.setVisible(true);

    }
    public InfoDarePanel getInfodare (){
        return infoDarePanel;
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Challenger");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.PINK);
        frame.setPreferredSize(new Dimension(400,520));
        frame.pack();
        frame.add(new LoginPanel());
        frame.setVisible(true);




    }

    public void showHomeScreen() {
        frontPanel.setVisible(false);
        popupPanel.setVisible(true);
        darePanel.setVisible(false);
    }
}
