import java.util.ArrayList;

public class MediaStorage {
    private static ArrayList<Media> media;
    private static Type databaseType;

    public MediaStorage() {
        media = new ArrayList<>();
        System.out.println("MediaStorage successfully loaded...");
        showMenuMain();
    }

    private static void showMenuMain() {
        int choice;

        newLine();

        System.out.print("Welcome to MediaStorageCo©. Please select media..." +
                "\n0. Exit." +
                "\n1. All.");

        for (int i = 0; i < Type.values().length; i++) {
            System.out.print("\n" + (i + 2) + ". " + formatWord(Type.values()[i]) + ".");
        }

        System.out.print("\nMedia: ");
        choice = Main.userController.getUserInt(Type.values().length + 1);

        if (choice == 0) {
            newLine();
            System.out.print("Have a nice day! :)");
            System.exit(0);
        } else if (choice == 1) {
            databaseType = null;
        } else {
            databaseType = Type.values()[choice - 2];
        }

        updateList();

        showMenuShow();
    }

    private static void showMenuShow() {
        int choice;

        newLine();

        if (databaseType == null) {
            System.out.println("Databases loaded. Available options:");
        } else {
            System.out.println("Database " + databaseType.toString().toLowerCase() + " loaded. Available options:");
        }

        System.out.print(
                "0. Back." +
                "\n1. Show all media." +
                "\n2. Search for media." +
                "\n3. Add media." +
                "\n4. Change media." +
                "\n5. Delete media." +
                "\nOption: ");

        choice = Main.userController.getUserInt(5);

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

        System.out.println(formatWord(databaseType) + " elements:");

        for (int i = 0; i < media.size(); i++) {
            printMediaObject(i);
        }

        System.out.print("Press enter to return...");
        Main.userController.getUserString();

        showMenuShow();
    }

    private static void showMenuSearch() {
        System.out.print("Search " + databaseType.toString().toLowerCase() + " database. Please select column: " +
                "\n0. 1");
    }

    private static void showMenuAdd() {
        String title;
        int uniqueIntValue;
        int person;
        Category category;
        Type type;

        newLine();

        person = showPersonList();

        if (databaseType == null) {
            newLine();

            System.out.println("Media types:");

            for (int i = 0; i < Type.values().length; i++) {
                System.out.println(formatWord((i + 1) + ". " + Type.values()[i] + "."));
            }

            System.out.print("Please select media type: ");

            type = Type.values()[Main.userController.getUserInt(1, Type.values().length) - 1];
        } else {
            type = databaseType;
        }

        newLine();

        System.out.print("Title: ");
        title = Main.userController.getUserString();

        newLine();

        System.out.print(getUniqueIntName(type) + ": ");
        uniqueIntValue = Main.userController.getUserInt(999999);

        newLine();

        System.out.print("Categories...");

        for (int i = 0; i < Category.values().length; i++) {
            System.out.print("\n" + (i) + ". " + formatWord(Category.values()[i]) + ".");
        }

        System.out.print("\nCategory: ");
        category = Category.values()[Main.userController.getUserInt(Category.values().length)];

        newLine();

        Main.database.addMedia(type, title, category, person, uniqueIntValue);

        updateList();

        System.out.println(formatWord(type) + " added successfully.");

        showMenuShow();
    }

    private static void showMenuRemove() {
        int choice;

        newLine();

        System.out.println(formatWord(databaseType) + " elements:" +
                "\n0. Back.");

        for (int i = 0; i < media.size(); i++) {
            printMediaObject(i);
        }

        System.out.print("WARNING! You're about to delete an item. Select 0 to cancel or select the Id of the item to delete: ");
        choice = Main.userController.getUserInt(media.size());

        if (choice != 0) {
            Main.database.removeMedia(media.get(choice - 1).getType(), media.get(choice - 1).getId());
            updateList();

            System.out.println("Element successfully deleted.");
        }

        showMenuShow();
    }

    private static void showMenuChange() {
        int choice;
        Media object;
        String column = "";
        String newValue = "";

        newLine();

        System.out.println(formatWord(databaseType) + ":" +
                "\n0. Back.");

        for (int i = 0; i < media.size(); i++) {
            printMediaObject(i);
        }

        System.out.print("Please select media to change: ");
        choice = Main.userController.getUserInt(media.size());

        newLine();

        printMediaObject(choice - 1);
        object = media.get(choice - 1);

        System.out.print(
                "0. Back." +
                "\n1. Title." +
                "\n2. " + object.getPersonTitle() + "." +
                "\n3. " + object.getIntTitle() + "." +
                "\n4. Category.");

        System.out.print("\nValue to change: ");
        choice = Main.userController.getUserInt(4);

        newLine();

        switch (choice) {
            case 1:
                column = "Title";
                System.out.print("Title: ");
                newValue = Main.userController.getUserString();
                break;
            case 2:
                if (object.getType() == Type.BOOK) {
                    column = "Author";
                } else if (object.getType() == Type.VIDEO) {
                    column = "Director";
                } else if (object.getType() == Type.GAME) {
                    column = "Developer";
                }

                newValue = Integer.toString(showPersonList());
                break;
            case 3:
                column = object.getIntTitle();

                System.out.print(column + ": ");
                newValue = Integer.toString(Main.userController.getUserInt(99999));
                break;
            case 4:
                column = "Category";
                System.out.print("Categories: ");

                for (int i = 0; i < Category.values().length; i++) {
                    System.out.print("\n" + (i + 1) + ". " + Category.values()[i].toString() + ".");
                }

                System.out.print("Category: ");
                newValue = Category.values()[Main.userController.getUserInt(1, Category.values().length) - 1].toString().toLowerCase();
                break;
        }

        Main.database.changeMedia(object.getType(), object.getId(), column, newValue);

    }

    //MENUS ABOVE

    private static void printMediaObject(int index) {
        Media object = media.get(index);

        System.out.print((index + 1) + ".\t [" + object.getType() + "] " + object.getTitle() + " - " + Main.database.getPerson(object.getPerson()) + " | ");

        if (object.getType() == Type.BOOK) {
            Book book = (Book)object;

            System.out.print("Pages: " + book.getPages());
        } else if (object.getType() == Type.VIDEO) {
            Video video = (Video)object;

            System.out.print("Duration: " + video.getDuration());
        } else if (object.getType() == Type.GAME) {
            Game game = (Game)object;

            System.out.print("Metascore: " + game.getMetascore());
        }

        System.out.println(". (" + object.getCategory().toString() + ")");
    }

    private static int showPersonList() {
        int index = 0;
        int choice;
        String input;
        String title;

        ArrayList<String[]> personList = Main.database.getPersonList();

        if (databaseType == Type.BOOK) {
            title = "Author";
        } else if (databaseType == Type.VIDEO) {
            title = "Director";
        } else if (databaseType == Type.GAME) {
            title = "Developer";
        } else {
            title = "Person";
        }

        System.out.println(title + "(s):" +
                "\n0. Back.");

        for (int i = 0; i < personList.size(); i++) {
            System.out.println((i + 1) + ". " + personList.get(i)[1] + ".");
        }

        System.out.print((personList.size() + 1) + ". New " + title + "." +
                "\nPlease select available " + title + "(s) or add new: ");

        choice = Main.userController.getUserInt(personList.size() + 1);

        if (choice == 0) {
            showMenuShow();
        } else if (choice == (personList.size() + 1)) {
            System.out.print(title + " name: ");

            input = formatWord(Main.userController.getUserString());

            index = Main.database.addPerson(input);
        } else {
            index = Integer.parseInt(personList.get(choice - 1)[0]);
        }

        return index;
    }

    public static String formatWord(Enum object) {
        if (object != null) {
            String formatedString = object.toString();
            return formatedString.substring(0, 1).toUpperCase() + formatedString.substring(1).toLowerCase();
        }

        return "Media";
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

    private static void newLine() {
        System.out.println("--------------------");
    }

    private static void updateList() {
        media = Main.database.getDatabaseList(databaseType);
    }

    private static String getUniqueIntName(Type type) {
        if (type == Type.BOOK) {
            return "Pages";
        } else if (type == Type.VIDEO) {
            return "Duration";
        } else if (type == Type.GAME) {
            return "Metascore";
        }

        return null;
    }
}