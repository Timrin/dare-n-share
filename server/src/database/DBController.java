package database;

import Converter.ServerApiCommunication;

import java.sql.SQLException;

/**
 * Database controller,
 * @author julia
 */

public class DBController {


    public void sendUserToDB(String name) throws SQLException {

        UserTable.addUserToDB(name);
    }

    public static void getUserIDfromDB(int userID){

    }
    public static void getUserFromDB(String name){

    }

    public void sendNewDareToDB(String objectiveType, String objective,String scopeType,
                                int scopeLength, String start,String end) throws SQLException {

        try {
            DareTable.insertNewDareToDB(objectiveType, objective, scopeType, scopeLength, start, end);
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public static void getDareIDfromDB(int dareId){
    }

    public void insertParticipantsToDB(int userID,int dareID) throws SQLException {
        DareTable.insertParticipantsToDare(userID,dareID);
    }
    public static void getDareFromDB(){

    }

}
