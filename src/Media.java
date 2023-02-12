public abstract class Media {
    private final int id;
    private final String title;
    private final int person;
    private final Category category;
    private final Type type;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public int getPerson() {
        return person;
    }

    public Category getCategory() {
        return category;
    }

    public Type getType() {
        return type;
    }

    public Media(Type type, int id, String title, int person, Category category) {
        this.person = person;
        this.type = type;
        this.id = id;
        this.title = title;
        this.category = category;
    }
}
