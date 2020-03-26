package dareSetUp;

import model.Participant;
import model.User;

public class DontEatMeat extends TimeYesNoDare{
    private User user;
    private Participant participant;

    public DontEatMeat(){
        super();
        this.participant = participant;
        this.user = user;
    }

    @Override
    public void setDare() {
        super.setDare();
    }

    public void setParticipant(Participant participant){
        this.participant=participant;
    }
    public void setUser(User user){
        this.user=user;
    }

    public User getUser() {
        return user;
    }

    public Participant getParticipant() {
        return participant;
    }
}
