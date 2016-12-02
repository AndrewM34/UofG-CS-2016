package musiclibrary;

/**
 * Created by andrew on 20/11/16.
 */
public class SongImpl implements Song {
    private String artist;
    private String albumArtist; // only used if different from artist
    private String title;
    private String albumTitle;
    private int year;
    private int rating;
    private int playCount;

    // constructor
    public SongImpl(String artist, String title, int year, String albumArtist, String albumTitle) {
        this.artist = artist;
        this.title = title;
        this.year = year;
        this.albumArtist = albumArtist;
        this.albumTitle = albumTitle;
        this.playCount = 0;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getTitle() {
        return this.title;
    }

    /**
     * @return if this song has a separate album artist
     */
    public boolean hasAlbumArtist() {
        return !(this.artist.equals(this.albumArtist));
    }

    public Album getAlbum() {
        String tempArtist = (albumArtist == null) ? artist : albumArtist; // if no album artist use song artist
        return new Album(tempArtist, albumTitle, year);
    }

    public int getPlayCount() {
        return this.playCount;
    }

    public void increasePlayCount() {
        this.playCount++;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) throws IllegalArgumentException {

        if ((rating < 0) || (rating > 5)) {
            throw new IllegalArgumentException("Rating out of bounds");
        }
        this.rating = rating;
//
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//        }
    }

    public String toString() {
        return title+"("+artist+")";
    }
}
