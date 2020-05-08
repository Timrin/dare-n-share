package database;

import java.sql.*;
import java.util.ArrayList;

public class FriendsTable {


    public static void addFriends(String userId, String friendId) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection ok");

            String query = "INSERT INTO Friends (userID, user) VALUES ('"+userId+"','" +friendId+"')";
            Statement statement = conn.createStatement();
            statement.execute(query);
            conn.close();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
//FIXME: returnera något annat än resultset,
    public static ArrayList<String> getFriends(String userId){
        Connection conn = null;
        ResultSet resultFromQuery = null;
        ArrayList<String>userIdForFriends = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection ok");

            String query = "SELECT user from Friends where userID='"+userId+"';";
            Statement statement = conn.createStatement();
            statement.execute(query);
            resultFromQuery = statement.getResultSet();
            while (resultFromQuery.next()){
                String friendId = resultFromQuery.getString("user");
                userIdForFriends.add(friendId);


            }
            resultFromQuery.close();
            statement.close();
            conn.close();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return userIdForFriends;
    }
}