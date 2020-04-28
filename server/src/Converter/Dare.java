package Converter;

import org.json.simple.JSONArray;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author Kamilla, Julia, Steven
 * @version 1.0
 * This is an entity class
 * @date 14/04-20
 */

public class Dare {

    private Map objective;
    private Map scope;
    private JSONArray participants;
    private String start;
    private String end;
    private Calendar calendar;
    private int length;
    private SimpleDateFormat sdf;

    private Date date;


    public Dare() throws ParseException {
        participants = new JSONArray();

        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        calendar = Calendar.getInstance();

    }

    public String setStart() {
        Date start = new Date();
        return sdf.format(start);
    }

    public String setEnd(int scopeLength) {
        Date now = new Date();
        Date end = new Date();

        end.setTime(now.getTime() + getDaysAsMilliSeconds(scopeLength));
        return sdf.format(end);
    }


    public long getDaysAsMilliSeconds(int days) {
        return (days * 24 * 60 * 60 * 1000);
    }




    public JSONArray getParticipants() {
        return participants;
    }

    public void setParticipants(JSONArray newArray) {
        //  participants.add(newArray);
        this.participants = newArray;
        //System.out.println("SE HER KAMILLA OG JULIA " + newArray.get(1));
    }

    public Map getObjective() {
        //System.out.println(objective.get("goal") + " Dette er goal");// funker
        //System.out.println(objective.get("type") + " Dette er type");// funker
        return objective;
    }

    public void setObjective(Map objective) {
        this.objective = objective;
    }

    public Map getScope() {
        return scope;
    }

    public void setScope(Map scope) {
        this.scope = scope;
    }


}
