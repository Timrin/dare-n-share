package database_sockets;

/**
 * @author Julia and Kamilla - XP pair programming
 * */

import java.util.ArrayList;

public class DareDB {

    private ArrayList<String> dares = new ArrayList<String>();

    /**
     * Expects an ID from the android app.
     * returns a dare with that ID.
     * */
    public String getDare(int id){
        return dares.get(id);
    }


    /**
     * Expects an ID from the android app.
     * returns the a dare with that ID.
     * */
    public void recieveDareFromClient(String dare){
        dares.add(dare);

    }

    public String deliverDare(){
        return dares.toString();
    }



}
