package database_sockets;


import enitity.Objective;
import enitity.Scope;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Kamilla
 * This class reads a json file and converts the data in to java objects
 * */

public class JsonConverter{

    private DareDB dareDB;
    private Object object;
    private Objective objective;
    private Scope scope;



    public JsonConverter() {}

    public void convertDare() throws IOException, ParseException {
        dareDB = new DareDB();
        String dare = dareDB.deliverDare();
        //object = new JSONParser().parse(new FileReader(""));
        object = new JSONParser().parse(new FileReader("resources/JSONDare.json"));

        JSONObject jo =(JSONObject) object;

        // getting dare ID
        int dareId  = (int) jo.get("id");

        System.out.println("dare ID = "+dareId);

        // getting objective
        // Make class?
        Objective typeObjective = (Objective) jo.get("type");
        Objective goal = (Objective) jo.get("goal");

        System.out.println("objective type = "+typeObjective);
        System.out.println("objective goal = "+goal);

        // scope
        Scope typeScope = (Scope) jo.get("type");
        Scope length = (Scope) jo.get("length");

        System.out.println("scope type = "+typeScope);
        System.out.println("scope length = "+length);

        // getting start and end time todo how to deal with time. Parse to simple time format?
        String start = (String) jo.get("start");
        String end = (String) jo.get("end");

        HashMap participants = (HashMap)jo.get("participants");










    }



}
