/**
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//Leaving video.txt empty to show what happens when trying to read it in console.
enum MediaType {
    BOOK, VIDEO, GAME
}

enum Category {
    FANTASY, HORROR, ACTION, SCIENCE_FICTION, OTHER
}

class Loader {
    private static MediaStorage storage;

    public static void main(String[] args) {
        storage = new MediaStorage();
    }

    public static void NewLine() {
        System.out.println("--------------------");
    }
}

class MediaStorage {
    private ArrayList<Media> mediaList;
    private MediaType mediaType;
    private int playerInput;

    public MediaStorage() {
        System.out.println("Loading program...");
        Loader.NewLine();
        ShowMenuMain();
    }

    private void ShowMenuMain() {
        mediaList = new ArrayList<>();

        System.out.print("Welcome to MediaStorageCO©. Available media..." +
                "\n1. Book." +
                "\n2. Video." +
                "\n3. Game." +
                "\n4. Exit" +
                "\nPlease select desired option: ");

        playerInput = GetPlayerInt(1, 4);
        Loader.NewLine();

        switch (playerInput) {
            case 1:
                mediaType = MediaType.BOOK;
                break;
            case 2:
                mediaType = MediaType.VIDEO;
                break;
            case 3:
                mediaType = MediaType.GAME;
                break;
            default:
                System.out.println("Have a nice day :)");
                System.exit(0);
                break;
        }

        LoadList();
        ShowMenuList();
    }

    private void ShowMenuList() {
        System.out.print(mediaType + ". Available choices..." +
                "\n1. View entries." +
                "\n2. Add entry." +
                "\n3. Remove entry." +
                "\n4. Go Back." +
                "\nPlease select desired option: ");

        playerInput = GetPlayerInt(1, 4);
        Loader.NewLine();

        switch (playerInput) {
            case 1:
                if (mediaList.size() > 0) {
                    ShowMenuView();
                } else {
                    System.out.println("Number of entries in " + mediaType + " is 0. To add new, please select option 2.");
                    ShowMenuList();
                }
                break;
            case 2:
                ShowMenuAdd();
                break;
            case 3:
                if (mediaList.size() > 0) {
                    ShowMenuRemove();
                } else {
                    System.out.println("Number of entries in " + mediaType + " is 0. To add new, please select option 2.");
                    ShowMenuList();
                }
                break;
            case 4:
                ShowMenuMain();
                break;
        }
    }

    private void ShowMenuView() {
        System.out.print(mediaType + ". Number of entries: " + mediaList.size() + ". Available choices..." +
                "\n1. List all." +
                "\n2. Search for entry." +
                "\n3. Go Back." +
                "\nPlease select desired option: ");

        playerInput = GetPlayerInt(1, 3);
        Loader.NewLine();

        switch (playerInput) {
            case 1:
                PrintList(false);
                break;
            case 2:
                PrintList(true);
                break;
            case 3:
                break;
        }

        Loader.NewLine();
        ShowMenuList();
    }

    private void ShowMenuAdd() {
        String title;
        Category category = null;
        String stringValue;
        int intValue;

        int playerInput;

        System.out.print("Add to " + mediaType + ". Please type in the required information..." +
                "\nTitle: ");
        title = GetUserString();

        System.out.print("Category (1. Fantasy, 2. Horror, 3. Action, 4. Science Fiction, 5. Other): ");
        playerInput = GetPlayerInt(1, 5);

        switch (playerInput) {
            case 1:
                category = Category.FANTASY;
                break;
            case 2:
                category = Category.HORROR;
                break;
            case 3:
                category = Category.ACTION;
                break;
            case 4:
                category = Category.SCIENCE_FICTION;
                break;
            case 5:
                category = Category.OTHER;
                break;
        }

        if (mediaType == MediaType.BOOK) {
            System.out.print("Author: ");
            stringValue = GetUserString();

            System.out.print("Pages: ");
            intValue = GetPlayerInt(1, 2147483646);

            mediaList.add(new Book(title, category, stringValue, intValue));
        } else if (mediaType == MediaType.VIDEO) {
            System.out.print("Director: ");
            stringValue = GetUserString();

            System.out.print("Duration (minutes): ");
            intValue = GetPlayerInt(1, 2147483646);

            mediaList.add(new Video(title, category, stringValue, intValue));
        } else if (mediaType == MediaType.GAME) {
            System.out.print("Developer: ");
            stringValue = GetUserString();

            System.out.print("Metascore: ");
            intValue = GetPlayerInt(1, 100);

            mediaList.add(new Game(title, category, stringValue, intValue));
        }

        System.out.println(mediaType + " " + title + " added.");
        Loader.NewLine();

        WriteList();
        ShowMenuList();
    }

    private void ShowMenuRemove() {
        int listSize = mediaList.size();

        System.out.print(mediaType + ". Number of entries: " + listSize + ". Available choices..." +
                "\n1. Remove by index." +
                "\n2. Remove all." +
                "\n3. Go Back." +
                "\nPlease select desired option: ");

        playerInput = GetPlayerInt(1, 3);

        switch (playerInput) {
            case 1:
                System.out.print("Please type in the index from 1 - " + listSize + " to remove (0 to cancel): ");
                playerInput = GetPlayerInt(0, listSize);

                if (playerInput != 0)  {
                    mediaList.remove(playerInput - 1);
                    System.out.println("Entry with index " + playerInput + " removed...");
                }
                break;
            case 2:
                System.out.print("WARNING! You are about to delete all entries in " + mediaType + ". Type 1 to continue and 0 to cancel: ");
                playerInput = GetPlayerInt(0, 1);

                if (playerInput == 1) {
                    for (int i = 0; i < listSize; i++) {
                        mediaList.remove(0);
                    }
                    System.out.println("All entries from " + mediaType + " deleted.");
                }
                break;
            case 3:
                break;
        }

        WriteList();
        ShowMenuList();
    }

    private void LoadList() {
        //Media
        String title = "";
        Category category = null;

        //Subclass
        String stringValue = "";
        int intValue = 0;

        try {
            File listFile = new File(mediaType.toString().toLowerCase() + ".txt");

            Scanner readFile = new Scanner(listFile);

            while (readFile.hasNextLine()) {
                title = readFile.nextLine();
                category = Category.valueOf(readFile.nextLine());

                stringValue = readFile.nextLine();
                intValue = Integer.parseInt(readFile.nextLine());

                if (mediaType == MediaType.BOOK) {
                    mediaList.add(new Book(title, category, stringValue, intValue));
                } else if (mediaType == MediaType.VIDEO) {
                    mediaList.add(new Video(title, category, stringValue, intValue));
                } else if (mediaType == MediaType.GAME) {
                    mediaList.add(new Game(title, category, stringValue, intValue));
                }
            }
        } catch (FileNotFoundException error) {
            System.out.println("Error occurred. Please reload and try again...");
            error.printStackTrace();
            System.exit(0);
        }
    }

    private void PrintList(boolean search) {
        ArrayList<Media> outputList = new ArrayList<>();
        String playerInput = "";
        String listOutput = "";
        int counter = 0;
        double calculator;

        if (search) {
            System.out.print("Please type in search keyword(s): ");
            playerInput = GetUserString();

            try {
                Loader.NewLine();

                File listFile = new File(mediaType.toString().toLowerCase() + ".txt");
                Scanner readFile = new Scanner(listFile);

                while (readFile.hasNextLine()) {
                    listOutput = readFile.nextLine();
                    counter++;

                    if (listOutput.indexOf(playerInput) != -1) {
                        calculator = Math.ceil(counter / 4.0) - 1;
                        outputList.add(mediaList.get((int)calculator));
                    }
                }

            } catch (FileNotFoundException error) {
                System.out.println("Error occurred. Please reload and try again...");
                error.printStackTrace();
                System.exit(0);
            }
        } else {
            outputList = mediaList;
        }

        for (int i = 0; i < outputList.size(); i++) {
            System.out.println("--- " + outputList.get(i).getTitle() + " [" + outputList.get(i).getCategory() + "]");

            if (mediaType == MediaType.BOOK) {
                Book book = (Book)outputList.get(i);
                System.out.println("- Author: " + book.getAuthor() + ". Pages: " + book.getPages());
            } else if (mediaType == MediaType.VIDEO) {
                Video video = (Video)outputList.get(i);
                System.out.println("- Director: " + video.getDirector() + ". Duration: " + video.getDuration());
            } else if (mediaType == MediaType.GAME) {
                Game game = (Game)outputList.get(i);
                System.out.println("- Developer: " + game.getDeveloper() + ". Metascore: " + game.getMetascore());
            }

            Loader.NewLine();
        }
        System.out.print("Click any key to continue...");
        GetUserString();
    }

    private void WriteList() {
        try {
            FileWriter writeFile = new FileWriter(mediaType.toString().toLowerCase() + ".txt");

            for (int i = 0; i < mediaList.size(); i++) {
                if (i != 0) {
                    writeFile.write("\n");
                }
                writeFile.write(mediaList.get(i).getTitle());
                writeFile.write("\n" + mediaList.get(i).getCategory().toString());

                if (mediaType == MediaType.BOOK) {
                    Book book = (Book)mediaList.get(i);
                    writeFile.write("\n" + book.getAuthor());
                    writeFile.write("\n" + book.getPages());
                } else if (mediaType == MediaType.VIDEO) {
                    Video video = (Video)mediaList.get(i);
                    writeFile.write("\n" + video.getDirector());
                    writeFile.write("\n" + video.getDuration());
                } else {
                    Game game = (Game)mediaList.get(i);
                    writeFile.write("\n" + game.getDeveloper());
                    writeFile.write("\n" + game.getMetascore());
                }
            }

            writeFile.close();
        } catch (IOException error) {
            System.out.println("Error occurred. Please reload and try again...");
            error.printStackTrace();
            System.exit(0);
        }
    }

    private String GetUserString() {
        return new Scanner(System.in).nextLine();
    }

    public int GetPlayerInt(int min, int max) {
        //Expected output will never be below 0 -- min will/must always be >= 0 in the menu options...
        int output = -1;
        int tmpValue = 0;
        Scanner input;

        while (output == -1) {
            input = new Scanner(System.in);

            if (input.hasNextInt()) {
                tmpValue = input.nextInt();
            }

            if (tmpValue >= min && tmpValue <= max) {
                output = tmpValue;
            } else {
                System.out.print("Please type in a valid number between " + min + " - " + max + ": ");
            }
        }

        return output;
    }
}

class Media {
    private String title;
    private Category category;

    public String getTitle() {
        return title;
    }

    public Category getCategory() {
        return category;
    }

    public Media(String title, Category category) {
        this.title = title;
        this.category = category;
    }
}

class Book extends Media {
    private String author;
    private int pages;

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    public Book(String title, Category category, String author, int pages) {
        super(title, category);
        this.author = author;
        this.pages = pages;
    }
}

class Video extends Media {
    private String director;
    private int duration;

    public String getDirector() {
        return director;
    }

    public int getDuration() {
        return duration;
    }

    public Video(String title, Category category, String director, int duration) {
        super(title, category);
        this.director = director;
        this.duration = duration;
    }
}

class Game extends Media {
    private String developer;
    private int metascore;

    public String getDeveloper() {
        return developer;
    }

    public int getMetascore() {
        return metascore;
    }

    public Game(String title, Category category, String developer, int metascore) {
        super(title, category);
        this.developer = developer;
        this.metascore = metascore;
    }
}*/