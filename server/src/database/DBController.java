package database;

import Converter.Controller;
import Converter.Dare;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Database controller,
 * @author julia
 */

public class DBController {
    private Controller controller;

    public DBController(Controller controller){
        this.controller=controller;
    }


    public void sendUserToDB(String userID, String name) {

        UserTable.addUserToDB(userID, name);
    }


    public String getUserFromDB(String uid){

        String userName = "";
        userName = UserTable.getUser(uid);
        return userName;
    }


    public void sendNewDareToDB(String objectiveType, String objectiveGoal,String scopeType,
                                int scopeLength, String start,String end) {

        int id = DareTable.insertNewDareToDB(objectiveType,
                objectiveGoal, scopeType, scopeLength, start, end);
    }

    public void getDareIDFromDB(int dareId){
        controller.sendDareID(dareId); // this ID will be used in insertParticipantToDB
    }

    public void combineDareIdAndParticipant(String participantId, String opponentId){ //Fixme: Change name?

    }



    public void insertParticipantsToDB(String userID,int dareID) {
        ParticipantTable.addParticipant(userID, dareID);
    }


    public void setDareFromDB(String objectiveType, String objectiveGoal, String scopeType,
                              int scopeLength, String start, String end, String uid1, String uid2){
        Dare dare = new Dare();
        dare.setObjectiveFromDB(objectiveType, objectiveGoal);
        System.out.println("YIHA "+ objectiveType +" "+ objectiveGoal + " " + dare.getObjectiveFromDB());
        dare.setScopeFromDB(scopeType, scopeLength);
        dare.setStartDate(start);
        dare.setEndDate(end);
        dare.addParticipants(uid1);
        dare.addParticipants(uid2);
    }

    /**
     * Gets dare data from database in the form of a ResultSet. Saves all dare values from query and
     * sets new java Dare object with said values. Returns Dare object
     * @param dareId unique ID of Dare
     * @return returns the requested Dare object
     */
    public Dare getDare(int dareId){

        //Gets ResultSet from Database
        ResultSet resultFromQuery = DareTable.getDareFromDB(dareId);

        Dare dare = new Dare();

        try {
            while (resultFromQuery.next()) {

                //Gets Objective
                String objectiveType = resultFromQuery.getString(2);
                System.out.println("YOYO " + objectiveType);
                String objectiveGoal = resultFromQuery.getString(3);

                //Gets Scope
                String scopeType = resultFromQuery.getString(4);
                int scopeLength = resultFromQuery.getInt(5);

                //Gets start and end date
                String start = resultFromQuery.getString(6);
                String end = resultFromQuery.getString(7);

                int participants = resultFromQuery.getInt(8); //Fixme: Should get from different table
              //  String userID = ParticipantTable.getParticipantUserIdFromDare(participants);

                int opponent = resultFromQuery.getInt(9); //Fixme: Should get from different table
            //    String userOpponentId = ParticipantTable.getParticipantUserIdFromDare(opponent);

                //Sets dare value
                dare.setObjectiveFromDB(objectiveType, objectiveGoal);
                System.out.println("DARETABLE: " + dare.getObjectiveFromDB() + " type:  " + dare.getObjectiveFromDB().get("type"));
                dare.setScopeFromDB(scopeType, scopeLength);
                dare.setStartDate(start);
                dare.setEndDate(end);
              //  dare.addParticipants(userID);
             //   dare.addParticipants(userOpponentId);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

       return dare;
    }

}
