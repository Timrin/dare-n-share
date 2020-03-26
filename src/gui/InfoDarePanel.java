package gui;

import javax.swing.*;
import java.awt.*;

public class InfoDarePanel extends JPanel {
    private JPanel bkgroundPanel = new JPanel();
    private JPanel panelForGrid = new JPanel(new GridLayout(5,1));
    private JPanel panelForHeader = new JPanel();
    private JPanel panelForTime = new JPanel();
    private JPanel panelForYesNo = new JPanel();
    private JPanel panelForCurrentScore = new JPanel();
    private JPanel panelForOpponent = new JPanel();

    private JLabel lblHeader = new JLabel();
    private JLabel lblTime = new JLabel("Days of our Dare");
    private JLabel lblGetDays = new JLabel();

    private JLabel lblTxtForYesNo = new JLabel();
    private JLabel lblCurrentScore = new JLabel();
    private JLabel lblGetCurrentScore = new JLabel();

    private JLabel lblOpponent = new JLabel();
    private JLabel lblGetOpponent = new JLabel();


    public InfoDarePanel (){

    }
}

