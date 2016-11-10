package playlists;
import java.util.Objects;

/**
 * A simple class to represent a song included in a playlist. It only has two
 * fields: an artist and a title.
 * 
 * @author Mary Ellen Foster
 */
public final class Song {

	/** The song artist */
	private final String artist;
	/** The song title */
	private final String title;

	/**
	 * Creates a new Song with the given artist and title.
	 * 
	 * @param artist
	 *            The song artist
	 * @param title
	 *            The song title
	 */
	public Song(String artist, String title) {
		this.artist = artist;
		this.title = title;
	}

	// Getters and toString()
	public String getArtist() {
		return artist;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return "\"" + title + "\" by " + artist;
	}


	/**
	 * Two songs are equal if they have the same title and artist.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Song) {
			Song s = (Song) obj;
			return Objects.equals(s.artist, this.artist) && Objects.equals(s.title, this.title);
		}
		return false;
	}

	/**
	 * It is good practice to override hashCode() whenever you override equals()
	 * -- more on this in upcoming lectures.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(artist, title);
	}

}
