public class Book extends Media {
    private final int PAGES;

    public int getPAGES() {
        return PAGES;
    }

    public Book(int id, String title, int author, Category category, int pages) {
        super(Type.BOOK, id, title, "Author", "Pages", author, category);
        this.PAGES = pages;
    }

}
