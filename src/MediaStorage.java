import java.util.ArrayList;

public class MediaStorage {
    private static ArrayList<Media> media;
    private static Type databaseType;
    private static String uniqueString;
    private static String uniqueInt;

    public MediaStorage() {
        media = new ArrayList<>();
        System.out.println("MediaStorage successfully loaded...");
        Main.NewLine();
        showMenuMain();
    }

    private static void showMenuMain() {
        int choice = 0;

        System.out.print("Welcome to MediaStorageCo©. Please select media...");

        for (int i = 0; i < Type.values().length; i++) {
            String tmpType = Type.values()[i].toString();
            tmpType = tmpType.substring(0, 1).toUpperCase() + tmpType.substring(1).toLowerCase();

            System.out.print("\n" + (i) + ". " + tmpType + ".");
        }

        System.out.print("\n" + (Type.values().length) + ". All.");
        System.out.print("\n" + (Type.values().length + 1) + ". Exit.");

        System.out.print("\nMedia: ");
        choice = Main.userController.getUserInt(0, (Type.values().length));

        if (choice != Type.values().length) {
            databaseType = Type.values()[choice];
        } else if (choice > Type.values().length) {
            System.out.print("Have a nice day! :)");
            System.exit(0);
        }

        media = Main.database.getDatabaseList(databaseType);

        if (databaseType == Type.BOOK) {
            uniqueString = "Author";
            uniqueInt = "Pages";
        } else if (databaseType == Type.VIDEO) {
            uniqueString = "Director";
            uniqueInt = "Duration";
        } else if (databaseType == Type.GAME) {
            uniqueString = "Developer";
            uniqueInt = "Metascore";
        }

        showMenuShow();
    }

    private static void showMenuShow() {
        int choice;

        if (databaseType == null) {
            System.out.print("Databases loaded. ");
        } else {
            System.out.print("Database " + databaseType.toString().toLowerCase() + " loaded. ");
        }

        Main.NewLine();

        System.out.print("Available options..." +
                "\n0. Show all." +
                "\n1. Find in database." +
                "\n2. Add to database." +
                "\n3. Change element in database." +
                "\n4. Delete element in database." +
                "\n5. Back." +
                "\nOption: ");
       choice = Main.userController.getUserInt(0, 5);

        switch (choice) {
            case 0:
                showMenuView();
                break;
            case 1:
                showMenuSearch();
                break;
            case 2:
                showMenuAdd();
                break;
            case 3:
                showMenuChange();
                break;
            case 4:
                showMenuRemove();
                break;
            case 5:
                showMenuMain();
                break;
        }
    }

    private static void showMenuView() {
        System.out.print("Showing " + databaseType.toString().toLowerCase() + " values...");

        for (int i = 0; i < media.size(); i++) {

        }

        System.out.print("Press enter to return...");
        Main.userController.getUserString()
    }

    private static void showMenuSearch() {
        System.out.print("Search " + databaseType.toString().toLowerCase() + " database. Please select column: " +
                "\n0. 1");
    }
    private static void showMenuAdd() {}
    private static void showMenuRemove() {}
    private static void showMenuChange() {}
}


/**
 * Find entry
 * Exit
 */

