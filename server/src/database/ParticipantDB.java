package database;

import java.sql.*;

/**
 * Class that add and get information from table Participants.
 * @author julia
 */

public class ParticipantDB {

    private Connection connect(){
        try {
            String url = "jdbc:mysql://localhost:3306/darenshare?serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "julia");
            System.out.println(con);
            System.out.println(con.getClientInfo().toString());
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connect();
    }

    /**
     * add Participants to table, retrieves the primary key and store in variable
     * @throws SQLException for SQL error that can happen during execute and createstatement
     */
    public void addParticipant() throws SQLException {
        Connection connect = this.connect();
        String query ="INSERT INTO participants(UserId, DareId) VALUES (5,22)";
        Statement statement = connect.createStatement();
        statement.execute(query,Statement.RETURN_GENERATED_KEYS);

        //get the generated key for PartID and saves in variable participantID
        ResultSet partIdKey = statement.getGeneratedKeys();
        while (partIdKey.next()){
            int participantID = partIdKey.getInt(1);
            System.out.println(participantID);
        }
    }

    public static void main(String[] args) throws SQLException {
        ParticipantDB pd = new ParticipantDB();
        pd.addParticipant();
    }
}
