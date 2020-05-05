package database;

import java.sql.*;

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
            System.out.println("Connection ok");

            String foreignKeyQuery = "PRAGMA FOREIGN_KEYS = on";
            String query = " INSERT INTO Participants(UserId, DareId) VALUES ('" + userID + "'," + dareID + ");";
            assert conn != null;
            Statement statement = conn.createStatement();

            statement.execute(foreignKeyQuery);
            statement.executeUpdate(query);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ResultSet getParticipantUserIdFromDare(int dareId) {
        Connection conn = null;
        ResultSet resultFromQuery = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String path = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(path);
            System.out.println("Connection ok");

            String query = "SELECT UserId from Participants where DareId=" + dareId + ";";
            assert conn != null;
            Statement statement = conn.createStatement();
            statement.execute(query);
            resultFromQuery = statement.getResultSet();


        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return resultFromQuery;
    }


    public static ResultSet getAllDareIdForUser(String userId) {
        Connection conn = null;
        ResultSet resultFromQuery = null;

        try {
            Class.forName("org.sqlite.JDBC");
            String path = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(path);
            System.out.println("Connection ok");

            String query = "SELECT DareId from Participants where UserId='" + userId + "';";
            Statement statement = conn.createStatement();
            statement.execute(query);

            resultFromQuery = statement.getResultSet();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return resultFromQuery;
    }

    public static boolean addToScore(int dareId, String userId, String score) {
        Connection conn = null;
        ResultSet resultFromQuery = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String path = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(path);
            System.out.println("Connection ok");

            // String query = "UPDATE Participants set (Score) values ('" + score+"') where DareId=" + dareId + " and UserId='" + userId + "';";
            String query = "update Participants set Score ='" + score + "' Where DareId=" + dareId + " and UserId='" + userId + "';";
            Statement statement = conn.createStatement();
            statement.execute(query);
            resultFromQuery = statement.getResultSet();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static ResultSet getScoreFromDB(int dareId, String userId) {
        Connection conn = null;
        ResultSet resultFromQuery = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String path = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(path);
            System.out.println("Connection ok");

            String query = "SELECT Score from Participants where DareId=" + dareId + " and UserId='" + userId + "';";
            Statement statement = conn.createStatement();
            statement.execute(query);
            resultFromQuery = statement.getResultSet();


        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultFromQuery;
    }
}