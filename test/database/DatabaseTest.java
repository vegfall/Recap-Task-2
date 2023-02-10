package database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class DatabaseTest {
    @Test
    public void DatabaseTest() {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/media?allowPublicKeyRetrieval=true&useSSL=false",
                    "mainUser",
                    "password123");

            Statement sqlStatement = connection.createStatement();

            ResultSet result = sqlStatement.executeQuery("SELECT * FROM book");



            while (result.next()) {
                System.out.println(result.getString("title"));
            }

            System.out.println("---");

            result = sqlStatement.executeQuery("SELECT * FROM video");

        } catch (SQLException error) {
            error.printStackTrace();
        }
    }
}
