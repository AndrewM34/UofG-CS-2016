package musicplayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import musiclibrary.Song;

/**
 * A table model for a list of songs.
 * 
 * @author mefoster
 * @see ChooseSongDialog
 * @see MainWindow
 */
@SuppressWarnings("serial")
public class SongTableModel extends AbstractTableModel {

	// The list of songs
	private List<Song> songs = new ArrayList<>();

	/**
	 * Add a song to the end of the list, and notify any listeners that the data
	 * has changed.
	 * 
	 * @param s
	 *            The song to add
	 */
	public void addSong(Song s) {
		songs.add(s);
		fireTableDataChanged();
	}

	/**
	 * Add a list of songs to the end of the list, and notify any listeners that
	 * the data has changed.
	 * 
	 * @param newSongs
	 *            The songs to add
	 */
	public void addSongs(List<Song> newSongs) {
		songs.addAll(newSongs);
		fireTableDataChanged();
	}

	/**
	 * Return the song at position i in the list.
	 * 
	 * @param i
	 *            The index to use
	 * @return The song at position i
	 * @throws IllegalArgumentException
	 *             if the index is negative or too large
	 */
	public Song getSong(int i) throws IllegalArgumentException {
		return songs.get(i);
	}

	/**
	 * Remove all songs from the list and notify any listeners that the data has
	 * changed.
	 */
	public void clear() {
		songs.clear();
		fireTableDataChanged();
	}

	/**
	 * Shuffle the songs in the list and notify any listeners that the data has
	 * changed.
	 */
	public void shuffle() {
		Collections.shuffle(songs);
		fireTableDataChanged();
	}

	// Override some methods to make the table structure work nicely

	// Columns in the table
	private String[] columnNames = { "Artist", "Title", "Plays", "Rating" };

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public Class<?> getColumnClass(int column) {
		if (column == 2) {
			return Integer.class;
		} else {
			return String.class;
		}
	}

	@Override
	public int getRowCount() {
		return songs.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		Song s = songs.get(row);
		switch (column) {
		case 0:
			return s.getArtist();
		case 1:
			return s.getTitle();
		case 2:
			return s.getPlayCount();
		case 3:
			// http://stackoverflow.com/a/24946101
			return String.join("", Collections.nCopies(s.getRating(), "*"));
		}
		throw new IllegalArgumentException();
	}

}
