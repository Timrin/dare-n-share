package database;

import java.sql.SQLException;

public class DBController {

    public void sendUserToDB(String name) throws SQLException {
        UserTable ut = new UserTable();
        ut.addUserToDB(name);
    }

    public void getUserIDfromDB(int userID){
        //method in jsonConverter?
    }

    public void sendNewDareToDB(String objectiveType, String objective,String scopeType,
                                int scopeLength, String start,String end) throws SQLException {
        DareTable dareDB = new DareTable();
        dareDB.insertNewDareToDB(objectiveType,objective,scopeType,scopeLength,start,end); //This method expects varables for a dare:
        //objectiveType, objective, ScopeType,ScopeLength,Start,End
    }

    public void getDareIDfromDB(int dareID){

    }

    public void insertParticipantsToDB(int userID,int dareID){
    }
}
