package Converter;

import org.json.simple.JSONArray;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Kamilla, Julia, Steven
 * @version 1.0
 * This is an entity class
 * @date 14/04-20
 */

public class Dare {

    private Map objective;
    private Map scope;

    private Map objectiveFromDB;
    private Map scopeFromDB;

    //private JSONArray participants; //userId and dareId
    private ArrayList<String> participants;

    private String startDate;
    private String endDate;

    private SimpleDateFormat sdf;


    public Dare(){
        participants = new JSONArray();
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    }

    public String setStart() {
        Date start = new Date();
        startDate = sdf.format(start);
        return sdf.format(start);
    }

    public String setEnd(int scopeLength) {
        Date now = new Date();
        Date end = new Date();

        end.setTime(now.getTime() + getDaysAsMilliSeconds(scopeLength));
        endDate = sdf.format(end);
        return sdf.format(end);
    }

    public long getDaysAsMilliSeconds(int days) {
        return (days * 24 * 60 * 60 * 1000);
    }

    public ArrayList<String> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<String> newArray) {
        this.participants = newArray; // an index looks like this : {"uid":1}
    }

    public void addParticipants(String participant){
        participants.add(participant);
    }

    public Map getObjective() {
        return objective;
    }

    public void setObjective(Map objective) {
        this.objective = objective;
    }

    public void setObjectiveFromDB(String objectiveType ,String objectiveGoal){
        objectiveFromDB = new HashMap();
        objectiveFromDB.put("type", objectiveType);
        objectiveFromDB.put("goal", objectiveGoal);
    }

    public Map getObjectiveFromDB() {
        return objectiveFromDB;
    }

    public void setScopeFromDB(String scopeType, int scopeLength){
        scopeFromDB = new HashMap();
        scopeFromDB.put("type", scopeType);
        scopeFromDB.put("length", scopeLength);
    }

    public Map getScopeFromDB() {
        return scopeFromDB;
    }

    public Map getScope() {
        return scope;
    }

    public void setScope(Map scope) {
        this.scope = scope;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
