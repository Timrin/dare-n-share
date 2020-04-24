package database;

import Converter.JsonConverterUser;
import Converter.User;

import java.sql.SQLException;

public class DBController {




    public DBController (){


    }


    public void sendUSerToDb(String name) throws SQLException {

        //name= json.getUser().getName();
        InsertUser iu = new InsertUser();
        iu.addUserToDB(name);

    }
}
