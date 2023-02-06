public class MediaStorage {
    public MediaStorage() {
        System.out.println("MediaStorage controller successfully loaded...");
        Main.NewLine();
        showMenuMain();
    }

    private static void showMenuMain() {
        int choice = 0;

        System.out.print("Welcome to MediaStorageCo©. Available media...");

        for (int i = 0; i < Type.values().length; i++) {
            System.out.print("\n" + (i + 1) + ". " + Type.values()[i] + ".");
        }

        System.out.println("\n" + (Type.values().length + 1) + ". Exit.");

        choice = Main.userController.getUserMenuChoice(Type.values().length + 1);
        System.out.println(choice);
    }
}
