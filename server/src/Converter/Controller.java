package Converter;

import database.DBController;

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

    private DBController dbController;
    private JsonConverterDare jsonConverterDare;


    public Controller() {}

    public Controller(JsonConverterDare jsonConverterDare){
        this.jsonConverterDare = jsonConverterDare;
        dbController = new DBController(this);

    }

    /**
     * Use this method to add a new dare to the database
     * Deconstructs a dare and sends along requested values
     * @param dare Java object Dare
     */
    public void addNewDare(Dare dare){

        //Comment
        String objectiveType = dare.getObjective().get("type").toString();
        String objectiveGoal = dare.getObjective().get("goal").toString();

        String scopeType = dare.getScope().get("type").toString();
        int scopeLength = Integer.parseInt(dare.getScope().get("length").toString());

        String start = dare.getStartDate();
        String end = dare.getEndDate();

        //Fixme: Change this to Not be dependant on string length
        String participant =  dare.getParticipants().get(0).toString();
        String participantId = participant.substring(8, 36);
        System.out.println("Participant ID : "+participantId);

        String opponent = dare.getParticipants().get(1).toString();
        String opponentId = opponent.substring(8,36);

        //Comment
        dbController.sendNewDareToDB(objectiveType, objectiveGoal, scopeType, scopeLength, start, end);

        //Comment
        dbController.combineDareIdAndParticipant(participantId,opponentId);
    }

    /**
     * Use this method to add a new user to the database
     * Deconstructs a Java object User and sends along requested values
     * @param user Java object User
     */
    public void addNewUser(User user){
            dbController.sendUserToDB(user.getUid(), user.getName());
    }

    public void sendDareID(int id){
        jsonConverterDare.sendDareIDToPost(id);
    }

    public String getUserFromDB(String uid){
        return dbController.getUserFromDB(uid);
    }

    //todo: fill out this method
    //Participants recieves a string, of an array (score array(true true false)). DB recieves a string from participants.
    public void addScoreToUsersDare(){}


    /**
     * Gets dare from database using dareId.
     * @param dareId
     * @return
     */
    public Dare getDare(int dareId){
       return dbController.getDare(dareId);
    }

}
