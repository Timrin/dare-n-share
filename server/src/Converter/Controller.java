package Converter;

import database.DBController;

import java.sql.SQLException;

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
    private DBController dbController;
    private JsonConverterDare jsonConverterDare;
    private JsonConverterUser jsonConverterUser;


    public Controller() {


    }

    public Controller(Dare dare, User user, Score score) {
        this.dare = dare;
        this.user = user;
        this.score = score;
    }

    public Controller(JsonConverterDare jsonConverterDare){
        this.jsonConverterDare = jsonConverterDare;
        dbController = new DBController(this);

    }

    public Controller(JsonConverterUser jsonConverterUser){
        this.jsonConverterUser = jsonConverterUser;
        dbController = new DBController(this);
    }


    public void addNewDare(Dare dare){

        String objectiveType = dare.getObjective().get("type").toString();
        String objectiveGoal = dare.getObjective().get("goal").toString();
        String scopeType = dare.getScope().get("type").toString();
        int scopeLength = Integer.parseInt(dare.getScope().get("length").toString());
        String start = dare.getStartDate();
        String end = dare.getEndDate();

        try {
            dbController.sendNewDareToDB(objectiveType, objectiveGoal, scopeType, scopeLength, start, end);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void addNewUser(User user){
        try {
            dbController.sendUserToDB(user.getUid(), user.getName());
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void sendDareID(int id){
        jsonConverterDare.sendDareIDToPost(id);
    }

    public String getUserFromDB(String iud){
        return dbController.getUserFromDB(iud);
    }

    //todo: fill out this method
    //Participants recieves a string, of an array (score array(true true false)). DB recieves a string from participants.
    public void addScoreToUsersDare(){}


    // todo: or add Dare to User?
    // When a user posts a dare, the participant) dare array(json array in user class) will be updated.
    public void addUserToDare(){}


    public Dare getDare(int dareId){
       return dbController.getDare(dareId);

    }



}
