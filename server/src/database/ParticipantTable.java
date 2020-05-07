package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Participant table communication
 *
 * @author julia
 */
public class ParticipantTable {




    /**
     * add Participants to table, retrieves the primary key and store in variable
     *
     * @throws SQLException for SQL error that can happen during execute and create statement
     */
    public static void addParticipant(String userID, int dareID) {

        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection ok add participant");

            String foreignKeyQuery = "PRAGMA FOREIGN_KEYS = on";
            String query = " INSERT INTO Participants(UserId, DareId) VALUES ('" + userID + "'," + dareID + ");";
            assert conn != null;
            Statement statement = conn.createStatement();

            statement.execute(foreignKeyQuery);
            statement.executeUpdate(query);
            conn.close();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<String> getParticipantUserIdFromDare(int dareId) {
        Connection conn = null;
        ResultSet resultFromQuery = null;
        ArrayList<String> users = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            String path = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(path);
            System.out.println("Connection ok get participants");

            String query = "SELECT UserId from Participants where DareId=" + dareId + ";";
            assert conn != null;
            Statement statement = conn.createStatement();
            statement.execute(query);
            resultFromQuery = statement.getResultSet();
            while (resultFromQuery.next()) {
                String user = resultFromQuery.getString("UserId");
                users.add(user);
            }


            conn.close();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());

        }


        return users;
    }


    public static ArrayList<Integer> getAllDareIdForUser(String userId) {
        Connection conn = null;
        ResultSet resultFromQuery = null;
       //int[] dareId = new int[10];
        ArrayList<Integer> dareId = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            String path = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(path);
            System.out.println("Connection ok get alldareId");

            String query = "SELECT DareId from Participants where UserId='" + userId + "';";
            Statement statement = conn.createStatement();
            statement.execute(query);

            resultFromQuery = statement.getResultSet();
            while (resultFromQuery.next()) {
                int dare = resultFromQuery.getInt("DareId");
                dareId.add(dare);
            }
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


        return dareId;
    }

    public static void addToScore(int dareId, String userId, String score) {
        Connection conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
            String path = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(path);
            System.out.println("Connection ok");


            String query = "update Participants set Score ='" + score + "' Where DareId=" + dareId + " and UserId='" + userId + "';";
            Statement statement = conn.createStatement();
            statement.execute(query);

            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static synchronized ResultSet dataBaseTalker(String query) {
        Connection conn = null;
        ResultSet resultFromQuery = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String path = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(path);
            System.out.println("Connection ok");

            // String query = "UPDATE Participants set (Score) values ('" + score+"') where DareId=" + dareId + " and UserId='" + userId + "';";
            Statement statement = conn.createStatement();
            statement.execute(query);
            resultFromQuery = statement.getResultSet();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultFromQuery;
    }

    public static String getScoreFromDB(int dareId, String userId) {
        Connection conn = null;
        ResultSet resultFromQuery = null;
        String score = "";
        try {
            Class.forName("org.sqlite.JDBC");
            String path = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(path);
            System.out.println("Connection ok get scorefromDB");

            String query = "SELECT Score from Participants where DareId=" + dareId + " and UserId='" + userId + "';";
            // resultFromQuery = dataBaseTalker(query);

            Statement statement = conn.createStatement();
            statement.execute(query);
            resultFromQuery = statement.getResultSet();
            score = resultFromQuery.getString("Score");
            if (score == null) {
                score = "";
            }
            System.out.println(score);
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return score;
    }
}