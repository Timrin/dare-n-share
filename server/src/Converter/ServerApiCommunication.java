package Converter;
import api_endpoints.DareEndpoint;
import api_endpoints.UserEndpoint;
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
    private DareEndpoint dareEndpoint;

    public ServerApiCommunication() {
        jsonDare= new JsonConverterDare();
        jsonScore = new JsonConverterScore();
        jsonUser = new JsonConverterUser();
    }

    public ServerApiCommunication(DareEndpoint dareEndpoint){
        this.dareEndpoint = dareEndpoint;
        jsonDare= new JsonConverterDare(this);
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
    public void newScore(String score) throws ParseException {
        jsonScore.JsonToJava(score);
    }
    /**
     * This method sends a user from Server to be parsed from Json to Java
     * */
    public void newUser(String user) throws ParseException {
        jsonUser.JsonToJava(user);

    }
    /**
     * This method returns a string that is parsed to a json body, and is called upon in
     * the dare endpoint.
     * */
    public String getDare(int dareId) throws java.text.ParseException {
        return jsonDare.getJsonDare(dareId);
    }
    /**
     * This method returns a string that is parsed to a json body, and is called upon in
     * the user endpoint
     * */
    public String getUser(String iud){
        return jsonUser.getUserFromDB(iud);
    }

    public void sendDareIDToPost(int id){
        dareEndpoint.addDareIDToPost(id);
    }
}
