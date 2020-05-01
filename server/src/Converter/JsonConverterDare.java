package Converter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.xml.xpath.XPathEvaluationResult;
import java.util.*;

/**
 * @author Kamilla
 * @version 1.0
 * This class parses Json objects into Java Objects
 * @date 14/04-20
 */
public class JsonConverterDare {
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
     * It then send it along to the controller.
     * @param newDare String containing information about a dare, read from DareEndpoint
     */
    public void JsonToJava(String newDare) throws ParseException {
        Dare dare = new Dare();
        Object obj = new JSONParser().parse(newDare);
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

        ArrayList<String> participantList = new ArrayList<>();
        while(partIterator.hasNext()){
            Map p = (Map) partIterator.next();
            participantList.add(p.get("uid").toString());
            System.out.println(p.get("uid").toString());
        }

        dare.setParticipants(participantList);

        sendDareToDB(dare);
    }

    public void sendDareToDB(Dare dare){
        controller.addNewDare(dare);
    }

    /**
     * This method gets a dare. It uses a dareId to get it from the controller, and then parses
     * the java object to Json.
     * @param dareId The Id of the dare one seeks to retrieve
     * @return Returns dare formatted to Json
     * @throws java.text.ParseException
     */
    public String getJsonDare(int dareId) {

        Dare dare;
        dare = controller.getDare(dareId);
        System.out.println("Dare "+ dare.toString());
        System.out.println("Verdi fra Dare : "+dare.getStartDate());

        JSONObject jo = new JSONObject();

        jo.put("dareID",dareId);
        System.out.println("JO dareId" + dareId);
        System.out.println("Objective test "+ dare.getObjectiveFromDB());
        Map m = new LinkedHashMap();
        m.putAll(dare.getObjectiveFromDB());
        jo.put("objective", m);

        System.out.println("Objective "+dare.getObjectiveFromDB());

        m = new LinkedHashMap();
        m.putAll(dare.getScopeFromDB());
        jo.put("scope", m);

        System.out.println("Scope "+ dare.getScopeFromDB());

        jo.put("start",dare.getStartDate());
        jo.put("end",dare.getEndDate());

        System.out.println("TEST: "+jo.toJSONString());

        ArrayList<String> participantsList = dare.getParticipants();
        JSONArray ja = new JSONArray();

        //DO NOT TOUCH. IS MAGIC
        for (String s : participantsList) {
            System.out.println(s);
            Map map = new LinkedHashMap();
            map.put("uid", s);
            ja.add(map);
        }

        System.out.println(ja.toJSONString());

        jo.put("participants", ja);

        System.out.println("Stringen: "+jo.toJSONString());

        return jo.toJSONString();
    }

    public void sendDareIDToPost(int id){
        serverApiCommunication.sendDareIDToPost(id);
    }

}
