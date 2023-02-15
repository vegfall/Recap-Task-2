public class Game extends Media {
    private final int METASCORE;

    public int getMETASCORE() {
        return METASCORE;
    }

    public Game(int id, String title, int developer, Category category, int metascore) {
        super(Type.GAME, id, title, "Developer", "Metascore", developer, category);
        this.METASCORE = metascore;
    }
}