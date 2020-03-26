package dareSetUp;

import model.Participant;
import model.User;

import java.io.Serializable;

/**
 * Should maybe be an abstract class, rather than an interface. todo if so.
 * Every dare has a start and a stop.
 */

public abstract class Dare implements Serializable {

    private Participant instigator;
    private Participant challenged;

    private boolean goalAchevied = true;



    public Dare(User instigatorUser, User challengedUser) {
        instigator = new Participant(instigatorUser, this);
        challenged = new Participant(challengedUser, this);

    }

    public  boolean start(){
        return !goalAchevied;
    }

    public boolean stop(){
        return goalAchevied;
    }

    public Participant getInstigator() {
        return instigator;
    }

    public Participant getChallenged() {
        return challenged;
    }


}
