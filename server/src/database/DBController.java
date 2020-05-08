package database;

import Converter.Controller;
import Converter.Dare;
import org.json.simple.JSONArray;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Database controller,
 *
 * @author julia, Steven, Kamilla - xp pair programming
 */

public class DBController {
    private Controller controller;

    public DBController(Controller controller) {
        this.controller = controller;
    }

    /**
     *
     */
    public void sendUserToDB(String userID, String name) {

        UserTable.addUserToDB(userID, name);
    }

    /**
     *
     */

    public String getUserFromDB(String uid) {

        String userName = "";
        userName = UserTable.getUser(uid);
        System.out.println(userName + " DBcontroller get user from db");
        return userName;
    }

    /**
     *FIXME: kontrollera userID om det finns innan insättning till dare tabell.
     */
    public int sendNewDareToDB(String objectiveType, String objectiveGoal, String scopeType,
                               int scopeLength, String start, String end, ArrayList<Map> participants) {

        int id = DareTable.insertNewDareToDB(objectiveType,
                objectiveGoal, scopeType, scopeLength, start, end);

        for (int i = 0; i < participants.size(); i++) {
            String userID = participants.get(i).get("uid").toString();
            ParticipantTable.addParticipant(userID, id);
        }
        return id;
    }


    /**
     * Gets dare data from database. Saves all dare values from query and
     * sets new java Dare object with said values. Returns Dare object
     *
     * @param dareId unique ID of Dare
     * @return returns the requested Dare object
     */
    public Dare getDare(int dareId) {

        //Gets ResultSet from Database
        // ResultSet resultFromQuery = DareTable.getDareFromDB(dareId);

       Dare dare = DareTable.getDareFromDB(dareId);

//        try {
//            while (resultFromQuery.next()) {
//
//                //Gets Objective
//                String objectiveType = resultFromQuery.getString("ObjectiveType");
//                System.out.println("YOYO " + objectiveType);
//                String objectiveGoal = resultFromQuery.getString("Objective");
//
//                //Gets Scope
//                String scopeType = resultFromQuery.getString("ScopeType");
//                int scopeLength = resultFromQuery.getInt("ScopeLength");
//
//                //Gets start and end date
//                String start = resultFromQuery.getString("Start");
//                String end = resultFromQuery.getString("End");
//
//                //Sets dare value
//                dare.setObjectiveFromDB(objectiveType, objectiveGoal);
//                System.out.println("DARETABLE: " + dare.getObjectiveFromDB() + " type:  " + dare.getObjectiveFromDB().get("type"));
//                dare.setScopeFromDB(scopeType, scopeLength);
//                dare.setStartDate(start);
//                dare.setEndDate(end);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        ArrayList<Map> participantsList = getAllUserFromDare(dareId);
        System.out.println(participantsList.get(0).get("uid"));

        dare.setParticipants(participantsList);

        return dare;
    }

    /**
     * This methods collects all users' user names in a given dare(by dare ID) and returns them in an ArrayList
     *
     * @param dareId The ID of the dare
     * @return ArrayList containing all user names of users in relevant dare
     */
    private ArrayList<Map> getAllUserFromDare(int dareId) {

        //Gets full ResultSet of participants in the dare from the database
       // ResultSet resultFromQuery = ParticipantTable.getParticipantUserIdFromDare(dareId);
       ArrayList <String> users =ParticipantTable.getParticipantUserIdFromDare(dareId);
        ArrayList<Map> list = new ArrayList();


        for(int i = 0; i<users.size();i++){
            String userId = users.get(i);
            Map m = new LinkedHashMap();

            m.put("uid", userId);

            String userName = UserTable.getUser(userId);
            m.put("name", userName);
            System.out.println(userName);

            JSONArray scores = getScore(dareId, userId);
            m.put("score", scores); //fixme vi jobber her
            list.add(m);
        }



//        try {
//            while (resultFromQuery.next()) {
//                String userId = resultFromQuery.getString("UserId");
//                Map m = new LinkedHashMap();
//                m.put("uid", userId);
//
//                String userName = UserTable.getUser(userId);
//                m.put("name", userName);
//                System.out.println(userName);
//
//                JSONArray scores = getScore(dareId, userId);
//                m.put("score", scores); //fixme vi jobber her
//                list.add(m);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return list;
    }

    /**
     *
     */
    public JSONArray getScore(int dareId, String userId) {
       // ResultSet resultSetScore = ParticipantTable.getScoreFromDB(dareId, userId);
        String score = ParticipantTable.getScoreFromDB(dareId,userId);
        JSONArray jsonScore = new JSONArray();
        String stringScore [] = score.split(":");
        for (int i = 0; i < stringScore.length; i++) {
            if (stringScore[i] != null) {

                if (stringScore[i].equals("true")) {
                    jsonScore.add(true);
                } else if (stringScore[i].equals("false")) {
                    jsonScore.add(false);
                }
//
//        try {
//            while (resultSetScore.next()) {
//                String fullScoreString = resultSetScore.getString("Score");
//                if (fullScoreString==null){
//                    return jsonScore;
//                }
//
//
//                score = fullScoreString.split(":");
//
//

                        //jsonScore.add(score[i]);
                    }
                }
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return jsonScore;
    }

    /**
     *return array of all dares for one userId
     */
    public ArrayList<String> getAllDareIDForOneUser(String uid) {

        ArrayList<Integer> dares= ParticipantTable.getAllDareIdForUser(uid);
        ArrayList<String> list = new ArrayList<>(); //fixme kanskje map?
        for (Integer dare : dares) {
            list.add(String.valueOf(dare));
        }

        return list;
    }

    /**
     *FIXME: resultset, kan behöva ändras.
     */
    public ArrayList<Map> getFriendsFromDB(String uid) {
      //  ResultSet resultFromQuery = FriendsTable.getFriends(uid);
        ArrayList<String> friendsUserIdList = FriendsTable.getFriends(uid);

        ArrayList<Map> friendslist = new ArrayList<>();

        for (String friends: friendsUserIdList){
            HashMap<String,String> mapOfFriend = new HashMap<>();
            mapOfFriend.put("uid", friends);
            String username = UserTable.getUser(friends);
            mapOfFriend.put("name",username);
            friendslist.add(mapOfFriend);
        }

//        try {
//            while (resultFromQuery.next()) {
//                HashMap<String, String> mapOfFriend = new HashMap<>();
//                String userId = resultFromQuery.getString("user");
//                mapOfFriend.put("uid", userId);
//                String username = UserTable.getUser(userId);
//                mapOfFriend.put("name", username);
//                friendslist.add(mapOfFriend);
//                resultFromQuery.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return friendslist;
    }

    /***
     * retrieves previous score (String) for user, add ":" + new score to String and add to database.
     *
     */
    public void addUserScoreToDB(int dareid, String userId, String score) {

        String scoreFromDB = ParticipantTable.getScoreFromDB(dareid, userId);
        String newScore = "";
        if (scoreFromDB == null) {
            ParticipantTable.addToScore(dareid, userId, score);
        } else {

                newScore = scoreFromDB + ":" + score;
                System.out.println("NEW SCORE " + newScore);

        }
        ParticipantTable.addToScore(dareid, userId, newScore);

    }

    public void addFriendsToDB(String userId,String friendId){

        FriendsTable.addFriends(userId, friendId);
        FriendsTable.addFriends(friendId,userId);

    }

}
