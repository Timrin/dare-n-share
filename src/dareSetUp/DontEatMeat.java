package dareSetUp;

import model.Participant;
import model.User;

public class DontEatMeat extends TimeYesNoDare{
    private User user;
    private Participant participant;

    public DontEatMeat(Participant participant, User user){
        super();
        this.participant = participant;
        this.user = user;
    }

    @Override
    public void setDare() {
        super.setDare();

    }

}
