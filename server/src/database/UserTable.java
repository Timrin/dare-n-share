package database;

import Converter.User;

import java.sql.*;

/**
 * communication class with User table in database
 *
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
     *
     * @param name username from GUI
     * @throws SQLException
     */
    public static void addUserToDB(String userId, String name) {

        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String path = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(path);
            System.out.println("Connection ok");


            String query = "INSERT INTO user(UserID,username) VALUES ('" + userId + "','" + name + "')";

            assert conn != null;
            Statement statement = conn.createStatement();
            statement.execute(query);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        //get the generated primary key and store in userID
//        ResultSet userIdKey = statement.getGeneratedKeys();
//        while (userIdKey.next()) {
//            String userID = userIdKey.getString(1);
//            System.out.println(userID);
//            String uname = userIdKey.getString(2);
//            System.out.println(uname);
//        }
    }


    //Get information from all columns for userId
    public static String getUser(String userId) throws SQLException {
        String name = null;
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String path = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(path);
            System.out.println("Connection ok");


            String query = "SELECT * FROM User where UserID = '" + userId + "';";


            assert conn != null;
            Statement statement = conn.createStatement();
            statement.execute(query);

            ResultSet resultFromQuery = statement.getResultSet();

            while (resultFromQuery.next()) {
                name = resultFromQuery.getString(2);

            }


        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return name;
    }


}
