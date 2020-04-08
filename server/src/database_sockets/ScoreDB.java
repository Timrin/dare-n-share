package database_sockets;
/**
 * @author Julia and Kamilla - XP pair programming
 * */

public class ScoreDB {

    private static String [] SCOREID = {"lol", "{\n" +
            "  \"uid\": 1,\n" +
            "  \"dare_id\": \"d1\",\n" +
            "  \"score\": {\n" +
            "    \"type\": \"BOOLEAN\",\n" +
            "    \"point\": true\n" +
            "  }\n" +
            "}"};

    public static String getScorebyUserID (int id){

        return SCOREID[id];
    }

}
