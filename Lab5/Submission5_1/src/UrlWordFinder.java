/**
 * Created by andrew on 23/10/16.
 */
public class UrlWordFinder extends WordFinder {
	// constructor
	public UrlWordFinder(String url) throws java.io.IOException {
		super(url);
		java.util.Scanner s = new java.util.Scanner(new java.net.URL(url).openStream());

		// while there are still lines, append the next line to contents
		while (s.hasNextLine()) {
			this.contents.add(s.nextLine());
		}

		s.close();
	}
}
