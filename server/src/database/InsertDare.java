package database;

import java.sql.*;

public class InsertDare {


    /**
     * MySQL connection to database. (do not touch)
     *
     * @return connection to database
     */
    private Connection connect() {

        try {
            String url = "jdbc:mysql://localhost:3306/darenshare?serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "julia");
            //System.out.println(con);
           // System.out.println(con.getClientInfo().toString());
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connect();
    }
/*
    public void insertDareRequest(String objectiveType, String objective, String scopeType,
                                  String scopeLength, String start, String end) throws SQLException {
        String chr = "'";
        String request = "'NOMEAT','YESNO','goal','2days','today','sunday'";
        // insertToDare(request);
    }

 */

    /**
     * insert new dare to table, returns generated primary key and store in variable
     * @throws SQLException
     */
    public void insertToDare() throws SQLException {

        Connection conn = this.connect();
        String sql = "INSERT INTO Dare(ObjectiveType, Objective, ScopeType, ScopeLength, Start, End) VALUES " +
                "('NOMEAT','YESNO','goal','2days','today','sunday');";
        Statement statement = conn.createStatement();
        statement.execute(sql,Statement.RETURN_GENERATED_KEYS);


        ResultSet result = statement.getGeneratedKeys();
        while (result.next()) {
            int dareId = result.getInt(1);

            System.out.println(dareId);
        }
    }


    /**
     * add value of partID into column participant in Dare table
     *
     * @param partID int with primary key from Participant table
     * @param dareID int to get which row to enter the value of PartID
     * @throws SQLException
     */
    public void insertParticipantsToDare(int partID, int dareID) throws SQLException {
        Connection conn = this.connect();
        String sql = "UPDATE dare set Participants = 4 WHERE Dareid = 3";
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
        String query = "SELECT * FROM dare where Dareid =" + id + ";";
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
        InsertDare id = new InsertDare();
        id.insertToDare();
    }

}
