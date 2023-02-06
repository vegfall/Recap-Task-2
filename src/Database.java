import java.sql.*;

public class Database {
    protected static Connection sqlConnection;

    public Database() {
        sqlConnection = connectDatabase();
    }

    private static Connection connectDatabase() {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/media?allowPublicKeyRetrieval=true&useSSL=false",
                    "mainUser",
                    "password123");

            System.out.println("Database successfully connected...");

            return connection;
        } catch (SQLException error) {
            error.printStackTrace();
            return null;
        }
    }

    public static void searchDatabase(String column, String input) {

    }
    public static void searchDatabase(String column, String input, Type type) {
        try {
            PreparedStatement sqlPrepareStatement;

            String query = "%" + input + "%";

            sqlPrepareStatement = sqlConnection.prepareStatement("SELECT * FROM " + type.toString() + " WHERE " + column + " LIKE ?");

            sqlPrepareStatement.setString(1, query);

            printDatabaseQuery(sqlPrepareStatement.executeQuery());
        } catch (SQLException error) {
            error.printStackTrace();
        }
    };

    public static void searchDatabase(Category category) {};

    public static void searchDatabase(Category category, Type type) {
        try (Statement sqlStatement = sqlConnection.createStatement()){
            String query = "'" + category + "'";

            ResultSet result = sqlStatement.executeQuery("SELECT * FROM " + type.toString() + " WHERE Category LIKE " + query);

            printDatabaseQuery(result);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    };
    public static void searchDatabase(String column, int input) {};
    public static void searchDatabase(String column, int input, Type type) {
        try {
            PreparedStatement sqlPrepareStatement = sqlConnection.prepareStatement("SELECT * FROM " + type.toString() + " WHERE " + column + " LIKE ?");

            sqlPrepareStatement.setInt(1, input);

            printDatabaseQuery(sqlPrepareStatement.executeQuery());
        } catch (SQLException error) {
            error.printStackTrace();
        }
    };



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