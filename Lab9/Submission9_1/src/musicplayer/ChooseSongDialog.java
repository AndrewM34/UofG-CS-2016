package musicplayer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import musiclibrary.MusicLibrary;
import musiclibrary.Song;

/**
 * A dialogue box that lists all songs in the music library and lets the user
 * choose one or more of them to load into the playlist.
 * 
 * @author mefoster
 */
@SuppressWarnings("serial")
public class ChooseSongDialog extends JDialog implements ActionListener {

	// The table displaying the album list, and the underlying model of the data
	// in the table
	private SongTableModel songModel;
	private JTable songTable;

	// Buttons
	private JButton okButton;
	private JButton cancelButton;

	// The main window and its music library
	private MainWindow mainWindow;

	/**
	 * Creates and lays out the dialogue box.
	 * 
	 * @param mainWindow
	 * @param musicLibrary
	 */
	public ChooseSongDialog(MainWindow mainWindow, MusicLibrary musicLibrary) {
		// Set up the window
		super(mainWindow, "Add songs to playlist", true);
		setLocationRelativeTo(mainWindow);

		// Store this for use in the action listener
		this.mainWindow = mainWindow;

		// Create a table of all the songs in the library
		// Initialise an empty table model, a JTable to display it, and a scroll
		// pane to contain the table
		songModel = new SongTableModel();
		songTable = new JTable(songModel);
		songTable.setAutoCreateRowSorter(true);

		// Hopefully get vaguely sane column widths
		songTable.getColumnModel().getColumn(0).setPreferredWidth(300);
		songTable.getColumnModel().getColumn(0).setMinWidth(300);
		songTable.getColumnModel().getColumn(1).setPreferredWidth(300);
		songTable.getColumnModel().getColumn(1).setMinWidth(300);
		songTable.getColumnModel().getColumn(2).setPreferredWidth(25);
		songTable.getColumnModel().getColumn(3).setPreferredWidth(25);
		JScrollPane songScroll = new JScrollPane(songTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		songScroll.setBorder(new TitledBorder("Music library"));
		songScroll.setPreferredSize(new Dimension(1000, 500));

		// Add all songs to the list
		songModel.addSongs(musicLibrary.getAllSongs());

		// Buttons at the bottom
		okButton = new JButton("Add selected songs to playlist");
		okButton.addActionListener(this);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);

		// Lay out the window
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(songScroll, BorderLayout.CENTER);

		Box bottomBox = Box.createHorizontalBox();
		bottomBox.add(Box.createHorizontalGlue());
		bottomBox.add(okButton);
		bottomBox.add(Box.createHorizontalGlue());
		bottomBox.add(cancelButton);
		bottomBox.add(Box.createHorizontalGlue());

		getContentPane().add(bottomBox, BorderLayout.SOUTH);

		pack();

		Utils.fixTableRowHeight(songTable);
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

		int[] selectedRows = songTable.getSelectedRows();
		
		if (source == cancelButton) {
			// Just close the window
			dispose();

		} else if (source == okButton) {
			List<Song> songList = new ArrayList<>();
			for (int i : selectedRows) {
				songList.add(songModel.getSong(i));
			}
			mainWindow.getSongModel().addSongs(songList); // append the songs to the playlist
			dispose(); // close the window
		}
	}
}
