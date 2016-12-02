package musicplayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;

import musiclibrary.Song;

/**
 * A simple dialogue box that lets a user change the rating of a single song.
 * 
 * @author mefoster
 */
@SuppressWarnings("serial")
public class SongRatingDialog extends JDialog implements ActionListener {

	// The UI element that specifies the rating
	private JSlider slider;

	// Buttons
	private JButton okButton, cancelButton;

	// Parent window and song to use
	private MainWindow mainWindow;
	private Song song;

	public SongRatingDialog(MainWindow mainWindow, Song song) {
		// Set up the window
		super(mainWindow, "Rate song", true);
		setLocationRelativeTo(mainWindow);

		// Store the properties for the action listener
		this.mainWindow = mainWindow;
		this.song = song;

		// Create the slider
		slider = new JSlider(0, 5, song.getRating());
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(1);
		slider.setPaintLabels(true);
		slider.setBorder(new TitledBorder("Song rating"));

		// Lay out the top part of the window -- include song details
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		getContentPane().add(new JLabel("Song: " + song.toString()));
		getContentPane().add(slider);

		// Buttons at the bottom
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);

		// Buttons at the bottom
		Box bottomBox = Box.createHorizontalBox();
		bottomBox.add(Box.createHorizontalGlue());
		bottomBox.add(okButton);
		bottomBox.add(Box.createHorizontalGlue());
		bottomBox.add(cancelButton);
		bottomBox.add(Box.createHorizontalGlue());

		getContentPane().add(bottomBox);

		pack();
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		
		if (source == okButton) {
			song.setRating(slider.getValue()); // set rating to slider value
			mainWindow.getSongModel().fireTableDataChanged(); // update listener

			System.out.printf("New rating = %d\n", slider.getValue());
			System.out.printf("song.getRating() = %d", song.getRating());

			dispose();
		} else if (source == cancelButton) {
			dispose();
		}
	}

}
