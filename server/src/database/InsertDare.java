package database;

import java.sql.*;

/**
 * Class that handles requests from server about table dare in DB
 * @author julia
 */

public class InsertDare {

    private DBController dbController;

    public InsertDare(DBController dbController){
        this.dbController = dbController;
    }

    public InsertDare() {

    }

    /**
     * MySQL connection to database. (do not touch)
     *
     * @return connection to database
     */
    private Connection connect() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/darenshare?serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "julia");
            //System.out.println(con);
           // System.out.println(con.getClientInfo().toString());
            return con;
        } catch (SQLException | ClassNotFoundException e) {
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
    public void insertNewDareToDB(String objectiveType,String objective,String scopeType,
                                  String scopeLength,String start,String end) throws SQLException {
        String sql1 = "INSERT INTO dare(ObjectiveType, Objective, ScopeType, ScopeLength ";
        String sql2 = ",Start, End) VALUES ('" + objectiveType+"', '"+objective+"', '"+scopeType+"', '";
        String sql3= scopeLength + "','"+ start+"','"+end+"');";
        String totalRequestLive = sql1+sql2+sql3;
        insertToDare(totalRequestLive);
    }


    /**
     * insert new dare to table, returns generated primary key and store in variable
     * @throws SQLException
     */
    public void insertToDare(String sqlRequest) throws SQLException {

        Connection conn = this.connect();
        Statement statement = conn.createStatement();
        statement.execute(sqlRequest,Statement.RETURN_GENERATED_KEYS);
        ResultSet result = statement.getGeneratedKeys();
        while (result.next()) {
            int dareId = result.getInt(1);

            dbController.getDareIDfromDB(dareId);

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
        String sql = "UPDATE dare set Participants =" + partID+" WHERE Dareid ="+ dareID +";";
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
        //id.insertParticipantsToDare(2,8);
       // id.insertNewDareToDB("Goal","FeedDucks","Y/N","4days","","");
        id.getDareFromDB(8);
    }

}
