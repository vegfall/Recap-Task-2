public class Main {
    public static Database database;

    public static void main(String[] args) {
        database = new Database();

        database.readDatabase(Type.BOOK);
    }
}