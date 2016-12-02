package musiclibrary;

/**
 * An interface representing a single song in a music library. A song has the
 * following properties:
 * 
 * <ul>
 * <li>A song artist</li>
 * <li>A song title</li>
 * <li>An album title</li>
 * <li>An album year</li>
 * <li>An album artist -- optional, only if the album artist is different than
 * the song artist (e.g., for guest appearances or compilation albums. This
 * field should be set to null if there is no separate album artist.</li>
 * </ul>
 * 
 * @see Album
 * @see MusicLibrary
 * 
 * @author mefoster
 */
public interface Song {

	/**
	 * @return the song artist
	 */
	public String getArtist();

	/**
	 * @return the song title
	 */
	public String getTitle();

	/**
	 * @return whether this song has a separate album artist
	 */
	public boolean hasAlbumArtist();

	/**
	 * Returns the album details for this song: album title, year, and album
	 * artist. If no separate album artist is defined for the song, the album
	 * artist should be identical to the song artist.
	 * 
	 * @return the album details for this song
	 */
	public Album getAlbum();

	/**
	 * @return the number of times this song has been played
	 */
	public int getPlayCount();

	/**
	 * Increases the play count of this song by 1.
	 */
	public void increasePlayCount();

	/**
	 * @return the rating of this song -- a number between 0 and 5
	 */
	public int getRating();

	/**
	 * Sets the rating of this song.
	 * 
	 * @param rating
	 *            the rating to set -- must be between 0 and 5
	 * @throws IllegalArgumentException
	 *             if the new rating value is out of range
	 */
	public void setRating(int rating) throws IllegalArgumentException;

	/**
	 * Creates and returns a new Song instance with the given parameters. The
	 * newly created song should have a play count and a rating of zero.
	 * 
	 * @param artist
	 *            the artist
	 * @param title
	 *            the title
	 * @param year
	 *            the year
	 * @param albumArtist
	 *            the album artist -- will be null if there is no separate album
	 *            artist
	 * @param albumTitle
	 *            the album title
	 * @return a newly created Song with the given properties
	 */
	public static Song createSong(String artist, String title, int year, String albumArtist, String albumTitle) {
		return new SongImpl(artist, title, year, albumArtist, albumTitle);
	}

}
