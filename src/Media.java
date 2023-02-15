public abstract class Media {
    private final int ID;
    private final String TITLE;
    private final String PERSON_TITLE;
    private final int PERSON;
    private final Category CATEGORY;
    private final Type TYPE;
    private final String INT_TITLE;

    public int getID() {
        return ID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public String getPERSON_TITLE() {
        return PERSON_TITLE;
    }

    public String getINT_TITLE() {
        return INT_TITLE;
    }

    public int getPERSON() {
        return PERSON;
    }

    public Category getCATEGORY() {
        return CATEGORY;
    }

    public Type getTYPE() {
        return TYPE;
    }

    public Media(Type type, int id, String title, String personTitle, String intTitle, int person, Category category) {
        this.PERSON_TITLE = personTitle;
        this.INT_TITLE = intTitle;
        this.PERSON = person;
        this.TYPE = type;
        this.ID = id;
        this.TITLE = title;
        this.CATEGORY = category;
    }
}
