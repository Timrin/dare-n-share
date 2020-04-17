package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class creates and modifies tables in DareNshare database. use carefully.
 * @author julia
 */

public class CreateTable {

    /**
     * MySQL connection to database. (do not touch)
     * @return connection to database
     */
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


    public void createTable() throws SQLException {
        Connection con = this.connect();
        // String sql = "CREATE TABLE Dare (Dareid integer AUTO_INCREMENT, ObjectiveType text, Objective text, " +
       //     "ScopeType text, ScopeLength text,Start text, End text, Participants integer, primary key (Dareid));";
       //  String sql1 ="CREATE TABLE User(UserID integer AUTO_INCREMENT, UserName text, Friends text, primary key (UserID));";
         //String sql2 ="CREATE TABLE Participants(PartID integer AUTO_INCREMENT, UserId integer, DareId integer, " +
          //     "primary key (PartID), foreign key (UserId) references darenshare.user(UserID), foreign key (DareId)references darenshare.dare(Dareid))";
        String sql="ALTER TABLE darenshare.dare ADD FOREIGN KEY (Participants) references darenshare.participants(PartID)";
        Statement statement = con.createStatement();
       // statement.execute(sql);
       // statement.execute(sql1);
        statement.execute(sql);
    }

    public static void main(String[] args) throws SQLException {
        CreateTable table = new CreateTable();
        table.createTable();
    }

}


