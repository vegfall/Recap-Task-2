import java.sql.*;

public class Database {
    protected static Connection sqlConnection;

    public Database() {
        sqlConnection = connectDatabase();
    }

    private static Connection connectDatabase() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/media?allowPublicKeyRetrieval=true&useSSL=false",
                    "mainUser",
                    "password123");
        } catch (SQLException error) {
            error.printStackTrace();
            return null;
        }
    }

    protected static Connection getSqlConnection() {
        return sqlConnection;
    }

    public static void readDatabase(Type type, String string) {
        try {
            PreparedStatement sqlPreparedStatement;

            sqlPreparedStatement = sqlConnection.prepareStatement("SELECT * FROM " + type.toString().toLowerCase() + " WHERE Id = ?");

            sqlPreparedStatement.setString(1, string);

            printDatabaseQuery(sqlPreparedStatement.executeQuery());
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public static void readDatabase(Type type) {
        try (Statement sqlStatement = sqlConnection.createStatement()) {
            ResultSet result = sqlStatement.executeQuery("SELECT * FROM " + type.toString().toLowerCase());

            printDatabaseQuery(result);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    protected static void printDatabaseQuery(ResultSet result) {
        try {
            while (result.next()) {
                System.out.println(
                        "ID: " + result.getString("Id")
                                + " Title: " + result.getString("Title")
                                + " Category: " + result.getString("Category"));
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }
}