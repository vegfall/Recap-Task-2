public class Book extends Media {
    private String author;
    private int pages;

    public Book(String title, Category category, String author, int pages) {
        super(title, category);
        this.author = author;
        this.pages = pages;
    }

}
