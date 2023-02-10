public class Game extends Media {
    private final String developer;
    private final int metascore;
    public Game(int id, String title, Category category, String developer, int metascore) {
        super(Type.GAME, id, title, category);
        this.developer = developer;
        this.metascore = metascore;
    }
}