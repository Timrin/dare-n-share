package database_sockets;

/**
 * @author Julia and Kamilla - XP pair programming
 * */

import java.util.ArrayList;
import java.util.HashMap;

public class DareDB {

   // private ArrayList<String> dares = new ArrayList<String>();
    private HashMap<Integer,String> dares = new HashMap<Integer, String>();
    private int nextId = 0; //Id of the next dare that is added

    /**
     * Expects an ID from the android app.
     * returns a dare with that ID - from database.
     * */
    public String getDare(int id){
        return dares.get(id);
    }

    /**
     * Expects an ID from the android app.
     * returns the a dare with that ID.
     * */
    public int createDare(String dare){
        //TODO: Add the id to the dare (in the string)

        //TODO: Assign the dare to the users

        dares.put(nextId, dare);

        return nextId++;
    }

    /* //Jsonconverter project
    public String deliverDare(){
        return dares.toString();
    }*/
}
