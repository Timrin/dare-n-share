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
    private JLabel changeToSomethingElse = new JLabel("NO ACTIVE DARES FOR YOU ");
    private JButton newDare = new JButton("NEW DARE");

    private DarePanel darePanel;

    private DareController dareController;

    public LoginPanel (){
        setBkgroundPanel();
        setFrontPanel();

        darePanel = new DarePanel(this);
        darePanel.setVisible(false);

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
           // dareController.loginUser(getNameTxt());


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
            bkgroundPanel.add(new DarePanel(this));

        });


    }
    public String getNameTxt() {
        return nameTxt.getText();
    }

    public JPanel getBkgroundPanel(){
        return   this.bkgroundPanel;
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
        //  title = BorderFactory.createTitledBorder("PanelRight");
        title = BorderFactory.createTitledBorder("Welcome Panel");
        welcomePanel.setBorder(title);
        title.setTitleJustification(TitledBorder.LEFT);

    }
    public void setUpDarePanel(){
        JPanel darePanel = new JPanel(new BorderLayout());
        popupPanel.add(darePanel);
        darePanel.add(daresInProgress);
        darePanel.add(changeToSomethingElse);
        darePanel.setBackground(Color.GREEN);
        TitledBorder title;
      //  title = BorderFactory.createTitledBorder("PanelRight");
        title = BorderFactory.createTitledBorder("Dare Panel");
        darePanel.setBorder(title);
        title.setTitleJustification(TitledBorder.LEFT);
    }

    public void setUpWinnersPanel(){
        JPanel winnerPanel = new JPanel(new BorderLayout());
        popupPanel.add(winnerPanel);
        winnerPanel.setBackground(Color.WHITE);
        TitledBorder title;
      //  title = BorderFactory.createTitledBorder("PanelRight");
        title = BorderFactory.createTitledBorder("YOUR MEDAL PLAZA");
        winnerPanel.setBorder(title);
        title.setTitleJustification(TitledBorder.LEFT);
        //winnerPanel.add(numberOfWins);
       // numberOfWins.setText("");
      //  winnerPanel.add(getNumberOfWins);
        ImageIcon medalIcon = new ImageIcon("images/iconMedals.jpg");
        Image image = medalIcon.getImage();
        image = image.getScaledInstance(130,130, Image.SCALE_AREA_AVERAGING);
        medalIcon = new ImageIcon(image);
        JLabel iconLbl = new JLabel(medalIcon);
        winnerPanel.add(iconLbl);


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
