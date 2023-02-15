public class Video extends Media {
    private final int duration;

    public int getDuration() {
        return duration;
    }

    public Video(int id, String title, int director, Category category, int duration) {
        super(Type.VIDEO, id, title, "Director", "Duration", director, category);
        this.duration = duration;
    }
}