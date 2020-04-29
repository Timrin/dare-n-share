package database;

import Converter.User;

import java.sql.*;

/**
 * communication class with User table in database
 * @author julia
 */
public class UserTable {

    /*
    private Connection connect(){
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url ="jdbc:sqlite:lib/dare_n_share.db";
            conn= DriverManager.getConnection(url);
            System.out.println("Connection ok");
        }catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }

     */


    /**
     * insert userinformation to table User
     * @param name username from GUI
     *
     * @throws SQLException
     */
    public static void addUserToDB(String name) throws SQLException {

        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url ="jdbc:sqlite:lib/dare_n_share.db";
            conn= DriverManager.getConnection(url);
            System.out.println("Connection ok");
        }catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        String request = "INSERT INTO user(username) VALUES ('" + name+ "')";

        assert conn != null;
        Statement statement = conn.createStatement();
        statement.execute(request);

        //get the generated primary key and store in userID
        ResultSet userIdKey = statement.getGeneratedKeys();
        while (userIdKey.next()) {
            int userID = userIdKey.getInt(1);
            System.out.println(userID);
            DBController.getUserIDfromDB(userID);
            String uname = userIdKey.getString(2);
            System.out.println(uname);
        }
    }


    //Get information from all columns for userId
    public static void getUser(int userId) throws SQLException {

        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url ="jdbc:sqlite:lib/dare_n_share.db";
            conn= DriverManager.getConnection(url);
            System.out.println("Connection ok");
        }catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        String request = "SELECT * FROM User where UserID = " + userId+";";

        assert conn != null;
        Statement statement = conn.createStatement();
        statement.execute(request);

        ResultSet result = statement.getResultSet();
        while (result.next()){
            String name = result.getString(2);
            DBController.getUserFromDB(name);

        }
    }


}
