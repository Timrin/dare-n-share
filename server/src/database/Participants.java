package database;

import java.sql.*;

/**
 * class insert/revieces info from table Participants in DB
 * @author julia
 */
public class Participants {

    //Jag tänker att controllern kan ha connect metod och skicka med connection i
    //parametern? går det tro?
    private Connection connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/darenshare?serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "julia");
            System.out.println(con);
            System.out.println(con.getClientInfo().toString());
            return con;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connect();
    }

    /**
     * add Participants to table, retrieves the primary key and store in variable
     * @throws SQLException for SQL error that can happen during execute and createstatement
     */
    public void addParticipant(int userID,int dareID) throws SQLException {
        Connection connect = this.connect();
        String query ="INSERT INTO participants(UserId, DareId) VALUES ("+userID + ","+dareID+");";
        Statement statement = connect.createStatement();
        statement.execute(query,Statement.RETURN_GENERATED_KEYS);

        //get the generated key for PartID and saves in variable participantID
        ResultSet partIdKey = statement.getGeneratedKeys();
        while (partIdKey.next()){
            int participantID = partIdKey.getInt(1);
            System.out.println(participantID);
        }
    }
}
