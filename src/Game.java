public class Game extends Media {
    private String developer;
    private int metascore;

    public Game(String title, Category category, String developer, int metascore) {
        super(title, category);
        this.developer = developer;
        this.metascore = metascore;
    }

}
