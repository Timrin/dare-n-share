package database;

import controller.Controller;
import entity.Dare;
import org.json.simple.JSONArray;

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
     * This method sends information about a new user to the database with the users ID, name and email
     *
     * @param userID
     * @param name
     * @param email
     */
    public void sendUserToDB(String userID, String name, String email) {
        UserTable.addUserToDB(userID, name, email);
    }

    /**
     * This method gets a user from the database. It uses a userID to get the user,
     * and returns a string with the users name
     *
     * @param uid
     * @return
     */
    public String getUserFromDB(String uid) {

        String userName = "";
        userName = UserTable.getUser(uid);
        return userName;
    }

    /**
     * This method gets a users email from the database. It uses a userID to get the user,
     * and returns a string with the users email
     *
     * @param uid
     * @return
     */
    public String getEmail(String uid) {
        String email = "";
        email = UserTable.getUserEmailById(uid);
        return email;
    }

    /**
     * FIXME: kontrollera userID om det finns innan insättning till dare tabell.
     */

    /**
     * This method sends a new dare to the database
     *
     * @param objectiveType
     * @param objectiveGoal
     * @param scopeType
     * @param scopeLength
     * @param start
     * @param end
     * @param participants
     * @return
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
        Dare dare = DareTable.getDareFromDB(dareId);
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
        ArrayList<String> users = ParticipantTable.getParticipantUserIdFromDare(dareId);
        ArrayList<Map> list = new ArrayList();

        for (int i = 0; i < users.size(); i++) {
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
        return list;
    }

    /**
     * Method get String of one users score in requested dare, add answers to JSONArray
     * @param dareId which dare the score is requested for
     * @param userId which user the score is requested for
     * @return JSONArray of scores
     */
    public JSONArray getScore(int dareId, String userId) {
        String score = ParticipantTable.getScoreFromDB(dareId, userId);
        JSONArray jsonScore = new JSONArray();
        String stringScore[] = score.split(":");
        for (int i = 0; i < stringScore.length; i++) {
            if (stringScore[i] != null) {

                if (stringScore[i].equals("true")) {
                    jsonScore.add(true);
                } else if (stringScore[i].equals("false")) {
                    jsonScore.add(false);
                }
            }
        }

        return jsonScore;
    }

    /**
     * Method that returns all DareID that one user have
     * @param uid which user is requesting all dares
     * @return ArrayList of all dare one user have
     */
    public ArrayList<String> getAllDareIDForOneUser(String uid) {

        ArrayList<Integer> dares = ParticipantTable.getAllDareIdForUser(uid);
        ArrayList<String> list = new ArrayList<>(); //fixme kanskje map?
        for (Integer dare : dares) {
            list.add(String.valueOf(dare));
        }

        return list;
    }

    /**
     * Method that get one users friends from database and returns name and userId of one users friends.
     */
    public ArrayList<Map> getFriendsFromDB(String uid) {
        ArrayList<String> friendsUserIdList = FriendsTable.getFriends(uid);

        ArrayList<Map> friendslist = new ArrayList<>();

        for (String friends : friendsUserIdList) {

            HashMap<String, String> mapOfFriend = new HashMap<>();
            mapOfFriend.put("uid", friends);
            String username = UserTable.getUser(friends);
            mapOfFriend.put("name", username);
            friendslist.add(mapOfFriend);

        }

        return friendslist;
    }

    /**
     * Retrieves previous score (String) for user, add ":" + new score to String and add to database.
     *
     * @param dareid
     * @param userId
     * @param score
     */
    public void addUserScoreToDB(int dareid, String userId, String score) {

        String scoreFromDB = ParticipantTable.getScoreFromDB(dareid, userId);
        String newScore = "";
        if (scoreFromDB == null) {
            ParticipantTable.addToScore(dareid, userId, score);
        } else {

            newScore = scoreFromDB + ":" + score;

        }
        ParticipantTable.addToScore(dareid, userId, newScore);

    }

    /**
     * Updates the database with users that have become friends
     *
     * @param friendId
     */
    public void addFriendsToDB(HashMap friendId) {

        String userId = (String) friendId.get("senderID");
        String friendEmail = (String) friendId.get("friendEmail");

        String friendID = UserTable.getUserIDWithEmail(friendEmail);
        FriendsTable.addFriends(userId, friendID);

    }
}
