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
                            result.getInt("Author"),
                            Category.valueOf(result.getString("Category").toUpperCase()),
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
                            result.getInt("Director"),
                            Category.valueOf(result.getString("Category").toUpperCase()),
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
                            result.getInt("Developer"),
                            Category.valueOf(result.getString("Category").toUpperCase()),
                            result.getInt("Metascore")
                    ));
                }
            }
        } catch (SQLException error) {
            error.printStackTrace();
        } finally {
            disconnectDatabase();
        }

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
            error.printStackTrace();
        } finally {
            disconnectDatabase();
        }

        return personList;
    }

    public static void addMedia(Type database, String title, Category category, int person, int uniqueInt) {
        String stringColumnName = "";
        String intColumnName = "";
        String formatCategory = "'" + category.toString().toLowerCase() + "'";


        if (database == Type.BOOK) {
            stringColumnName = "Author";
            intColumnName = "Pages";
        } else if (database == Type.VIDEO) {
            stringColumnName = "Director";
            intColumnName = "Duration";
        } else if (database == Type.GAME) {
            stringColumnName = "Developer";
            intColumnName = "Metascore";
        }

        connectDatabase();

        try {
            PreparedStatement sqlPrepareStatement = sqlConnection.prepareStatement(
                    "INSERT INTO " + database + " (Id, Title, Category, " + stringColumnName + ", " + intColumnName + ")" +
                        "VALUE (" + findAvailableId(database.toString()) + ", ?, " + formatCategory + ", " + person + ", " + uniqueInt + ")"
            );

            sqlPrepareStatement.setString(1, title);

            sqlPrepareStatement.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        } finally {
            disconnectDatabase();
        }
    }

    public static void removeMedia(Type database, int Id) {
        connectDatabase();

        try (Statement sqlStatement = sqlConnection.createStatement()) {
            sqlStatement.executeUpdate("DELETE FROM " + database + " WHERE Id = " + Id);
        } catch (SQLException error) {
            error.printStackTrace();
        } finally {
            disconnectDatabase();
        }
    }

    public static void changeMedia(Type database, int Id, String column, String newValue) {
        connectDatabase();

        try {
            PreparedStatement sqlPrepareStatement = sqlConnection.prepareStatement(
                    "UPDATE " + database + " SET " + column + " = ? WHERE ID = " + Id);

            sqlPrepareStatement.setString(1, newValue);

            sqlPrepareStatement.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        } finally {
            disconnectDatabase();
        }
    }

    public static int addPerson(String name) {
        int index = 0;

        connectDatabase();

        index = findAvailableId("person");

        try {
            PreparedStatement sqlPrepareStatement;

            sqlPrepareStatement = sqlConnection.prepareStatement("INSERT INTO person (Id, Name) VALUE (" + index + ", ?)");

            sqlPrepareStatement.setString(1, name);

            sqlPrepareStatement.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        } finally {
            disconnectDatabase();
        }

        return index;
    }

    public static String getPerson(int index) {
        String name = "";

        connectDatabase();

        try (Statement sqlStatement = sqlConnection.createStatement()) {
            ResultSet result = sqlStatement.executeQuery("SELECT * FROM person WHERE Id = " + index);

            while (result.next()) {
                name = result.getString("Name");
            }
        } catch (SQLException error) {
            error.printStackTrace();
        } finally {
            disconnectDatabase();
        }

        return name;
    }

    private static int findAvailableId(String database) {
        int currentIndex = 0;
        boolean spaceBetween = false;

        try (Statement sqlStatement = sqlConnection.createStatement()) {
            ResultSet result = sqlStatement.executeQuery("SELECT * FROM " + database);

            while (result.next()) {
                if (result.getInt("Id") > currentIndex + 1) {
                    currentIndex++;
                    spaceBetween = true;
                    break;
                } else {
                    currentIndex++;
                }
            }

            if (!spaceBetween) {
                currentIndex++;
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }

        return currentIndex;
    }

    private static void connectDatabase() {
        try {
            sqlConnection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/media?allowPublicKeyRetrieval=true&useSSL=false",
                    "mainUser",
                    "password123");
        } catch (SQLException error) {
            if (sqlConnection != null) {
                disconnectDatabase();
            }
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
}