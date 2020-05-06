package database;

import java.sql.*;

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


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet getFriends(String userId){
        Connection conn = null;
        ResultSet resultFromQuery = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection ok");

            String query = "SELECT user from Friends where userID='"+userId+"';";
            Statement statement = conn.createStatement();
            statement.execute(query);
            resultFromQuery = statement.getResultSet();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return resultFromQuery;
    }
}