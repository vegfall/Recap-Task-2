public abstract class Media {
    private final int id;
    private final String title;
    private final Category category;
    private final Type type;

    public Media(Type type, int id, String title, Category category) {
        this.type = type;
        this.id = id;
        this.title = title;
        this.category = category;
    }
}
