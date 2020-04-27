package database;

import Converter.JsonConverterUser;
import Converter.User;

import java.sql.SQLException;

/**
 * Controllerclass that communicates with DB and ....?
 * @author julia
 */
public class DBController {

    //GetUserIdfromDB
    //InsertNewDare
    //GetDareIdFromDB (use dareId to insert participants)
    //InsertParticipants to DB
    //getDare
    //FIXME: hela klassen behöver nog lite omvårdnad. endast utkast



    public DBController (){

    }


    public void sendUserToDB(String name) throws SQLException {
        InsertUser iu = new InsertUser();
        iu.addUserToDB(name);
    }

    public void getUserIDfromDB(int userID){
        //method in jsonConverter?
    }

    public void sendNewDareToDB(String objectiveType, String objective,String scopeType,
    String scopeLength, String start,String end) throws SQLException {
        InsertDare dareDB = new InsertDare();
        dareDB.insertNewDareToDB(objectiveType,objective,scopeType,scopeLength,start,end); //This method expects varables for a dare:
        //objectiveType, objective, ScopeType,ScopeLength,Start,End
    }

    public void getDareIDfromDB(int dareID){

    }

    public void insertParticipantsToDB(int userID,int dareID){
    }
}
