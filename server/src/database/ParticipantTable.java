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
     * add Participants to table
     *
     * @throws SQLException for SQL error that can happen during execute and create statement
     */
    public static void addParticipant(String userID, int dareID) {

        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(url);


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

    /**
     * retrieves participants of dare
     * @param dareId
     * @return Arraylist with userId
     */
    public static ArrayList<String> getParticipantUserIdFromDare(int dareId) {
        Connection conn = null;
        ResultSet resultFromQuery = null;
        ArrayList<String> users = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            String path = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(path);


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

    /**
     * get all dares for a user and returns values in array
     * @param userId
     * @return array of all dare for one user
     */
    public static ArrayList<Integer> getAllDareIdForUser(String userId) {
        Connection conn = null;
        ResultSet resultFromQuery = null;

        ArrayList<Integer> dareId = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            String path = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(path);


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

    /**
     * send score to table participant
     * @param dareId
     * @param userId
     * @param score
     */
    public static void addToScore(int dareId, String userId, String score) {
        Connection conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
            String path = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(path);


            String query = "update Participants set Score ='" + score + "' Where DareId=" + dareId + " and UserId='" + userId + "';";
            Statement statement = conn.createStatement();
            statement.execute(query);

            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * utveckla denna metod. se hur den går att använda för fler delar av db
     * @param query
     * @return
     */
    public static synchronized ResultSet dataBaseTalker(String query) {
        Connection conn = null;
        ResultSet resultFromQuery = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String path = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(path);


            // String query = "UPDATE Participants set (Score) values ('" + score+"') where DareId=" + dareId + " and UserId='" + userId + "';";
            Statement statement = conn.createStatement();
            statement.execute(query);
            resultFromQuery = statement.getResultSet();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return resultFromQuery;
    }

    /**
     * return string of score
     * @param dareId
     * @param userId
     * @return
     */
    public static String getScoreFromDB(int dareId, String userId) {
        Connection conn = null;
        ResultSet resultFromQuery = null;
        String score = "";
        try {
            Class.forName("org.sqlite.JDBC");
            String path = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(path);
            String query = "SELECT Score from Participants where DareId=" + dareId + " and UserId='" + userId + "';";

            Statement statement = conn.createStatement();
            statement.execute(query);
            resultFromQuery = statement.getResultSet();
            score = resultFromQuery.getString("Score");
           //vad gör detta? behövs det?
            if (score == null) {
                score = "";
            }

            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return score;
    }
}