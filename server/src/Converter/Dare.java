package Converter;

import org.json.simple.JSONArray;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

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
    private Date end;
    private Calendar calendar;
    private int length;
    private SimpleDateFormat sdf;



    public Dare() {
        participants = new JSONArray();
        //length = (int) scope.get("length");
        sdf = new SimpleDateFormat("dd:MM:yy HH:mm:ss");
        calendar = Calendar.getInstance();
    }

   // set date (expects date)
    public void setStart(Date calendar) {
      this.start= sdf.format(calendar);
       // sdf.parse(String.valueOf(start));
       // length = (int) scope.get("length");
        setEnd(3);
    }

    // get date - return date
    public String getStart() throws ParseException {
        return start;
    }

    // set end date - uses get date

    public void setEnd(int length){
       calendar.setTime(end);
       calendar.add(Calendar.DAY_OF_MONTH, length);
    }

    public Date getEnd(){
        return end;
    }

    public JSONArray getParticipants() {
        return participants;
    }

    public void setParticipants(JSONArray newArray) {
        //  participants.add(newArray);
        this.participants = newArray;
        System.out.println("SE HER KAMILLA OG JULIA " + newArray.get(1));
    }

    public Map getObjective() {
        System.out.println(objective.get("goal") + " Dette er goal");// funker
        System.out.println(objective.get("type") + " Dette er type");// funker
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
