public class Video extends Media {
    private final String director;
    private final int duration;

    public String getDirector() {
        return director;
    }

    public int getDuration() {
        return duration;
    }

    public Video(String title, Category category, String director, int duration) {
        super(title, category);
        this.director = director;
        this.duration = duration;
    }
}
