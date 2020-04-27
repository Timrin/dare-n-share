package database;

import java.sql.*;

/**
 * class that communicates to DB and table user with server
 * @author julia
 */

public class InsertUser {

    private Connection connect() {
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
     * insert userinformation to table User
     * @param name username from GUI
     *
     * @throws SQLException
     */
    public void addUserToDB(String name) throws SQLException {

        String request = "INSERT INTO user(username) VALUES ('" + name+ "')";
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


    //Get information from all columns for userId
    public void getUser(int userId) throws SQLException {
        String request = "SELECT * FROM user WHERE UserID=" + userId+";";
        Connection conn = this.connect();
        Statement statement = conn.createStatement();
        statement.execute(request);
        ResultSet result = statement.getResultSet();
        while (result.next()){
            String name = result.getString("UserName");
            System.out.println(name);
        }
    }

    public static void main(String[] args) throws SQLException {
        InsertUser iu = new InsertUser();
        iu.getUser(14);
    }


}
