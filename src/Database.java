import java.sql.*;

public class Database {
    private static Connection sqlConnection;

    public Database() {
        sqlConnection = getSqlConnection();
    }

    private Connection getSqlConnection() {
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

    public static void readDatabase(String string) {

    }

    public static void readDatabase(Type type) {
        try (Statement sqlStatement = sqlConnection.createStatement()) {
            ResultSet result = sqlStatement.executeQuery("SELECT * FROM " + type.toString().toLowerCase());

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
/**
 * class Database {
 *     Connection connection;
 *
 *     private String addToDatabase = "INSERT INTO table1 (column1, column2) VALUES (30, 'Test2')";
 *
 *     public Database() {
 *         try (Connection con = getConnection()) {
 *             Statement statement = con.createStatement();
 *
 *             //statement.executeUpdate(addToDatabase);
 *
 *             ResultSet result = statement.executeQuery("SELECT * FROM table1");
 *
 *             while (result.next()) {
 *                 System.out.println(result.getString("column1"));
 *                 System.out.println(result.getString("column2"));
 *             }
 *         } catch (SQLException error) {
 *             error.printStackTrace();
 *         }
 *     }
 *
 *     public Connection getConnection(){
 *         try{
 *             return DriverManager.getConnection(
 *                             "jdbc:mysql://localhost:3306/java?allowPublicKeyRetrieval=true&useSSL=false",
 *                             "user1",
 *                             "pass"
 *                     );
 *         }
 *         catch(SQLException sqlException){
 *             sqlException.printStackTrace();
 *         }
 *         return null;
 *     }
 * }
 */
