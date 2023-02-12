import java.util.ArrayList;

public class MediaStorage {
    private static ArrayList<Media> media;
    private static Type databaseType;
    private static String uniqueString;
    private static String uniqueInt;

    public MediaStorage() {
        media = new ArrayList<>();
        System.out.println("MediaStorage successfully loaded...");
        newLine();
        showMenuMain();
    }

    private static void showMenuMain() {
        int choice = 0;

        newLine();

        System.out.print("Welcome to MediaStorageCo©. Please select media..." +
                "\n0. Exit.");

        for (int i = 0; i < Type.values().length; i++) {
            System.out.print("\n" + (i + 1) + ". " + formatWord(Type.values()[i]) + ".");
        }

        System.out.print("\n" + (Type.values().length + 1) + ". All.");

        System.out.print("\nMedia: ");
        choice = Main.userController.getUserInt(0, (Type.values().length + 1));

        if (choice > 0 && choice < Type.values().length + 1) {
            databaseType = Type.values()[choice - 1];
        } else if (choice == 0) {
            newLine();
            System.out.print("Have a nice day! :)");
            System.exit(0);
        } else {
            databaseType = null;
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
        } else if (databaseType == null) {
            uniqueString = "Person";
            uniqueInt = "Number value";
        }

        showMenuShow();
    }

    private static void showMenuShow() {
        int choice;

        newLine();

        if (databaseType == null) {
            System.out.print("Databases loaded. Available options..." +
                    "\n0. Back." +
                    "\n1. Show all." +
                    "\n2. Search in database." +
                    "\nOption: ");

            choice = Main.userController.getUserInt(0, 2);
        } else {
            System.out.print("Database " + databaseType.toString().toLowerCase() + " loaded. Available options..." +
                    "\n0. Back." +
                    "\n1. Show all." +
                    "\n2. Search in database." +
                    "\n3. Add to database." +
                    "\n4. Change element in database." +
                    "\n5. Delete element in database." +
                    "\nOption: ");

            choice = Main.userController.getUserInt(0, 5);
        }

        switch (choice) {
            case 0:
                showMenuMain();
                break;
            case 1:
                showMenuView();
                break;
            case 2:
                showMenuSearch();
                break;
            case 3:
                showMenuAdd();
                break;
            case 4:
                showMenuChange();
                break;
            case 5:
                showMenuRemove();
                break;
        }
    }

    private static void showMenuView() {
        newLine();

        for (int i = 0; i < media.size(); i++) {
            printMediaObject(i);
        }

        System.out.print("\nPress enter to return...");
        Main.userController.getUserString();

        showMenuShow();
    }

    private static void showMenuSearch() {
        System.out.print("Search " + databaseType.toString().toLowerCase() + " database. Please select column: " +
                "\n0. 1");
    }

    private static void showMenuAdd() {
        int choice;
        String title;
        int uniqueIntValue;
        int person = 0;
        Category category;

        ArrayList<String[]> personList = Main.database.getPersonList();

        newLine();

        System.out.println(uniqueString + "(s):" +
                "\n0. Back.");

        for (int i = 0; i < personList.size(); i++) {
            System.out.println((i + 1) + ". " + personList.get(i)[1] + ".");
        }

        System.out.print((personList.size() + 1) + ". New " + uniqueString + "." +
                "\nPlease select available " + uniqueString + "(s) or add new: ");

        choice = Main.userController.getUserInt(0, personList.size() + 1);

        if (choice == 0) {
            showMenuShow();
        } else if (choice == (personList.size() + 1)) {
            person = addPerson();
        } else {
            person = Integer.parseInt(personList.get(choice - 1)[0]);
        }

        newLine();

        System.out.print("Title: ");
        title = Main.userController.getUserString();

        newLine();

        System.out.print(uniqueInt + ": ");
        uniqueIntValue = Main.userController.getUserInt(0, 999999);

        newLine();

        System.out.print("Categories...");

        for (int i = 0; i < Category.values().length; i++) {
            System.out.print("\n" + (i) + ". " + formatWord(Category.values()[i]) + ".");
        }

        System.out.print("\nCategory: ");
        category = Category.values()[Main.userController.getUserInt(0, Category.values().length)];

        newLine();

        Main.database.addMedia(databaseType, title, category, person, uniqueIntValue);

        media = Main.database.getDatabaseList(databaseType);

        //addMedia(title, uniqueIntValue, person, category);

        showMenuShow();
    }

    private static void showMenuRemove() {}

    private static void showMenuChange() {}

    //MENUS ABOVE

    private static void printMediaObject(int index) {
        Media object = media.get(index);

        System.out.print("[" + object.getType() + "] " + object.getId() + ". " + object.getTitle() + " - ");

        if (object.getType() == Type.BOOK) {
            Book book = (Book)object;

            System.out.print(book.getAuthor() + ". Pages: " + book.getPages());
        } else if (object.getType() == Type.VIDEO) {
            Video video = (Video)object;

            System.out.print(video.getDirector() + ". Duration: " + video.getDuration());
        } else if (object.getType() == Type.GAME) {
            Game game = (Game)object;

            System.out.print(game.getDeveloper() + ". Metascore: " + game.getMetascore());
        }

        System.out.println(". (" + object.getCategory().toString() + ")");
    }

    private static int addPerson() {
        String userInput;

        System.out.print(uniqueString + " name: ");

        userInput = Main.userController.getUserString();
        userInput = formatWord(userInput);

        return Main.database.addPerson(userInput);
    }

    private static void addMedia(String title, int uniqueIntValue, int person, Category category) {

    }

    public static String formatWord(Enum object) {
        String formatedString = object.toString();
        return formatedString.substring(0, 1).toUpperCase() + formatedString.substring(1).toLowerCase();
    }

    public static String formatWord(String string) {
        String[] separated = string.split(" ");
        String returnValue = "";

        for (int i = 0; i < separated.length; i++) {
            if (i == 0) {
                returnValue += separated[i].substring(0, 1).toUpperCase() + separated[i].substring(1).toLowerCase();
            } else {
                returnValue += " " + separated[i].substring(0, 1).toUpperCase() + separated[i].substring(1).toLowerCase();
            }
        }

        return returnValue;
    }

    public static void newLine() {
        System.out.println("--------------------");
    }
}