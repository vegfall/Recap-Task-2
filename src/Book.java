public class Book extends Media {
    private final String author;
    private final int pages;

    public Book(int id, String title, Category category, String author, int pages) {
        super(Type.BOOK, id, title, category);
        this.author = author;
        this.pages = pages;
    }

}
