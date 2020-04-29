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

    public DBController() {
    }

    public DBController(Controller controller){
        this.controller=controller;
        dareTable = new DareTable(this);
    }


    public void sendUserToDB(String userID,String name) throws SQLException {

        UserTable.addUserToDB(userID,name);
    }

    public static void getUserIDfromDB(int userID){

    }
    public static void getUserFromDB(String name){

    }

    public void sendNewDareToDB(String objectiveType, String objectiveGoal,String scopeType,
                                int scopeLength, String start,String end) throws SQLException {

        try {
            dareTable.insertNewDareToDB(objectiveType, objectiveGoal, scopeType, scopeLength, start, end);
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void getDareIDfromDB(int dareId){
        controller.sendDareID(dareId);
    }

    public void insertParticipantsToDB(int userID,int dareID) throws SQLException {
        DareTable.insertParticipantsToDare(userID,dareID);
    }
    public void setDareFromDB(String objectiveType, String objectiveGoal, String scopeType,
                              int scopeLength, String start, String end, int uid1, int uid2){
        Dare dare = new Dare();
        dare.setObjectiveFromDB(objectiveType, objectiveGoal);


    }

}
