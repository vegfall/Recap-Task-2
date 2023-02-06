public class Main {
    public static Database database;
    public static MediaStorage mediaStorage;
    public static UserController userController;

    public static void main(String[] args) {
        database = new Database();
        userController = new UserController();
        mediaStorage = new MediaStorage();


        //database.searchDatabase("title", "2", Type.BOOK);
        //database.searchDatabase(Category.ACTION, Type.BOOK);
        //database.searchDatabase("Id", 2, Type.BOOK);


    }

    public static void NewLine() {
        System.out.println("--------------------");
    }
}