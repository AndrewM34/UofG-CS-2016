package musiclibrary;

import java.util.Objects;

/**
 * Defines the properties of an album: title, year, and artist.
 * 
 * @author mefoster
 */
public class Album {

	/** The artist */
	private String artist;
	/** The title */
	private String title;
	/** The year */
	private int year;

	/**
	 * Creates a new album with the given properties.
	 * 
	 * @param artist
	 *            the artist to use
	 * @param title
	 *            the title to use
	 * @param year
	 *            the year to use
	 */
	public Album(String artist, String title, int year) {
		this.artist = artist;
		this.title = title;
		this.year = year;
	}

	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(artist, title, year);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Album) {
			Album album = (Album) obj;
			return Objects.equals(album.artist, this.artist) && Objects.equals(album.title, this.title)
					&& album.year == this.year;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return artist + ": " + title + " (" + year + ")";
	}
}
