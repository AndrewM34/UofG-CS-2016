package musicplayer;

import java.io.IOException;
import java.util.List;

import musiclibrary.Album;
import musiclibrary.MusicLibrary;

public class Main {

	/**
	 * Main method: loads a music library from the given file, or from a default
	 * file if no filename is specified, and then creates and displays a
	 * top-level window.
	 * 
	 * @param args
	 *            Command-line arguments -- if an argument is present, it is
	 *            used as the source for loading the music library
	 */
	public static void main(String... args) throws IOException {
		// Load the library
		MusicLibrary ml = new MusicLibrary();
		if (args.length > 0) {
			ml.load(args[0]);
		} else {
			ml.load("songs.txt");
		}

		// Create and display the main window
		MainWindow mainWindow = new MainWindow(ml);
		mainWindow.setVisible(true);
	}

}
