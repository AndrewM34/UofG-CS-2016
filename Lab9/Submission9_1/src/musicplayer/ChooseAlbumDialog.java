package musicplayer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import musiclibrary.MusicLibrary;
import musiclibrary.Song;

/**
 * A dialogue box that lists all albums in the music library and lets the user
 * choose one or more of them to load into the playlist.
 * 
 * @author mefoster
 */
@SuppressWarnings("serial")
public class ChooseAlbumDialog extends JDialog implements ActionListener {

	// The table displaying the album list, and the underlying model of the data
	// in the table
	private JTable albumTable;
	private AlbumTableModel albumModel;

	// Buttons
	private JButton okButton;
	private JButton cancelButton;

	// The main window and its music library
	private MainWindow mainWindow;
	private MusicLibrary musicLibrary;

	/**
	 * Creates and lays out the dialogue box.
	 * 
	 * @param mainWindow
	 * @param musicLibrary
	 */
	public ChooseAlbumDialog(MainWindow mainWindow, MusicLibrary musicLibrary) {
		// Set up the window
		super(mainWindow, "Choose album", true);
		setLocationRelativeTo(mainWindow);

		// Store properties for use in action listener
		this.mainWindow = mainWindow;
		this.musicLibrary = musicLibrary;

		// Create the table of albums
		albumModel = new AlbumTableModel(musicLibrary);
		albumTable = new JTable(albumModel);
		albumTable.setAutoCreateRowSorter(true);

		// Buttons at the bottom
		okButton = new JButton("Add selected album(s) to playlist");
		okButton.addActionListener(this);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);

		// Lay out the window
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(new JScrollPane(albumTable), BorderLayout.CENTER);

		Box bottomBox = Box.createHorizontalBox();
		bottomBox.add(Box.createHorizontalGlue());
		bottomBox.add(okButton);
		bottomBox.add(Box.createHorizontalGlue());
		bottomBox.add(cancelButton);
		bottomBox.add(Box.createHorizontalGlue());

		getContentPane().add(bottomBox, BorderLayout.SOUTH);

		pack();
		Utils.fixTableRowHeight(albumTable);
	}

	/*
	 * Responds to a click on one of the buttons.
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();

		int[] selectedRows = albumTable.getSelectedRows();

		if (source == cancelButton) {
			// Just close the window
			dispose();
		} else if (source == okButton) {
			List<Song> songList = new ArrayList<>();
			for (int i : selectedRows) {
				songList.addAll(musicLibrary.getAlbumSongs(albumModel.getAlbum(i)));
			}
			mainWindow.getSongModel().addSongs(songList);
			dispose(); // close window
		}
	}

}
