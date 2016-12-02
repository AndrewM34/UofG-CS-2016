package musicplayer;

import java.awt.Font;
import java.awt.FontMetrics;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.UIManager;

/**
 * A collection of static methods to make my Swing application work more nicely
 * on high-DPI displays.
 * 
 * @author mefoster
 */
public class Utils {

	/**
	 * Increases the size of all fonts for all components to the given size.
	 * Based on http://stackoverflow.com/a/35029265
	 * 
	 * @param size
	 *            The font size to use
	 */
	public static void setDefaultFontSize(int size) {
		Set<Object> keySet = UIManager.getLookAndFeelDefaults().keySet();
		for (Object key : keySet) {
			if (key != null && key.toString().toLowerCase().contains("font")) {
				Font font = UIManager.getDefaults().getFont(key);
				if (font != null) {
					font = font.deriveFont((float) size);
					UIManager.put(key, font);
				}
			}
		}
	}

	/**
	 * Hack to get table row height set up properly on high-DPI Windows
	 * machines. It determines the height of the font used in a table row and
	 * sets the row height to be that value + 10.
	 * 
	 * @param table
	 *            The table to update
	 * @see https://docs.oracle.com/javase/tutorial/2d/text/measuringtext.html
	 */
	public static void fixTableRowHeight(JTable table) {
		// Get metrics from the graphics
		FontMetrics metrics = table.getGraphics().getFontMetrics(table.getFont());
		// get the height of a line of text in this
		// font and render context, and use it to set row height
		table.setRowHeight(metrics.getHeight() + 10);
	}

}
