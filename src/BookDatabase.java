import java.sql.*;

public class BookDatabase extends Database {
    public BookDatabase() {
        try (Statement sqlStatement = sqlConnection.createStatement()) {
            ResultSet result = sqlStatement.executeQuery("SELECT * FROM book");

            printDatabaseQuery(result);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }
}
