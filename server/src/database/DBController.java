package database;

import java.sql.SQLException;

public class DBController {

    public void sendUserToDB(String name) throws SQLException {

        UserTable.addUserToDB(name);
    }

    public void getUserIDfromDB(int userID){
        //method in jsonConverter?
    }

    public void sendNewDareToDB(String objectiveType, String objective,String scopeType,
                                int scopeLength, String start,String end) throws SQLException {
       // DareTable dareDB = new DareTable();
        try {
            DareTable.insertNewDareToDB(objectiveType, objective, scopeType, scopeLength, start, end); //This method expects varables for a dare:
            //objectiveType, objective, ScopeType,ScopeLength,Start,End
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void getDareIDfromDB(int dareID){

    }

    public void insertParticipantsToDB(int userID,int dareID){
    }
}
