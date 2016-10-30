import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class TestWordFinder {

	private static final String FILE_SOURCE = "dorian-gray.txt";
	private static final String URL_SOURCE = "http://www.dcs.gla.ac.uk/~mefoster/joose2/frankenstein.txt";

	@Test
	public void testCreateWordFinderFromValidUrl() throws IOException {
		WordFinder wf = WordFinder.createWordFinder(URL_SOURCE);
		Assert.assertEquals("Wrong class returned for createWordFinder with valid URL", UrlWordFinder.class,
				wf.getClass());
	}

	@Test
	public void testCreateWordFinderFromValidFile() throws IOException {
		WordFinder wf = WordFinder.createWordFinder(FILE_SOURCE);
		Assert.assertEquals("Wrong class returned for createWordFinder with valid file", FileWordFinder.class,
				wf.getClass());
	}

	@Test(expected = IOException.class)
	public void testCreateWordFinderFromInvalidUrl() throws IOException {
		WordFinder.createWordFinder(URL_SOURCE + ".zzz");
	}

	@Test(expected = IOException.class)
	public void testCreateWordFinderFromInvalidUrl2() throws IOException {
		WordFinder.createWordFinder(URL_SOURCE.replaceAll("uk", "zzz"));
	}

	@Test(expected = IOException.class)
	public void testCreateWordFinderFromInvalidFile() throws IOException {
		WordFinder.createWordFinder(FILE_SOURCE.replaceAll("a", "b"));
	}

	@Test
	public void testCount() throws IOException {
		WordFinder wf = WordFinder.createWordFinder(URL_SOURCE);
		Assert.assertEquals("Count incorrect for 'the' on URL source", 4371, wf.count("the"));
		
		wf = WordFinder.createWordFinder(FILE_SOURCE);
		Assert.assertEquals("Count incorrect for 'the' on file source", 3948, wf.count("the"));
	}

}
