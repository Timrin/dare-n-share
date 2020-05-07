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
     *
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

    public void insertParticipantsToDB(String userID, int dareID) {
        ParticipantTable.addParticipant(userID, dareID);
    }


    /**
     * Gets dare data from database in the form of a ResultSet. Saves all dare values from query and
     * sets new java Dare object with said values. Returns Dare object
     *
     * @param dareId unique ID of Dare
     * @return returns the requested Dare object
     */
    public Dare getDare(int dareId) {

        //Gets ResultSet from Database
        ResultSet resultFromQuery = DareTable.getDareFromDB(dareId);

        Dare dare = new Dare();

        try {
            while (resultFromQuery.next()) {

                //Gets Objective
                String objectiveType = resultFromQuery.getString("ObjectiveType");
                System.out.println("YOYO " + objectiveType);
                String objectiveGoal = resultFromQuery.getString("Objective");

                //Gets Scope
                String scopeType = resultFromQuery.getString("ScopeType");
                int scopeLength = resultFromQuery.getInt("ScopeLength");

                //Gets start and end date
                String start = resultFromQuery.getString("Start");
                String end = resultFromQuery.getString("End");

                //Sets dare value
                dare.setObjectiveFromDB(objectiveType, objectiveGoal);
                System.out.println("DARETABLE: " + dare.getObjectiveFromDB() + " type:  " + dare.getObjectiveFromDB().get("type"));
                dare.setScopeFromDB(scopeType, scopeLength);
                dare.setStartDate(start);
                dare.setEndDate(end);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        ResultSet resultFromQuery = ParticipantTable.getParticipantUserIdFromDare(dareId);
        ArrayList<Map> list = new ArrayList();


        try {
            while (resultFromQuery.next()) {
                String userId = resultFromQuery.getString("UserId");
                Map m = new LinkedHashMap();
                m.put("uid", userId);

                String userName = UserTable.getUser(userId);
                m.put("name", userName);
                System.out.println(userName);

                JSONArray scores = getScore(dareId, userId);
                m.put("score", scores); //fixme vi jobber her
                list.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     *
     */
    private JSONArray getScore(int dareId, String userId) {
        ResultSet resultSetScore = ParticipantTable.getScoreFromDB(dareId, userId);
        String[] score = new String[10];
        JSONArray jsonScore = new JSONArray();

        try {
            while (resultSetScore.next()) {
                String fullScoreString = resultSetScore.getString("Score");
                if (fullScoreString==null){
                    return jsonScore;
                }

                score = fullScoreString.split(":");

                for (int i = 0; i < score.length; i++) {
                    if (score[i] != null) {

                        if (score[i].equals("true")) {
                            jsonScore.add(true);
                        } else if (score[i].equals("false")) {
                            jsonScore.add(false);
                        }

                        //jsonScore.add(score[i]);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonScore;
    }

    /**
     *
     */
    public ArrayList<String> getAllDareIDForOneUser(String uid) {
        ResultSet resultFromQuery = ParticipantTable.getAllDareIdForUser(uid);
        ArrayList<String> list = new ArrayList<>(); //fixme kanskje map?

        try {
            while (resultFromQuery.next()) {
                int dareId = resultFromQuery.getInt("DareId");
                list.add(String.valueOf(dareId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     *
     */
    public ArrayList<Map> getFriendsFromDB(String uid) {
        ResultSet resultFromQuery = FriendsTable.getFriends(uid);

        ArrayList<Map> friendslist = new ArrayList<>();

        try {
            while (resultFromQuery.next()) {
                HashMap<String, String> mapOfFriend = new HashMap<>();
                String userId = resultFromQuery.getString("user");
                mapOfFriend.put("uid", userId);
                String username = UserTable.getUser(userId);
                mapOfFriend.put("name", username);
                friendslist.add(mapOfFriend);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friendslist;
    }

    /***
     *
     *
     */
    public void addUserScoreToDB(int dareid, String userId, String score) {

        ResultSet resultFromQuery = ParticipantTable.getScoreFromDB(dareid, userId);
        String newScore = "";
        try {
            if (resultFromQuery == null) {
                ParticipantTable.addToScore(dareid, userId, score);
            } else {
                while (resultFromQuery.next()) {
                    String oldScore = resultFromQuery.getString("Score");
                    newScore = oldScore + ":" + score;
                    System.out.println("NEW SCORE " + newScore);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ParticipantTable.addToScore(dareid, userId, newScore);

    }

    public void addFriendsToDB(String userId,String friendId){

        FriendsTable.addFriends(userId, friendId);
        FriendsTable.addFriends(friendId,userId);

    }

}
