package gd.model;

import gd.enums.DemonDifficulty;
import gd.enums.Difficulty;

/**
 * Represents one level in Geometry Dash. All statistics and attributes of a Geometry
 * Dash level are defined here (name, creator, difficulty, stars, length, etc)
 *
 * @author Alex1304 (almost everything there)
 * @author DoubleCookies (different toString methods)
 */
public class GDLevel {

    /**
     * Constructs an instance of gd.model.GDLevel by providing all of its attributes at
     * once.
     *
     * @param id              - the ID of the level
     * @param name            - the name of the level
     * @param creator         - the name of the user who created this level
     * @param difficulty      - the level difficulty
     * @param demonDifficulty - if it's a Demon, the type of Demon difficulty
     * @param stars           - the number of stars assigned to the level
     * @param featuredScore   - the featured score of the level, or a value &lt;= 0 if not
     *                        featured
     * @param epic            - whether the level is marked as Epic
     * @param downloads       - amount of downloads for the level
     * @param likes           - amount of likes for the level
     * @param description     - level description
     * @param gdSong          - level music info
     * @throws IllegalArgumentException if the argument {@code pass} &lt; -2
     */
    public GDLevel(long id, String name, String creator, Difficulty difficulty,
                   DemonDifficulty demonDifficulty, short stars, int featuredScore, boolean epic, long downloads,
                   long likes, String description, GDSong gdSong) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.difficulty = difficulty;
        this.demonDifficulty = demonDifficulty;
        this.stars = stars;
        this.featuredScore = featuredScore;
        this.epic = epic;
        this.downloads = downloads;
        this.likes = likes;
        this.description = description;
        this.gdSong = gdSong;
    }

    private long id;
    private String name;
    private String creator;
    private Difficulty difficulty;
    private DemonDifficulty demonDifficulty;
    private short stars;
    private int featuredScore;
    private boolean epic;
    private long downloads;
    private long likes;
    private String description;
    private GDSong gdSong;

    /**
     * Gets the ID of the level
     *
     * @return long
     */
    public long getId() {
        return id;
    }

    /**
     * Gets the name of the level
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the name of the creator who created this level
     *
     * @return String
     */
    public String getCreator() {
        return creator;
    }


    /**
     * Gets the level difficulty
     *
     * @return gd.enums.Difficulty
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Gets the type of Demon difficulty
     *
     * @return gd.enums.DemonDifficulty
     */
    public DemonDifficulty getDemonDifficulty() {
        return demonDifficulty;
    }

    /**
     * Gets the number of stars assigned to the level
     *
     * @return short
     */
    public short getStars() {
        return stars;
    }

    /**
     * Gets the featured score of the level, or a value &lt;= 0 if not featured
     *
     * @return int
     */
    public int getFeaturedScore() {
        return featuredScore;
    }

    /**
     * Whether the level is marked as Epic
     *
     * @return boolean
     */
    public boolean isEpic() {
        return epic;
    }

    /**
     * Gets the amount of downloads for the level
     *
     * @return long
     */
    public long getDownloads() {
        return downloads;
    }

    /**
     * Gets the amount of likes for the level
     *
     * @return long
     */
    public long getLikes() {
        return likes;
    }


    /**
     * Whether the level is featured.
     *
     * @return boolean
     */
    public boolean isFeatured() {
        return featuredScore > 0;
    }

    /**
     * Whether the level is Awarded, i.e the amount of stars is greater than 0.
     *
     * @return boolean
     */
    public boolean isAwarded() {
        return stars > 0;
    }

    /**
     * Gets the level description
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the level song
     *
     * @return gd.model.GDSong
     */
    public GDSong getGdSong() {
        return gdSong;
    }

    /**
     * Default overrided toString() method
     *
     * @return string with name, creator, id, amount of likes and downloads
     */
    @Override
    public String toString() {
        return "\"" + name + "\" by " + creator + " (" + id + ") — likes: " + likes + ", downloads: " + downloads;
    }

    /**
     * Create string for markdown tables
     *
     * @return string with name, creator, id, amount of likes and downloads
     */
    public String markdownString() {
        return "| " + name + " | " + creator + " | " + id + " | " + downloads + " | " + likes;
    }

    /**
     * Create string for markdown tables about level descriptions
     *
     * @return string with name, creator, id, description length and description itself
     */
    public String markdownWithDescriptionString() {
        String replacedDescription = description.replace("|", "&#124;");
        return "| " + name + " | " + creator + " | " + id + " | " + description.length() + " | " + replacedDescription;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    /**
     * Two levels are considered as equal if and only if they have both
     * the same ID
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GDLevel other = (GDLevel) obj;
        return id == other.id;
    }

}
