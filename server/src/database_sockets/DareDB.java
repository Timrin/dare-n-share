package database_sockets;

/**
 * @author Julia and Kamilla - XP pair programming
 * */

import java.sql.SQLOutput;

public class DareDB {

    private static String[] DAREID = {"{\n" +
            "    \"id\": 5150922,\n" +
            "    \"objective\": {\n" +
            "        \"type\": \"BOOLEAN\",\n" +
            "        \"goal\": \"NO_MEAT\"\n" +
            "    },\n" +
            "    \"scope\": {\n" +
            "        \"type\": \"TIMED\",\n" +
            "        \"length\": 3\n" +
            "    },\n" +
            "    \"start\": \"2012-01-23T18:25:43.511Z\",\n" +
            "    \"end\": \"2012-04-23T18:25:43.511Z\",\n" +
            "    \"participants\": [\n" +
            "        {\n" +
            "            \"uid\": 1,\n" +
            "            \"name\": \"Timothy\",\n" +
            "            \"profile_img\": \"/profile-picture/1\",\n" +
            "            \"score\": [\n" +
            "                true,\n" +
            "                false\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"uid\": 2,\n" +
            "            \"name\": \"Steven\",\n" +
            "            \"profile_img\": \"/profile-picture/2\",\n" +
            "            \"score\": [\n" +
            "                false,\n" +
            "                false\n" +
            "            ]\n" +
            "        }\n" +
            "    ]\n" +
            "}"};



    public static String getDareByID(int id){
        return DAREID[id];
    }

    public static void main(String[] args) {
        getDareByID(0);
    }

}
