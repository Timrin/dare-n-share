package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

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
    private JLabel changeToSomethingElse = new JLabel("No");
    private JButton newDare = new JButton("NEW DARE");

    public LoginPanel (){
        setBkgroundPanel();
        setFrontPanel();

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
        newDare.setBackground(Color.MAGENTA);
        TitledBorder title;
        title = BorderFactory.createTitledBorder("New Dare");
        newDare.setBorder(title);
        title.setTitleJustification(TitledBorder.LEFT);
        title.setTitleColor(Color.BLACK);


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
        title = BorderFactory.createTitledBorder("PanelRight");
        title = BorderFactory.createTitledBorder("Dare Panel");
        darePanel.setBorder(title);
        title.setTitleJustification(TitledBorder.LEFT);
    }

    public void setUpWinnersPanel(){
        JPanel winnerPanel = new JPanel(new BorderLayout());
        popupPanel.add(winnerPanel);
        TitledBorder title;
        title = BorderFactory.createTitledBorder("PanelRight");
        title = BorderFactory.createTitledBorder("Winner panel ");
        winnerPanel.setBorder(title);
        title.setTitleJustification(TitledBorder.LEFT);
        winnerPanel.add(numberOfWins);
        winnerPanel.add(getNumberOfWins);
        ImageIcon icon2 = new ImageIcon("C:\\MAU\\Objektorienterade strömmar\\guiForDare\\images\\iconsmedals.jpg");
        Image image2 = icon2.getImage();
        Image newImg2 = image2.getScaledInstance(130,130, Image.SCALE_SMOOTH);
        icon2 = new ImageIcon(newImg2);
        JLabel iconLbl = new JLabel(icon2);
        winnerPanel.add(iconLbl);


    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("oegfdkgnldfkg");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.PINK);
        frame.setPreferredSize(new Dimension(400,520));
        frame.pack();
        frame.add(new LoginPanel());
        frame.setVisible(true);

    }

}
