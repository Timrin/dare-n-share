package Converter;

/**
 * @author Kamilla
 * @version 1.0 semantic versioning
 * This class acts as a converting gateway between endpoints and controller. Using three
 * help classes, it converts incoming Strings to Java objects, and outgoing Java
 * objects to Strings in JSON format.
 * @date 15/04-20
 */
public class ServerApiCommunication {
    private static ServerApiCommunication instance = null;
    private JsonConverterDare jsonDare;
    private JsonConverterScore jsonScore;
    private JsonConverterUser jsonUser;
    private Controller controller;
    private JsonConverterFriend jsonFriend;

    private ServerApiCommunication() {
        jsonDare = new JsonConverterDare();
        jsonScore = new JsonConverterScore();
        jsonUser = new JsonConverterUser();
        jsonFriend = new JsonConverterFriend();
        controller = new Controller();
    }

    public static ServerApiCommunication getInstance (){
        if (instance == null)
            instance = new ServerApiCommunication();

        return instance;
    }

    /**
     * This method sends a dare from Server to be parsed from Json to Java
     */
    public int newDare(String dareString) {

        Dare dare = jsonDare.JsonToJava(dareString);
        if(dare.getParticipants().size() >= 2) {
            int dareId = controller.addNewDare(dare);
            return dareId;
        }
        return -1;

    }

    /**
     * This method sends the score from Server to be parsed from Json to Java
     */
    public boolean newScore(String score) {
        boolean addScoreIsOk = controller.addScore(jsonScore.JsonToJava(score));

        return addScoreIsOk;
//        Score scoreObj = jsonScore.JsonToJava(score);
//        int size = scoreObj.getScore().size();
//        Dare dare = controller.getDare(Integer.parseInt(scoreObj.getDid()));
//        int length = Integer.parseInt(dare.getScope().get("length").toString());
//        if (length < size) {
//            System.out.println("computer says no");
//        } else {
//            controller.addScore(jsonScore.JsonToJava(score));
//        }
    }

    /**
     * This method parses an incoming new user from a String in JSON format into
     * a User Java object. It then sends it to the database to be stored
     *
     * @param user JSON String of new user
     */
    public void newUser(String user) {
        controller.addNewUser(jsonUser.JsonToJava(user));
    }

    /**
     * This method gets a Java Dare from the controller based on the dare ID
     * It uses JsonConverterDare to parse that dare into a JSON formatted string
     *
     * @param dareId ID of the requested dare
     * @return returns that dare as a string in JSON format
     */
    public String getDare(int dareId) {
        return jsonDare.JavaToJson(controller.getDare(dareId));
    }

    /**
     * This method returns a string that is parsed to a json body, and is called upon in
     * the user endpoint
     */
    public String getUser(String uid) {
        //String user = controller.getUserFromDB(uid);


        User user = controller.getUserFromDB(uid);
        if (user.getName() != null) {
            System.out.println(user.getName() + " " + user.getDares() + " SEREVER API COM");
            System.out.println(jsonUser.JavaToJson(user));
            System.out.println(user.getDares());
            return jsonUser.JavaToJson(user);
        } else {
            return null;
        }

    }

    public void newFriend(String friend){



        controller.addFriendToDBController(jsonFriend.JsonToJava(friend));
    }


}
