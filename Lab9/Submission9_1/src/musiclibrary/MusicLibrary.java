package musiclibrary;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a music library -- i.e., a collection of songs. The
 * MusicLibrary provides methods for saving a library to a file on disc and
 * loading from disc and for listing the songs and albums in the library.
 * 
 * @author mefoster
 */
public class MusicLibrary {
	/** The list of songs */
	private List<Song> songs = new ArrayList<>();

	/**
	 * @return the complete list of songs
	 */
	public List<Song> getAllSongs() {
		return songs;
	}

	/**
	 * Returns all of the albums in the library -- i.e., every album that is
	 * associated with at least one song in the library. Any album should only
	 * appear on the list once.
	 * 
	 * @return The list of albums
	 */
	public List<Album> getAlbums() {
		List<Album> albums = this.getAllSongs().stream().map(s -> s.getAlbum()).distinct().collect(Collectors.toList());
		return albums;
	}

	/**
	 * Returns all songs in the library from the given album.
	 * 
	 * @param album
	 *            The album details to use
	 * @return The list of songs on that album
	 */
	public List<Song> getAlbumSongs(Album album) {
		List<Song> albumSongs = new ArrayList<>();
		for (Song s : songs) {
			if (s.getAlbum().equals(album)) {
				albumSongs.add(s);
			}
		}

		return albumSongs;
	}

	/**
	 * Saves a music library to the given filename.
	 * 
	 * @param filename
	 *            The filename to use
	 * @throws IOException
	 *             If there is an error saving to the given file
	 */
	public void save(String filename) throws IOException {
		PrintStream pw = new PrintStream(new FileOutputStream(filename), true, "UTF-8");
		for (Song song : songs) {
			pw.println(song.getArtist());
			pw.println(song.getTitle());
			pw.println(song.getAlbum().getYear());
			pw.println(song.getAlbum().getTitle());
			if (song.hasAlbumArtist()) {
				pw.println(song.getAlbum().getArtist());
			} else {
				pw.println();
			}
		}
		pw.flush();
		pw.close();
	}

	/**
	 * Loads a music library from the given filename.
	 * 
	 * @param filename
	 *            The filename to load from
	 * @throws IOException
	 *             If there is an error loading from the file
	 */
	public void load(String filename) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(filename));
		songs.clear();
		String artist = null, title = null, albumTitle = null, albumArtist = null;
		int year = 0;
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i).trim();
			switch (i % 5) {
			case 0:
				artist = line;
				break;
			case 1:
				title = line;
				break;
			case 2:
				year = Integer.parseInt(line);
				break;
			case 3:
				albumTitle = line;
				break;
			case 4:
				albumArtist = line.isEmpty() ? null : line;
				songs.add(Song.createSong(artist, title, year, albumArtist, albumTitle));
				break;
			}
		}
	}

}
