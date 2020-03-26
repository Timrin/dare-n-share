package dareSetUp;

import model.Participant;
import model.User;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Should maybe be an abstract class, rather than an interface. todo if so.
 * Every dare has a start and a stop.
 */

public abstract class Dare implements Serializable {

    private Participant instigator;
    private Participant challenged;

    private boolean goalAchevied = true;

    private int id;


   // private static final int serialVersionUID =1; todo hmmm can we do this?
    private static final AtomicInteger atomicRefID = new AtomicInteger();
    private transient int refID;


    public Dare(User instigatorUser, User challengedUser) {
        instigator = new Participant(instigatorUser, this);
        challenged = new Participant(challengedUser, this);
       // refID = atomicRefID.incrementAndGet(); todo dette hører til der oppe. atmoic halløj

    }
    public int getRefID(){
        return refID;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract String toString();
}
