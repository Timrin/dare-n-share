/**
 * GUI class that view page with information about active dare were the user will add information
 * if they succseeded with their daily goal and update their score.
 * This code is ONLY for prototype. will not be used in finished product.
 *
 */

package gui;

import dareSetUp.Challenges;
import dareSetUp.DareController;
import dareSetUp.YesOrNo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoDarePanel extends JPanel {
    private JPanel bkgroundPanel2 = new JPanel();
    private JPanel panelForGrid = new JPanel(new GridLayout(5,1));
    private JPanel panelForHeader = new JPanel();
    private JPanel panelForTime = new JPanel();
    private JPanel panelForYesNo = new JPanel();
    private JPanel panelForCurrentScore = new JPanel();
    private JPanel panelForOpponent = new JPanel();
    private JLabel lblForWelcome = new JLabel("");
    private JLabel lblHeaderDareName = new JLabel();
    private JLabel lblTime = new JLabel("Did you eat meat today?");
    private JLabel lblDucks = new JLabel("Did you feed your local duck today?");
    private JLabel lblGetDays = new JLabel();
    private JButton addAnswer = new JButton("Add answer");
    private JLabel lblTxtForDareInfo = new JLabel();
    private JLabel lblForQuestion = new JLabel();
    private JCheckBox yesCheckBox = new JCheckBox("Yes");
    private JCheckBox noCheckBox = new JCheckBox("NO");
    private JLabel lblCurrentScore = new JLabel();
    private JLabel lblGetCurrentScore = new JLabel();
    private JLabel lblOpponent = new JLabel("Your Competitor: ");
    private JLabel lblGetOpponent = new JLabel();
    private JPanel frontPanel = new JPanel();
    private LoginPanel loginPanel;


    public InfoDarePanel (LoginPanel loginPanel){
        this.loginPanel = loginPanel;

        setBkgroundPanel2();
        setPanelForGrid();
        setPanelForHeader();
        setPanelForYesNo();
        setPanelForOpponent();
        setPanelForCurrentScore();

    }


    public void setLblHeaderDareName(String lblHeaderDareName) {
       this.lblHeaderDareName.setText(lblHeaderDareName);
    }

    public String getLblHeaderDareName() {
        return lblHeaderDareName.getText();
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

    public String getOpponent(){
        return lblGetOpponent.getText();
    }

    public void setBkgroundPanel2(){
        loginPanel.add(bkgroundPanel2);
        add(bkgroundPanel2);
        bkgroundPanel2.setBackground(Color.GREEN);
        bkgroundPanel2.setPreferredSize(new Dimension(400,500));
        bkgroundPanel2.add(panelForGrid);

    }

    /**
     * panel for active dare information.
     */
    public void setPanelForHeader(){
        panelForGrid.add(panelForHeader);
        panelForHeader.add(lblForWelcome);
        panelForHeader.add(lblHeaderDareName);
        lblHeaderDareName.setText(getLblHeaderDareName());
        panelForGrid.setPreferredSize(new Dimension(400,400));


    }

    /**
     * Panel for Yes/No question. When user add an answer score will be updated.
     */
    public void setPanelForYesNo(){
        panelForGrid.add(panelForYesNo);

        panelForYesNo.add(lblTime);
        if(getLblHeaderDareName().equals(Challenges.DontEatMeat)) {
            panelForYesNo.add(lblForQuestion);
        }
        if(getLblHeaderDareName().equals(Challenges.FeedTheDucks)){
            panelForYesNo.add(lblDucks);
        }
        panelForYesNo.add(yesCheckBox);
        panelForYesNo.add(noCheckBox);
        panelForYesNo.add(addAnswer);
        addAnswer.addActionListener(e -> {
            if (yesCheckBox.isSelected()) {
                lblGetCurrentScore.setText("0 , Better luck next time");
            }
            else lblGetCurrentScore.setText("1000");
        });



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



}

