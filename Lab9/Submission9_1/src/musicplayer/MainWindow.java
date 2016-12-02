package musicplayer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.JOptionPane;

import musiclibrary.MusicLibrary;
import musiclibrary.Song;

/**
 * The main window of a music player application. The buttons at the top allow
 * songs to be added to the playlist from the music library, either a song at a
 * time or an album at a time. They also let the playlist be shuffled, cleared,
 * or "played".
 * 
 * The play count of a song is increased every time it is played; users can also
 * rate any song in the list by double clicking on it.
 * 
 * @author mefoster
 */
// Disable an Eclipse warning that is not relevant here
@SuppressWarnings("serial")
public class MainWindow extends JFrame implements ActionListener {

	// The buttons to display across the top
	private JButton playButton;
	private JButton clearButton;
	private JButton shuffleButton;
	private JButton chooseButton;
	private JButton albumButton;

	// The full music library and the GUI components for managing the current
	// playlist
	private MusicLibrary musicLibrary;
	private SongTableModel songModel;
	private JTable songTable;

	/**
	 * Creates and displays a new MainWindow. The program exits when the window
	 * is closed.
	 * 
	 * @param musicLibrary
	 */
	public MainWindow(MusicLibrary musicLibrary) {
		// Basic window features
		super("Playlist");
		setLocationByPlatform(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Scale everything larger
		Utils.setDefaultFontSize(30);

		// Store the music library for use throughout the program
		this.musicLibrary = musicLibrary;

		// Initialise an empty table model, a JTable to display it, and a scroll
		// pane to contain the table
		songModel = new SongTableModel();
		songTable = new JTable(songModel);
		songTable.setAutoCreateRowSorter(true);

		// Try to get vaguely sane column widths
		songTable.getColumnModel().getColumn(0).setPreferredWidth(400);
		songTable.getColumnModel().getColumn(0).setMinWidth(300);
		songTable.getColumnModel().getColumn(1).setPreferredWidth(300);
		songTable.getColumnModel().getColumn(1).setMinWidth(300);
		songTable.getColumnModel().getColumn(2).setPreferredWidth(25);
		songTable.getColumnModel().getColumn(3).setPreferredWidth(25);

		// Scroll pane -- scrollbar and title
		JScrollPane songScroll = new JScrollPane(songTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		songScroll.setBorder(new TitledBorder("Playlist"));

		// Respond to double clicks on the table
		MouseAdapter mouseListener = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if (event.getClickCount() == 2) {
					int row = songTable.getSelectedRow();
					Song song = songModel.getSong(row);
					new SongRatingDialog(MainWindow.this, song).setVisible(true);
				}
			}
		};
		songTable.addMouseListener(mouseListener);

		// Initialise all buttons and add the current class as an action
		// listener to all
		playButton = new JButton("Play");
		playButton.addActionListener(this);
		shuffleButton = new JButton("Shuffle playlist");
		shuffleButton.addActionListener(this);
		clearButton = new JButton("Clear playlist");
		clearButton.addActionListener(this);
		chooseButton = new JButton("Add songs from library ...");
		chooseButton.addActionListener(this);
		albumButton = new JButton("List albums ...");
		albumButton.addActionListener(this);

		// Lay out the buttons in a line
		Box topBox = Box.createHorizontalBox();
		topBox.add(playButton);
		topBox.add(Box.createHorizontalStrut(100));
		topBox.add(shuffleButton);
		topBox.add(clearButton);
		topBox.add(Box.createHorizontalStrut(100));
		topBox.add(chooseButton);
		topBox.add(albumButton);

		// Lay out the window
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(topBox, BorderLayout.NORTH);
		getContentPane().add(songScroll, BorderLayout.CENTER);

		pack();

		// Fix the table cells
		Utils.fixTableRowHeight(songTable);
	}

	/**
	 * Responds to a click on any of the buttons on the window.
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// Determine which button was pushed
		Object source = event.getSource();

		// Here's what to do with the clear and shuffle buttons
		if (source == clearButton) {
			// added functionality: confirmation dialog
			// draw the JOptionPane
			JOptionPane optionPane = new JOptionPane();

			// draw a confirmDialog, if yes clicked then clear songModel
			if (optionPane.showConfirmDialog(this, "Would you like to clear the playlist?") == 0) {
				songModel.clear();
			}
			// optionPane closes itself when done
		} else if (source == shuffleButton) {
			// Turn off any sorting before shuffling the songs or else it is
			// confusing
			songTable.getRowSorter().setSortKeys(null);
			songModel.shuffle();

		} else if (source == chooseButton) { // add songs from library
			// create a new instance of ChooseSongDialog and set visible
			new ChooseSongDialog(this, musicLibrary).setVisible(true);

		} else if (source == albumButton) {
			// create a new instance of ChooseAlbumDialog and set visible
			new ChooseAlbumDialog(this, musicLibrary).setVisible(true);

		} else if (source == playButton) {
			// get the selected row
			int row = songTable.getSelectedRow();
			System.out.println(row);
			if (row == -1) {
				row = 0;
			}

			// now play all songs from the selected row down
			for (int i=row; i<(songModel.getRowCount()); i++) {
				songModel.getSong(i).increasePlayCount();
			}

			// update display
			songModel.fireTableDataChanged();
		}
	}

	/**
	 * Returns the underlying model of the playlist table so that dialogue boxes
	 * can update it as necessary.
	 * 
	 * @return The song model
	 */
	public SongTableModel getSongModel() {
		return songModel;
	}
}
