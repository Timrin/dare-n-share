package dareSetUp;

import model.Participant;
import model.User;

import java.io.Serializable;

/**
 * Should maybe be an abstract class, rather than an interface. todo if so.
 * Every dare has a start and a stop.
 */

public abstract class Dare implements Serializable {

    private Participant participant1;
    private Participant participant2;

    private boolean goalAchevied = true;



    public Dare(User user1, User user2) {
        participant1 = new Participant(user1, this);
        participant2 = new Participant(user2, this);

    }

    public  boolean start(){
        return !goalAchevied;
    }

    public boolean stop(){
        return goalAchevied;
    }

    public Participant getParticipant(User user) {
        Participant ret = null;
        if (participant1.getUser().getName().equals(user.getName())) {
            ret = participant1;
        } else if (participant2.getUser().getName().equals(user.getName())) {
            ret = participant2;
        }
        return ret;
    }


}
