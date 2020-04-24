package database;

import java.sql.*;

public class InsertUser {

    private Connection connect() {
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
     * insert userinformation to table User
     * @param name username from GUI
     * @param friend test for now.
     * @throws SQLException
     */
    public void addUserToDB(String name, String friend) throws SQLException {

        String request = "INSERT INTO user(username, friends) VALUES ('Karolina', 'Julia')";
        Connection conn = this.connect();
        Statement statement = conn.createStatement();
        statement.execute(request,Statement.RETURN_GENERATED_KEYS);

        //get the generated primary key and store in userID
        ResultSet userIdKey = statement.getGeneratedKeys();
        while (userIdKey.next()) {
            int userID = userIdKey.getInt(1);
            System.out.println(userID);
        }
    }


    public static void main(String[] args) throws SQLException {
        InsertUser iu = new InsertUser();
        iu.addUserToDB("aa","ss");
    }
}
