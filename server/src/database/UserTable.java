package database;


import java.sql.*;

/**
 * communication class with User table in database
 *
 * @author julia
 */
public class UserTable {

    /**
     * insert userinformation to table User
     *FIXME: insert email
     * @param name username from GUI
     * @throws SQLException
     */
    public static void addUserToDB(String userId, String name) {

        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String path = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(path);

            String query = "INSERT INTO user(UserID,username) VALUES ('" + userId + "','" + name + "')";
            assert conn != null;
            Statement statement = conn.createStatement();
            statement.execute(query);

            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * method to retrieve name of user from table. FIXME: email kolumn, ska det hämtas här?
     * @param userId
     * @return username
     */
    public static String getUser(String userId) {
        String name = null;
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String path = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(path);


            String query = "SELECT UserName FROM User where UserID = '" + userId + "';";


            assert conn != null;
            Statement statement = conn.createStatement();
            statement.execute(query);

            ResultSet resultFromQuery = statement.getResultSet();

            while (resultFromQuery.next()) {
                name = resultFromQuery.getString("UserName");

            }
            resultFromQuery.close();
            conn.close();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return name;
    }


}
