public class Main {
    public static Database database;
    public static MediaStorage mediaStorage;
    public static UserController userController;

    public static void main(String[] args) {
        database = new Database();
        userController = new UserController();
        mediaStorage = new MediaStorage();
    }
}