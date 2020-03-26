package dareSetUp;

import model.Participant;
import model.User;

public class DontEatMeat extends TimeYesNoDare {
    private User user;
    private Participant participant;
    private YesOrNo yesOrNo;

    public DontEatMeat(User user1, User user2) {
        super(user1, user2);
        this.participant = participant;
        this.user = user;
    }

    @Override
    public void setDare() {
        super.setDare();
    }

    public void endDare() {
        super.endDare();
    }


    public void setScore(YesOrNo yesOrNo) {
        if (yesOrNo.equals("Yes")) {
            addToScore(1000);
        }
        if (yesOrNo.equals("No")) {
            addToScore(-100);
        }
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Participant getParticipant() {
        return participant;
    }

    @Override
    public String toString() {
        return String.format("Dont eat meat for " + String.valueOf(getTimeOfDare()) + " days");
    }
}
