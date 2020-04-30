package database;

import java.sql.*;

/**
 * Participant table communication
 *
 * @author julia
 */
public class ParticipantTable {


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
     * add Participants to table, retrieves the primary key and store in variable
     *
     * @throws SQLException for SQL error that can happen during execute and createstatement
     */
    public static void addParticipant(String userID, int dareID) throws SQLException {

        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection ok");

            String foreignKeyQuery = "PRAGMA FOREIGN_KEYS = on";
            String query = " INSERT INTO participants(UserId, DareId) VALUES ('" + userID + "'," + dareID + ");";
            assert conn != null;
            Statement statement = conn.createStatement();

            statement.execute(foreignKeyQuery);
            statement.executeUpdate(query);

            //get the generated key for PartID and saves in variable participantID
            ResultSet generatedKeys = statement.getGeneratedKeys();
            while (generatedKeys.next()) {
                int participantID = generatedKeys.getInt(1);
                System.out.println(participantID);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getParticipant(int participantId) {
        Connection conn = null;
        String userid = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String path = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(path);
            System.out.println("Connection ok");

            String query = "SELECT UserId from Participants where PartID=" + participantId + ";";
            assert conn != null;
            Statement statement = conn.createStatement();
            statement.execute(query);

            ResultSet resultFromQuery = statement.getResultSet();
            userid = resultFromQuery.getString("UserId");
            System.out.println(userid);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return userid;
    }
}
