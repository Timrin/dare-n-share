package Converter;

import database.DBController;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

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

    private DBController dbController;

    public Controller() {
        dbController = new DBController(this);
    }

    /**
     * Use this method to add a new dare to the database
     * Deconstructs a dare and sends along requested values
     * @param dare Java object Dare
     */
    public int addNewDare(Dare dare){

        //Gets objective values from dare
        String objectiveType = dare.getObjective().get("type").toString();
        String objectiveGoal = dare.getObjective().get("goal").toString();

        //Gets scope values from dare
        String scopeType = dare.getScope().get("type").toString();
        int scopeLength = Integer.parseInt(dare.getScope().get("length").toString());


        //Gets start and end date from dare
        String start = dare.getStartDate();
        String end = dare.getEndDate();

        //Gets list of participants from dare
        ArrayList<Map> participants = dare.getParticipants();

        //Sends dare info to be stored in database, which in turn returns generated dare ID
        int dareId = dbController.sendNewDareToDB(objectiveType, objectiveGoal, scopeType, scopeLength,
                start, end, participants);

        return dareId;
    }





    //todo: fill out this method
    //Participants recieves a string, of an array (score array(true true false)). DB recieves a string from participants.
    public void addScoreToUsersDare(){}


    /**
     * Gets dare from database using dareId.
     * @param dareId
     * @return
     */
    public Dare getDare(int dareId){
       return dbController.getDare(dareId);
    }


    /***********************************************
     * Build user
     * */
    public User getUserFromDB(String uid){
        User user = new User();
       // dbController.getUserFromDB(uid);
        user.setDares(dbController.getAllDareIDForOneUser(uid));
        user.setName(dbController.getUserFromDB(uid));
        user.setUid(uid);
        user.setFriendsList(dbController.getFriendsFromDB(uid));
        System.out.println(user.getDares() + " controller get user from db");
        return user;
    }
    public ArrayList<String> getDareIdForUser(String id){
        return dbController.getAllDareIDForOneUser(id);
    }



    /**
     * Use this method to add a new user to the database
     * Deconstructs a Java object User and sends along requested values
     * @param user Java object User
     */
    public void addNewUser(User user){
        dbController.sendUserToDB(user.getUid(), user.getName());
    }


/*********************************************@*******@***********@
 * Score methods
 *
 * */

public boolean addScore (Score score){
    int dareId = Integer.parseInt(score.getDid());
    System.out.println("Controller addScore, dareID: "+ dareId);
    String userId = score.getUid();
    System.out.println("Controller addScore, userID: "+ userId);
    String point = score.getPoint();
    System.out.println("Controller addScore, point: "+ point);

    Dare dare = dbController.getDare(dareId);
    System.out.println("Controller addScore, dare exists: " + dare.getEndDate());
    int length = Integer.parseInt(dare.getScopeFromDB().get("length").toString());
    System.out.println("Scope length" + length);

//    for(int i = 0; i < participants.size(); i++){
//        if(participants.get(i).get("uid") == userId) {
//            ja = (JSONArray) participants.get(i).get("score");
//        }
//    }
//    if(ja.get(0) instanceof Integer || ja.get(0) == null) {
//        System.out.println("Score not valid");
//    return;
//    }
    int nbrOfScores = dbController.getScore(dareId, userId).size();
    System.out.println("Number of scores posted so far: " + nbrOfScores);

    if(length > nbrOfScores) {
        dbController.addUserScoreToDB(dareId, userId, point);
        return true;
    }else{
        return false;
    }
}

}
