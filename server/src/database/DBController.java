package database;

import Converter.Controller;
import Converter.Dare;
import Converter.ServerApiCommunication;

import java.sql.SQLException;

/**
 * Database controller,
 * @author julia
 */

public class DBController {
    private Controller controller;
    private DareTable dareTable;
    private UserTable userTable;
    private Dare dare;

    public DBController() {
    }

    public DBController(Controller controller){
        this.controller=controller;
        dareTable = new DareTable(this);
    }


    public void sendUserToDB(String userID, String name) throws SQLException {

        UserTable.addUserToDB(userID, name);
    }

    public static void getUserIDfromDB(int userID){

    }
    public String getUserFromDB(String uid){
        String userName = "";
        try {
            userName = UserTable.getUser(uid);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return userName;
    }

    public void sendNewDareToDB(String objectiveType, String objectiveGoal,String scopeType,
                                int scopeLength, String start,String end) throws SQLException {

        dareTable.insertNewDareToDB(objectiveType, objectiveGoal, scopeType, scopeLength, start, end);

    }

    public void getDareIDfromDB(int dareId){
        controller.sendDareID(dareId); // this ID will be used in insertParticipantToDB
    }

    public void combineDareIdAndParticipant(String participantId, String opponentId){

    }



    public void insertParticipantsToDB(int userID,int dareID) throws SQLException {
        DareTable.insertParticipantsToDare(userID,dareID);
    }
    public void setDareFromDB(String objectiveType, String objectiveGoal, String scopeType,
                              int scopeLength, String start, String end, String uid1, String uid2){
        dare = new Dare();
        dare.setObjectiveFromDB(objectiveType, objectiveGoal);
        System.out.println("YIHA "+ objectiveType +" "+ objectiveGoal + " " + dare.getObjectiveFromDB());
        dare.setScopeFromDB(scopeType, scopeLength);
        dare.setStartDate(start);
        dare.setEndDate(end);
        dare.addParticipants(uid1);
        dare.addParticipants(uid2);
    }
    /*public Dare getAboveDare(int id){
        return dare;
    }*/
    public Dare getDare(int dareId){
        System.out.println();
       return dareTable.getDareFromDB(dareId);
        //return ;
    }

}
