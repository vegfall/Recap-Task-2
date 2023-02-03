public class Video extends Media {
    private String director;
    private int duration;
    public Video(String title, Category category, String director, int duration) {
        super(title, category);
        this.director = director;
        this.duration = duration;
    }
}
