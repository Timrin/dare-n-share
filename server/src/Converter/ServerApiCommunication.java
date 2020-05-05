package Converter;

/**
 * @author Kamilla
 * @date 15/04-20
 * @version 1.0 semantic versioning
 * This class acts as a converting gateway between endpoints and controller. Using three
 * help classes, it converts incoming Strings to Java objects, and outgoing Java
 * objects to Strings in JSON format.
 * */
public class ServerApiCommunication {
    private JsonConverterDare jsonDare;
    private JsonConverterScore jsonScore;
    private JsonConverterUser jsonUser;
    private Controller controller;

    public ServerApiCommunication() {
        jsonDare= new JsonConverterDare();
        jsonScore = new JsonConverterScore();
        jsonUser = new JsonConverterUser();
        controller = new Controller();
    }

    /**
     * This method sends a dare from Server to be parsed from Json to Java
     * */
    public int newDare(String dareString) {

        Dare dare = jsonDare.JsonToJava(dareString);
        int dareId = controller.addNewDare(dare);

        return dareId;
    }
    /**
     * This method sends the score from Server to be parsed from Json to Java
     * */
    public void newScore(String score) {
        jsonScore.JsonToJava(score);
    }

    /**
     * This method parses an incoming new user from a String in JSON format into
     * a User Java object. It then sends it to the database to be stored
     * @param user JSON String of new user
     * */
    public void newUser(String user) {
        controller.addNewUser(jsonUser.JsonToJava(user));
    }

    /**
     * This method gets a Java Dare from the controller based on the dare ID
     * It uses JsonConverterDare to parse that dare into a JSON formatted string
     * @param dareId ID of the requested dare
     * @return returns that dare as a string in JSON format
     */
    public String getDare(int dareId){
        return jsonDare.JavaToJson(controller.getDare(dareId));
    }

    /**
     * This method returns a string that is parsed to a json body, and is called upon in
     * the user endpoint
     * */
    public String getUser(String uid){
        //String user = controller.getUserFromDB(uid);
        User user = controller.getUserFromDB(uid);
        System.out.println(user.getName()+" "+user.getDares() + " SEREVER API COM");
        System.out.println(jsonUser.JavaToJson(user));
        System.out.println(user.getDares());


        return jsonUser.JavaToJson(user);
    }
}
