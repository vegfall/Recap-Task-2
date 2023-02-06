import java.util.Scanner;

public class UserController {
    private static Scanner input;
    public UserController() {
        System.out.println("UserController successfully created...");
    }

    public static int getUserMenuChoice(int max) {
        System.out.print("Please select desired option: ");

        return getUserInt(1, max);
    }

    public static int getUserInt(int min, int max) {
        int value = 0;
        boolean valid = false;

        while (!valid) {
            input = new Scanner(System.in);

            if (input.hasNextInt()) {
                value = input.nextInt();
            }

            if (value >= min && value <= max) {
                valid = true;
            } else {
                System.out.print("Please type in a valid number between " + min + " - " + max + ": ");
            }
        }

        return value;
    }
}
