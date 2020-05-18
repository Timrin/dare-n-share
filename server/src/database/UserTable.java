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
     */
    public static void addUserToDB(String userId, String name, String email) {

        Connection conn = null;
        Statement statement = null;

        try {
            Class.forName("org.sqlite.JDBC");
            String path = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(path);

            String query = "INSERT INTO user(UserID,username) VALUES ('" + userId + "','" + name + "','" + email + "')";
            assert conn != null;
            statement = conn.createStatement();
            statement.execute(query);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (Exception e) {
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
            }
        }
    }


    /**
     * method to retrieve name of user from table. FIXME: email kolumn, ska det hämtas här?
     *
     * @param userId
     * @return username
     */
    public static String getUser(String userId) {

        String name = null;

        Connection conn = null;
        Statement statement = null;
        ResultSet resultFromQuery = null;

        try {
            Class.forName("org.sqlite.JDBC");
            String path = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(path);


            String query = "SELECT UserName FROM User where UserID = '" + userId + "';";


            assert conn != null;
            statement = conn.createStatement();
            statement.execute(query);

            resultFromQuery = statement.getResultSet();

            while (resultFromQuery.next()) {
                name = resultFromQuery.getString("UserName");

            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (resultFromQuery != null) resultFromQuery.close();
            } catch (Exception e) {
            }
            try {
                if (statement != null) statement.close();
            } catch (Exception e) {
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
            }
        }
        return name;
    }

    public static String getUserIDWithEmail(String email) {
        String userId = null;

        Connection conn = null;
        Statement statement = null;
        ResultSet resultFromQuery = null;

        try {
            Class.forName("org.sqlite.JDBC");
            String path = "jdbc:sqlite:lib/dare_n_share.db";
            conn = DriverManager.getConnection(path);

            String query = "SELECT UserID from User where EmailUser ='" + email + "';";
            statement = conn.createStatement();
            statement.execute(query);
            userId = resultFromQuery.getString("UserID");

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (resultFromQuery != null) resultFromQuery.close();
            } catch (Exception e) {
            }
            try {
                if (statement != null) statement.close();
            } catch (Exception e) {
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
            }
        }

        return userId;
    }
}