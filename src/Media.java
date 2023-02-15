public abstract class Media {
    private final int id;
    private final String title;
    private final String personTitle;
    private final int person;
    private final Category category;
    private final Type type;
    private final String intTitle;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPersonTitle() {
        return personTitle;
    }

    public String getIntTitle() {
        return intTitle;
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

    public Media(Type type, int id, String title, String personTitle, String intTitle, int person, Category category) {
        this.personTitle = personTitle;
        this.intTitle = intTitle;
        this.person = person;
        this.type = type;
        this.id = id;
        this.title = title;
        this.category = category;
    }
}
