import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static Connection sqlConnection;

    public static ArrayList<Media> getDatabaseList(Type database) {
        ArrayList<Media> list = new ArrayList<>();

        connectDatabase();

        try (Statement sqlStatement = sqlConnection.createStatement()) {
            ResultSet result;

            if (database == Type.BOOK || database == null) {
                result = sqlStatement.executeQuery("SELECT * FROM book");

                while (result.next()) {
                    list.add(new Book(
                            result.getInt("Id"),
                            result.getString("Title"),
                            Category.valueOf(result.getString("Category").toUpperCase()),
                            result.getString("Author"),
                            result.getInt("Pages")
                    ));
                }
            }

            if (database == Type.VIDEO || database == null) {
                result = sqlStatement.executeQuery("SELECT * FROM video");

                while (result.next()) {
                    list.add(new Video(
                            result.getInt("Id"),
                            result.getString("Title"),
                            Category.valueOf(result.getString("Category").toUpperCase()),
                            result.getString("Director"),
                            result.getInt("Duration")
                    ));
                }
            }

            if (database == Type.GAME || database == null) {
                result = sqlStatement.executeQuery("SELECT * FROM game");

                while (result.next()) {
                    list.add(new Game(
                            result.getInt("Id"),
                            result.getString("Title"),
                            Category.valueOf(result.getString("Category").toUpperCase()),
                            result.getString("Developer"),
                            result.getInt("Duration")
                    ));
                }
            }
        } catch (SQLException error) {
            disconnectDatabase();
            error.printStackTrace();
            System.exit(0);
        }

        disconnectDatabase();
        return list;
    }

    public static ArrayList<String[]> getPersonList() {
        ArrayList<String[]> personList = new ArrayList<>();

        connectDatabase();

        try (Statement sqlStatement = sqlConnection.createStatement()){
            ResultSet result = sqlStatement.executeQuery("SELECT * FROM person");

            while (result.next()) {
                String[] array = {result.getString("Id"), result.getString("Name")};

                personList.add(array);
            }

        } catch (SQLException error) {
            disconnectDatabase();
            error.printStackTrace();
            System.exit(0);
        }

        disconnectDatabase();
        return personList;
    }

    private static void connectDatabase() {
        try {
            sqlConnection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/media?allowPublicKeyRetrieval=true&useSSL=false",
                    "mainUser",
                    "password123");
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    private static void disconnectDatabase() {
        try {
            sqlConnection.close();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }



    /**
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
    }*/

}