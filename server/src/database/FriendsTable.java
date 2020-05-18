package database;

import java.sql.*;
import java.util.ArrayList;

public class FriendsTable {

//Jag tänker att vi kan testa att uppdatera insättningen här direkt istället för att skicka två st förfrågningar
    public static void addFriends(String userId, String friendId) {

        Connection conn = null;
        Statement statement = null;

        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection ok");

            String query = "INSERT INTO Friends (userID, user) VALUES ('"+userId+"','" +friendId+"')";
            statement = conn.createStatement();
            statement.execute(query);
            String updateQuery = "INSERT INTO Friends(userID, user) VALUES ('" +friendId+"','"+userId+"')";
            statement.executeUpdate(updateQuery);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (statement != null) statement.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }

    public static ArrayList<String> getFriends(String userId){

        Connection conn = null;
        Statement statement = null;
        ResultSet resultFromQuery = null;

        ArrayList<String>userIdForFriends = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection ok");

            String query = "SELECT user from Friends where userID='"+userId+"';";
            statement = conn.createStatement();
            statement.execute(query);
            resultFromQuery = statement.getResultSet();

            while (resultFromQuery.next()){
                String friendId = resultFromQuery.getString("user");
                userIdForFriends.add(friendId);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (resultFromQuery != null) resultFromQuery.close(); } catch (Exception e) {}
            try { if (statement != null) statement.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }

        return userIdForFriends;
    }
}