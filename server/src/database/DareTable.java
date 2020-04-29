package database;

import java.sql.*;

public class DareTable {

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


    public void insertNewDareToDB(String objectiveType,String objective,String scopeType,
                                  int scopeLength,String start,String end) throws SQLException {
        String sql1 = "INSERT INTO dare(ObjectiveType, Objective, ScopeType, ScopeLength ";
        String sql2 = ",Start, End) VALUES ('" + objectiveType+"', '"+objective+"', '"+scopeType+"', '";
        String sql3= scopeLength + "','"+ start+"','"+end+"');";
        String totalRequestLive = sql1+sql2+sql3;
        insertToDare(totalRequestLive);
    }

    public void insertToDare(String request) throws SQLException {

        Connection conn = this.connect();
        Statement statement = conn.createStatement();
        statement.execute(request);
        ResultSet result = statement.getGeneratedKeys();
        while (result.next()) {
            int dareId = result.getInt(1);
            System.out.println(dareId);
        }
    }
    public void insertParticipantsToDare( int dareID,int partID) throws SQLException {
        Connection conn = this.connect();
        String foreignK= "PRAGMA FOREIGN_KEYS = ON; ";
        String sql = " UPDATE dare SET Participants ="+partID+" WHERE Dareid ="+ dareID +";";
        Statement statement = conn.createStatement();
        statement.execute(foreignK);
        System.out.println("f");
        statement.executeUpdate(sql);
    }

    public void insertOpponent(int dareID,int opponent)throws SQLException{
        Connection conn = this.connect();
        String sql =" UPDATE Dare set Opponent =" +opponent+" WHERE Dareid =" + dareID+";";
        Statement statement = conn.createStatement();
        statement.execute(sql);
    }

    /**
     * retrieves information from specific dare and store in variables
     * @param id
     * @throws SQLException
     */
    public void getDareFromDB(int id) throws SQLException {

        Connection conn = this.connect();
        String query = "SELECT * FROM Dare where Dareid =" + id + ";";
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
            String scopeLength = result.getString(5);
            System.out.println(scopeLength);
            String start = result.getString(6);
            String end = result.getString(7);
            int participants = result.getInt(8);
            System.out.println(participants);
        }


    }

    public static void main(String[] args) throws SQLException {
        DareTable table = new DareTable();
        table.getDareFromDB(2);
    }

}
