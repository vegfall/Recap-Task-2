public class Video extends Media {
    private final int DURATION;

    public int getDURATION() {
        return DURATION;
    }

    public Video(int id, String title, int director, Category category, int duration) {
        super(Type.VIDEO, id, title, "Director", "Duration", director, category);
        this.DURATION = duration;
    }
}