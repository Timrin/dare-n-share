package dareSetUp;

import gui.DarePanel;
import gui.LoginPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DareController implements ActionListener {
    private LoginPanel loginPanel;
    private DarePanel darePanel;
    private Dare dare;

    private TimeYesNoDare timeYesNoDare;
    private TimeDare TimeDare;
    private TimeAnotherType timeAnotherType;

    private GoalDare goalDare;
    private GoalDareAnotherType goalDareAnotherType;
    private GoalYesNo goalYesNo;


    public DareController() {
        darePanel.getStartDareBtn().addActionListener(this);
    }

    // Starts the dare after pushing the button "its on like donkey kong" in gui
    @Override
    public void actionPerformed(ActionEvent e) {
        if (darePanel.getStartDareBtn().isSelected()) {

            if (TimeDare instanceof TimeYesNoDare) {
                timeYesNoDare.start();
            }
            if (TimeDare instanceof TimeAnotherType) {
                timeAnotherType.start();
            }


        }
    }
}
