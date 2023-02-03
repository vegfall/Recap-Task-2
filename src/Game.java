public class Game extends Media {
    private final String developer;
    private final int metascore;

    public Game(String title, Category category, String developer, int metascore) {
        super(title, category);
        this.developer = developer;
        this.metascore = metascore;
    }
}