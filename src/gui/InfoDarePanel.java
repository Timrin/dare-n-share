package gui;

import dareSetUp.DareController;

import javax.swing.*;
import java.awt.*;

public class InfoDarePanel extends JPanel {
    private JPanel bkgroundPanel2 = new JPanel();
    private JPanel panelForGrid = new JPanel(new GridLayout(5,1));
    private JPanel panelForHeader = new JPanel();
    private JPanel panelForTime = new JPanel();
    private JPanel panelForYesNo = new JPanel();
    private JPanel panelForCurrentScore = new JPanel();
    private JPanel panelForOpponent = new JPanel();
    private JLabel lblForWelcome = new JLabel("Welcome");
    private JLabel lblHeaderUserName = new JLabel();
    private JLabel lblTime = new JLabel("Days of our Dare");
    private JLabel lblGetDays = new JLabel();
    private JTextField nameTxt = new JTextField();
    private JLabel nameLbl = new JLabel("Username");
    private JButton loginBtn = new JButton("Login");

    private JLabel lblTxtForDareInfo = new JLabel();
    private JLabel lblForQuestion = new JLabel();
    private JCheckBox yesCheckBox = new JCheckBox("YES");
    private JCheckBox noCheckBox = new JCheckBox("NO");
    private JLabel lblCurrentScore = new JLabel();
    private JLabel lblGetCurrentScore = new JLabel();

    private JLabel lblOpponent = new JLabel("Your Competitor: ");

    private JLabel lblGetOpponent = new JLabel();

    private JPanel frontPanel = new JPanel();

    private DareController dareController;


    public InfoDarePanel (){
        setUpFrame();
        setFrontPanel();
       /* setBkgroundPanel2();
        setPanelForHeader();
        setPanelForYesNo();
        setPanelForOpponent();
        setPanelForCurrentScore();

        */

    }


    public void setLblHeaderUserName(String lblHeaderUserName) {
       this.lblHeaderUserName.setText(lblHeaderUserName);
    }

    public void setLblGetDays(String lblGetDays) {
        this.lblGetDays.setText(lblGetDays);
    }

    public void setLblGetCurrentScore(String lblGetCurrentScore) {
        this.lblGetCurrentScore.setText(lblGetCurrentScore);
    }

    public void setLblGetOpponent(String lblGetOpponent) {
        this.lblGetOpponent.setText(lblGetOpponent);
    }

    public void setBkgroundPanel2(){
        add(bkgroundPanel2);
        bkgroundPanel2.setBackground(Color.GREEN);
        bkgroundPanel2.setPreferredSize(new Dimension(400,500));
        bkgroundPanel2.add(panelForGrid);

    }

    public void setUpFrame(){
        JFrame frame = new JFrame("Challenged");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.PINK);
        frame.setPreferredSize(new Dimension(400,520));
        frame.pack();
        frame.add(this);
        frame.setVisible(true);
    }

    public void setPanelForHeader(){
        panelForGrid.add(panelForHeader);
        panelForHeader.add(lblForWelcome);
        panelForHeader.add(lblHeaderUserName);


    }

    public void setPanelForYesNo(){
        panelForGrid.add(panelForYesNo);

        panelForYesNo.add(lblTime);

        panelForYesNo.add(lblForQuestion);
        panelForYesNo.add(yesCheckBox);
        panelForYesNo.add(noCheckBox);



    }

    public void setPanelForOpponent(){
        panelForGrid.add(panelForOpponent);
        panelForOpponent.add(lblOpponent);
        panelForOpponent.add(lblGetOpponent);
        panelForOpponent.setBackground(Color.ORANGE);

    }
    public void setPanelForCurrentScore(){
        panelForGrid.add(panelForCurrentScore);
        panelForCurrentScore.add(lblCurrentScore);
        lblCurrentScore.setText("Your score is: ");
        panelForCurrentScore.add(lblGetCurrentScore);
        panelForCurrentScore.setBackground(Color.PINK);

    }

    public void setBkgroundPanel (){
        add(bkgroundPanel2);
        bkgroundPanel2.setBackground(Color.PINK);
        bkgroundPanel2.add(frontPanel);
    }

    public void setPanelForGrid(){
        bkgroundPanel2.add(panelForGrid);
        panelForGrid.setBackground(Color.BLUE);

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
            panelForGrid.setVisible(false);
          //  bkgroundPanel2.add(panelForGrid);

          //  loginPanel.showHomeScreen();
            //setBkgroundPanel();
            //setPanelForGrid();
        //    this.dareController.loginUser(nameTxt.getText());

        });
        frontPanel.setBackground(Color.PINK);


    }





}

