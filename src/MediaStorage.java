import java.util.ArrayList;

public class MediaStorage {
    public static ArrayList<Media> media;

    public MediaStorage() {
        media = new ArrayList<>();
        System.out.println("MediaStorage successfully loaded...");
        Main.NewLine();
        showMenuMain();
    }

    private static void showMenuMain() {
        int choice = 0;
        Type database = null;

        System.out.print("Welcome to MediaStorageCo©. Please select media...");

        for (int i = 0; i < Type.values().length; i++) {
            String tmpType = Type.values()[i].toString();
            tmpType = tmpType.substring(0, 1).toUpperCase() + tmpType.substring(1).toLowerCase();

            System.out.print("\n" + (i) + ". " + tmpType + ".");
        }

        System.out.print("\n" + (Type.values().length) + ". All.");
        System.out.print("\n" + (Type.values().length + 1) + ". Exit.");

        choice = Main.userController.getUserInt(0, (Type.values().length));

        if (choice != Type.values().length) {
            database = Type.values()[choice];
        } else if (choice > Type.values().length) {
            System.out.print("Have a nice day! :)");
            System.exit(0);
        }

        media = Main.database.getDatabaseList(database);
    }

    private static void showMenuShow() {}
    private static void showMenuAdd() {}
    private static void showMenuRemove() {}
    private static void showMenuChange() {}
}


/**
 * Find entry
 * Exit
 */

