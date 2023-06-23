package databaseRelated;

import java.sql.*;

public class MySQLConnection {
    String URL = "jdbc:mysql://localhost/mychatroom";
    String username = "root";
    String password = "Admin";

    Boolean executeSQL(String sqlCmd) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, username, password);
            Statement statement = connection.prepareStatement(sqlCmd);

            statement.execute(sqlCmd);

            connection.close();

        } catch (Exception ex) {
            System.out.println("Error in Connecting to Data Base!\n\tPlease Check Your Connection.");
        }

        return true;
    }

    ResultSet executeQuery (String sqlCmd) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement(sqlCmd);

            ResultSet rs = statement.executeQuery();

            connection.close();

            return rs;

        } catch (Exception ex) {
            return null;
        }
    }
}