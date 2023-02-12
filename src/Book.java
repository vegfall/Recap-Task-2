public class Book extends Media {
    private final int pages;

    public int getPages() {
        return pages;
    }

    public Book(int id, String title, int author, Category category, int pages) {
        super(Type.BOOK, id, title, author, category);
        this.pages = pages;
    }

}
