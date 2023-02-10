public class Game extends Media {
    private final String developer;
    private final int metascore;

    public String getDeveloper() {
        return developer;
    }

    public int getMetascore() {
        return metascore;
    }

    public Game(int id, String title, Category category, String developer, int metascore) {
        super(Type.GAME, id, title, category);
        this.developer = developer;
        this.metascore = metascore;
    }
}