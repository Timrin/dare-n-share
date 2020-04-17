package database_sockets;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Kamilla
 *
 * This class is to be deleted. It is only here for testing purposes.
 * */

public class ScoreDB {
    private ArrayList<String> scoreBoolean = new ArrayList<>();
    private HashMap<Integer, String> participants = new HashMap<>();

    public ScoreDB() {}
    /**
     *  + points
     * **/
    public String getNewPoints(String bool){
        String newPoints;
        int points = 0;

        if (bool.equals("true")){
            points+=1;
        }else if (bool.equals("false")){
            points-=1;
        }
        newPoints = String.valueOf(points);

        return newPoints;
    }

        /**
         * Adds postet score to the arraylist..
         * */
    public boolean setScoreToUser(String post){
        return scoreBoolean.add(post);
    }


}
