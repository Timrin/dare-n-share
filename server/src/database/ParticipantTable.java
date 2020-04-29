package database;

import java.sql.*;

public class ParticipantTable {



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



    /**
     * add Participants to table, retrieves the primary key and store in variable
     * @throws SQLException for SQL error that can happen during execute and createstatement
     */
    public void addParticipant(int userID,int dareID) throws SQLException {
        Connection connect = this.connect();
        String query =" INSERT INTO participants(UserId, DareId) VALUES ("+userID + ","+dareID+");";
        Statement statement = connect.createStatement();

        String pragma = "PRAGMA FOREIGN_KEYS = on";
        statement.execute(pragma);
        statement.executeUpdate(query);

        //get the generated key for PartID and saves in variable participantID
        ResultSet partIdKey = statement.getGeneratedKeys();
        while (partIdKey.next()){
            int participantID = partIdKey.getInt(1);
            System.out.println(participantID);
        }
    }
}
