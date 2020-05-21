package controller;

import database.DBController;
import entity.Dare;
import entity.Score;
import entity.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is a controller that handles data from API endpoints before the
 * backend sends the data to the database.
 *
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
     *
     * @param dare Java object Dare
     */
    public int addNewDare(Dare dare) {

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


    /**
     * Gets dare from database using dareId.
     *
     * @param dareId
     * @return a dare object with the dareId
     */
    public Dare getDare(int dareId) {
        return dbController.getDare(dareId);
    }


    /**
     * Gets a user from database by its user ID.
     * @param uid
     * @return a user object with the userId (uid)
     */
    public User getUserFromDB(String uid) {
        User user = new User();
        user.setDares(dbController.getAllDareIDForOneUser(uid));
        user.setName(dbController.getUserFromDB(uid));
        user.setEmail(dbController.getEmail(uid));
        user.setUid(uid);
        user.setFriendsList(dbController.getFriendsFromDB(uid));

        return user;
    }


    /**
     * Use this method to add a new user to the database
     * Deconstructs a Java object User and sends along requested values
     *
     * @param user Java object User
     */
    public void addNewUser(User user) {
        dbController.sendUserToDB(user.getUid(), user.getName(), user.getEmail());
    }


    /**
     * This method adds to score in a dare with the belonging dareId.
     * If dare length is bigger than the number of scores, it returns true, and the user
     * gets to add to score.
     * If dare length is smaller than the number of scores, it returns false and the
     * user does not get to add to score
     * @param score
     * @return boolean
     */

    public boolean addScore(Score score) {
        int dareId = Integer.parseInt(score.getDid());

        String userId = score.getUid();
        String point = score.getPoint();

        Dare dare = dbController.getDare(dareId);

        int length = Integer.parseInt(dare.getScopeFromDB().get("length").toString());
        int nbrOfScores = dbController.getScore(dareId, userId).size();

        if (length > nbrOfScores) { // if true, user can add to score
            dbController.addUserScoreToDB(dareId, userId, point);
            return true;
        } else {
            return false;
        }

    }

    /**
     * This method recieves a hashmap of friend requests.
     * @param friend
     */
    public void addFriendToDBController(HashMap friend){
        dbController.addFriendsToDB(friend);

    }

}

