public class Game extends Media {
    private final int metascore;

    public int getMetascore() {
        return metascore;
    }

    public Game(int id, String title, int developer, Category category, int metascore) {
        super(Type.GAME, id, title, "Developer", "Metascore", developer, category);
        this.metascore = metascore;
    }
}