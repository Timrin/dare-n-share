package Converter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.*;

/**
 * @author Kamilla
 * @version 1.0
 * This class parses Json objects into Java Objects
 * @date 14/04-20
 */
public class JsonConverterDare {

    /**
     * This method parses a String in JSON body, recieved from server, to a Dare Java object.
     * It then send it along to the controller.
     * @param newDare String containing information about a dare, read from DareEndpoint
     */
    public Dare JsonToJava(String newDare) {

        Dare dare = new Dare();

        //Parses string into JSONObject for easier handling of String
        Object obj = new JSONObject();
        try {
            obj = new JSONParser().parse(newDare);
        }catch (ParseException p){
            p.printStackTrace();
        }
        org.json.simple.JSONObject jo = (JSONObject) obj;

        //Sets the objective of the dare, which contains type and goal
        dare.setObjective((Map) jo.get("objective"));
        Iterator<Map.Entry> iteratorObjective = dare.getObjective().entrySet().iterator();
        while (iteratorObjective.hasNext()) {
            Map.Entry pair = iteratorObjective.next();
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }

        //Sets the scope of the dare, which contains type and length
        dare.setScope((Map) jo.get("scope"));
        Iterator<Map.Entry> iteratorScope = dare.getScope().entrySet().iterator();
        while (iteratorScope.hasNext()) {
            Map.Entry pair = iteratorScope.next();
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }

        //Parses length to an integer, and sets the startDate of the dare (to now)
        //and sets endDate to length days later
        try {
            int length = Integer.parseInt( dare.getScope().get("length").toString());
            System.out.println("TEST LENGTH " + length);
            jo.put("start", dare.setStart());
            jo.put("end", dare.setEnd(length));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        //Sets the participants of the dare
        JSONArray ja = (JSONArray) jo.get("participants");

        Iterator partIterator = ja.iterator();

        //Creates an ArrayList containing all participants' user IDs
        ArrayList<String> participantList = new ArrayList<>();
        while(partIterator.hasNext()){
            Map p = (Map) partIterator.next();
            participantList.add(p.get("uid").toString());
            System.out.println(p.get("uid").toString());
        }

        dare.setParticipants(participantList);

        return dare;
    }

    /**
     * This method gets a dare. It uses a dareId to get it from the controller, and then parses
     * the java object to Json.
     * @param dare The Id of the dare one seeks to retrieve
     * @return Returns dare formatted to Json
     */
    public String JavaToJson(Dare dare) {

        System.out.println("JsonConverterDare:JavaToJson, dare received from database: " + dare.toString());

        JSONObject jo = new JSONObject();

        //Sets dare ID
        jo.put("dareID",dare);
        System.out.println("JO dareId" + dare);

        //Sets the objective of dare
        Map m = new LinkedHashMap();
        m.putAll(dare.getObjectiveFromDB());
        jo.put("objective", m);
        System.out.println("JsonConverterDare:JavaToJson, Objective: "+dare.getObjectiveFromDB());

        //Sets the scope of dare
        m = new LinkedHashMap();
        m.putAll(dare.getScopeFromDB());
        jo.put("scope", m);
        System.out.println("JsonConverterDare:JavaToJson, Scope: "+ dare.getScopeFromDB());

        //Sets start en end date of dare
        jo.put("start",dare.getStartDate());
        jo.put("end",dare.getEndDate());

        //Creates a JSONArray of participants from the ArrayList
        ArrayList<String> participantsList = dare.getParticipants();
        JSONArray ja = new JSONArray();
        System.out.println("JsonConverterDare:JavaToJson, Participants:");
        for (String s : participantsList) {
            System.out.println(s);
            Map map = new LinkedHashMap();
            map.put("uid", s);
            ja.add(map);
        }
        jo.put("participants", ja);

        System.out.println("JsonConverterDare:JavaToJson, full string: "+jo.toJSONString());

        return jo.toJSONString();
    }

}
