package Converter;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.HashMap;
/**
 * @author Kamilla
 * @date 15/04-20
 * @version 1.0 semantic versioning
 * This class is a controller.
 * Data recieved from the tomcat Server goes through this class to their respected places.
 * */
public class ServerApiCommunication {
    private JsonConverterDare jsonDare;
    private JsonConverterScore jsonScore;
    private JsonConverterUser jsonUser;

    // These variables are for testing purposes. We need to generate an ID for user and dare
    private HashMap<Integer,String> json2javaUser = new HashMap<>();
    private HashMap<Integer,String> java2jsonUser = new HashMap<>();
    private int nextID =0;

    public ServerApiCommunication() {
        jsonDare= new JsonConverterDare();
        jsonScore = new JsonConverterScore();
        jsonUser = new JsonConverterUser();
    }
    /**
     * This method sends a dare from Server to be parsed from Json to Java
     * */
    public void newDare(String dare) throws org.json.simple.parser.ParseException {
        jsonDare.JsonToJava(dare);
    }
    /**
     * This method sends the score from Server to be parsed from Json to Java
     * */
    public void newScore(String score) throws IOException, ParseException {
        jsonScore.JsonToJava(score);
    }
    /**
     * This method sends a user from Server to be parsed from Json to Java
     * */
    public void newUser(String user) throws ParseException {
       // json2javaUser.put(nextID,jsonUser.JsonToJava(user));
        jsonUser.JsonToJava(user);

    }
    /**
     * This method returns a string that is parsed to a json body, and is called upon in
     * the dare endpoint.
     * */
    public String getDare(){
        return jsonDare.JavaToJson();
    }
    /**
     * This method returns a string that is parsed to a json body, and is called upon in
     * the user endpoint
     * */
    public String getUser(){
        return jsonUser.JavaToJson();
        //return json2javaUser.get(id);
        //return jsonUser.JsonToJava()
    }
}
