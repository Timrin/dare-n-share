package Converter;

/**
 *
 * This class is a controller.
 * Purpose: to map out the logic of the dares
 * before sending the objects to the database. // and after ?
 * @date 24/04-20
 * @version 1.0
 * @author Kamilla, Steven, Julia - XP pair programming
 * */

public class Controller {
    private Dare dare;
    private User user;
    private Score score;


    public Controller() {

    }

    public Controller(Dare dare, User user, Score score) {
        this.dare = dare;
        this.user = user;
        this.score = score;
    }
    //todo: fill out this method
    public void addScoreToUsersDare(){

    }


    // todo: or add Dare to User?
    public void addUserToDare(){

    }


    //todo: We need to set a timestamp when server recieves dare from client
    //todo: also, a timer for when the dare is over.
    public void timeStampToDare(){

    }


}
