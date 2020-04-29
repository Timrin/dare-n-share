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
    private Dare dare;
    private ServerApiCommunication serverApiCommunication;
    private Controller controller;

    public JsonConverterDare() {
    }

    public JsonConverterDare(ServerApiCommunication serverApiCommunication){
        this.serverApiCommunication = serverApiCommunication;
        controller = new Controller(this);
    }

    /**
     * This method parses a String in JSON body, recieved from server, to a Dare Java object.
     */
    public void JsonToJava(String newdare) throws ParseException, java.text.ParseException {
        this.dare = new Dare();

        Object obj = new JSONParser().parse(newdare);
        org.json.simple.JSONObject jo = (JSONObject) obj;

        this.dare.setObjective((Map) jo.get("objective"));

        Iterator<Map.Entry> iteratorObjective = this.dare.getObjective().entrySet().iterator();
        while (iteratorObjective.hasNext()) {
            Map.Entry pair = iteratorObjective.next();
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }

        this.dare.setScope((Map) jo.get("scope"));
        Iterator<Map.Entry> iteratorScope = this.dare.getScope().entrySet().iterator();
        while (iteratorScope.hasNext()) {
            Map.Entry pair = iteratorScope.next();
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }

        try {
            int length = Integer.parseInt( dare.getScope().get("length").toString());
            System.out.println("TEST LENGTH " + length);
            jo.put("start",dare.setStart());
            jo.put("end", dare.setEnd(length));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        JSONArray ja = (JSONArray) jo.get("participants");
        dare.setParticipants(ja);

        sendDareToDB(dare);
       // dare.setStart(calendar.getTime());

        //dare.setEnd(3);
    }

    public void timeFrame(){

    }

    public void sendDareToDB(Dare dare){
        new Controller().addNewDare(dare);
    }


    /**
     * This method returns a JSONString after converting Java objects into s JSON string.
     * This method is supposed to be called upon in the dareEndpoint through the controller
     */
    public String JavaToJson() throws java.text.ParseException {

        JSONObject jo = new JSONObject();

        Map m = new LinkedHashMap();
        m.putAll(dare.getObjective());
        jo.put("objective", m);

        m = new LinkedHashMap();
        m.putAll(dare.getScope());
        jo.put("scope", m);

        System.out.println(dare.getScope().get("length"));

//        try {
//            int length = Integer.parseInt( dare.getScope().get("length").toString());
//            System.out.println("TEST LENGTH " + length);
//            jo.put("start",dare.setStart());
//            jo.put("end", dare.setEnd(length));
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }


        System.out.println("TEST: "+jo.toJSONString());

        JSONArray ja = dare.getParticipants();
        jo.put("participants", ja);
        // System.out.println(ja.toJSONString());



        return jo.toJSONString();
    }

    public void sendDareIDToPost(int id){
        serverApiCommunication.sendDareIDToPost(id);
    }

}
