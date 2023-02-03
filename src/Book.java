public class Book extends Media {
    private final String author;
    private final int pages;

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
