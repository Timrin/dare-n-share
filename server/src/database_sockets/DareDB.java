package database_sockets;

/**
 * @author Julia and Kamilla - XP pair programming
 * */

import java.util.ArrayList;
import java.util.HashMap;

public class DareDB {

   // private ArrayList<String> dares = new ArrayList<String>();
    private HashMap<Integer,String> dares = new HashMap<Integer, String>();



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
    public void createDare(int id, String dare){
        //dares.add(dare);

        if(!dares.containsKey(id)) {
            dares.put(id, dare);
        }
    }

    //??
    public String deliverDare(){
        return dares.toString();
    }
}
