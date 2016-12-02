package musicplayer;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import musiclibrary.Album;
import musiclibrary.MusicLibrary;

/**
 * A table model for a list of albums.
 * 
 * @author mefoster
 * @see ChooseAlbumDialog
 */
@SuppressWarnings("serial")
public class AlbumTableModel extends AbstractTableModel {

	// The list of albums
	private List<Album> albums;

	/**
	 * Creates a new AlbumTableModel by loading the albums from the given
	 * library.
	 * 
	 * @param ml
	 *            The music library to use
	 */
	public AlbumTableModel(MusicLibrary ml) {
		this.albums = ml.getAlbums();
	}

	/**
	 * Returns the album at the given position.
	 * 
	 * @param i
	 *            The position to use
	 * @return The album at that position
	 * @throws IndexOutOfBoundsException
	 *             if the index is less than 0 or greater than the number of
	 *             albums in the list
	 */
	public Album getAlbum(int i) throws IndexOutOfBoundsException {
		return albums.get(i);
	}

	// Override some methods to make the table structure work nicely
	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Artist";
		case 1:
			return "Title";
		case 2:
			return "Year";
		}
		throw new IllegalArgumentException();
	}

	@Override
	public int getRowCount() {
		return albums.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		Album a = albums.get(row);
		switch (column) {
		case 0:
			return a.getArtist();
		case 1:
			return a.getTitle();
		case 2:
			return a.getYear();
		}
		throw new IllegalArgumentException();
	}
}
