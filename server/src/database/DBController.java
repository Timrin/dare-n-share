package database;

import Converter.Controller;
import Converter.Dare;

import javax.servlet.http.Part;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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


    public int sendNewDareToDB(String objectiveType, String objectiveGoal,String scopeType,
                                int scopeLength, String start,String end, ArrayList<String> participants) {

        int id = DareTable.insertNewDareToDB(objectiveType,
                objectiveGoal, scopeType, scopeLength, start, end);

        for (String participant : participants) {
            ParticipantTable.addParticipant(participant, id);
        }
        return id;
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

        dare.setParticipants(getAllUserFromDare(dareId));

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
        }catch(Exception e){
            e.printStackTrace();
        }

       return dare;
    }

    private ArrayList<String> getAllUserFromDare(int dareId){

        ResultSet resultFromQuery = ParticipantTable.getParticipantUserIdFromDare(dareId);
        ArrayList<String> list = new ArrayList();

        try {
            while (resultFromQuery.next()){
                String userId = resultFromQuery.getString("UserId");
                String userName = UserTable.getUser(userId);
                System.out.println(userName);
                list.add(userName);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

}
