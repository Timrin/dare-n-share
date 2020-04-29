package database;

import java.sql.*;

/**
 * Dare communication to Dare table in database
 * @author julia
 */

public class DareTable {
/*
    private Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection ok");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

 */
    private DBController dbController;
    public DareTable(DBController dbController) {
        this.dbController = dbController;

    }

    public void insertNewDareToDB(String objectiveType, String objective, String scopeType,
                                         int scopeLength, String start, String end) throws SQLException, ClassNotFoundException {
        String request1 = "INSERT INTO dare(ObjectiveType, Objective, ScopeType, ScopeLength ";
        String request2 = ",Start, End) VALUES ('" + objectiveType + "', '" + objective + "', '" + scopeType + "', '";
        String request3 = scopeLength + "','" + start + "','" + end + "');";
        String totalRequestLive = request1 + request2 + request3;
        insertToDare(totalRequestLive);
    }

    public void insertToDare(String request) throws SQLException, ClassNotFoundException {
       int dareId = 0;
        Connection connect = null;

        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:lib/dare_n_share.db";
        connect = DriverManager.getConnection(url);
        System.out.println("Connection ok");

        Statement statement = connect.createStatement();
        statement.execute(request);
        ResultSet result = statement.getGeneratedKeys();
        while (result.next()) {
            dareId = result.getInt(1);
            System.out.println(dareId);
            dbController.getDareIDfromDB(dareId);
            //DBController.getDareIDfromDB(dareId);
        }
    }

    public static void insertParticipantsToDare(int dareID, int partID) throws SQLException {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url ="jdbc:sqlite:lib/dare_n_share.db";
            conn= DriverManager.getConnection(url);
            System.out.println("Connection ok");
        }catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        String foreignK = "PRAGMA FOREIGN_KEYS = ON; ";
        String sql = " UPDATE dare SET Participants =" + partID + " WHERE Dareid =" + dareID + ";";
        assert conn != null;
        Statement statement = conn.createStatement();
        statement.execute(foreignK);
        System.out.println("f");
        statement.executeUpdate(sql);
    }

    public static void insertOpponent(int dareID, int opponent) throws SQLException {

        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url ="jdbc:sqlite:lib/dare_n_share.db";
            conn= DriverManager.getConnection(url);
            System.out.println("Connection ok");
        }catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        String sql = " UPDATE Dare set Opponent =" + opponent + " WHERE Dareid =" + dareID + ";";
        assert conn != null;
        Statement statement = conn.createStatement();
        statement.execute(sql);
    }

    /**
     * retrieves information from specific dare and store in variables
     *
     * @param id
     * @throws SQLException
     */
    public  void getDareFromDB(int id) throws SQLException {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url ="jdbc:sqlite:lib/dare_n_share.db";
            conn= DriverManager.getConnection(url);
            System.out.println("Connection ok");
        }catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        String query = "SELECT * FROM Dare where Dareid =" + id + ";";
        assert conn != null;
        Statement statement = conn.createStatement();

        statement.execute(query);
        ResultSet result = statement.getResultSet();
        while (result.next()) {
            int dareId = result.getInt(1);
            System.out.println(dareId);
            String objectiveType = result.getString(2);
            String objective = result.getString(3);
            System.out.println(objective);
            String scopeType = result.getString(4);
            System.out.println(scopeType);
            int scopeLength = result.getInt(5);
            System.out.println(scopeLength);
            String start = result.getString(6);
            String end = result.getString(7);
            int participants = result.getInt(8);
            String userID =  ParticipantTable.getParticipant(participants);
            System.out.println(participants);
            int opponent = result.getInt(9);
            String userOpponentId =ParticipantTable.getParticipant(opponent);
           // dbController.setDareFromDB(objectiveType,objective,scopeType,scopeLength,start,end,userID,userOpponentId);

        }

    }


}
