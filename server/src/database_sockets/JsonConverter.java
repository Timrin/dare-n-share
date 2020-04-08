package database_sockets;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonConverter{

    private DareDB dareDB;
    private Object object;


    public JsonConverter() throws FileNotFoundException, ParseException {
        dareDB = new DareDB();
        String dare = dareDB.deliverDare();
        //object = new JSONParser().parse();

        JSONObject jo =(JSONObject) object;
        /*
        * Hvordan skal vi bruke json?
        * Skal vi plukke et Json objekt fra hverandre?
        *
        *
        *
        * */
    }



}
