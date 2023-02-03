public abstract class Media {
    private final String title;
    private final Category category;

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
