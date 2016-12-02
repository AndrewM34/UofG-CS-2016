package test;

import musiclibrary.Album;
import musiclibrary.Song;
import musiclibrary.SongImpl;
import org.junit.*;
import org.junit.rules.ExpectedException;

public class TestSongImpl {
	public Song s1, s2, s3;

	@Before
	public void setUp() throws Exception {
		s1 = new SongImpl("artist1", "sTitle1", 1984, null, "albumTitle1");
		s2 = new SongImpl("artist2", "sTitle2", 3005, "comp", "albumTitle2");
		s3 = new SongImpl("Yung Lean", "Hurt", 2001, "Yung Lean", "Unknown Death 2002"); // SADBOYS :(
	}

	@After
	public void tearDown() throws Exception {
		s1 = null;
		s2 = null;
	}

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void testSetRatingIllegalArgument() {
		exception.expect(IllegalArgumentException.class);
		s1.setRating(100); // should throw an IllegalArgumentException
		s2.setRating(-10); // also should throw IllegalArgumentException
	}

	@Test
	public void testSetRating() {
		s2.setRating(3);
		Assert.assertEquals("Set rating not equal to rating", 3, s2.getRating());
	}

	@Test
	public void testIncreasePlayCount() {
		for (int i=1; i<10; i++) {
			s1.increasePlayCount();
			Assert.assertEquals("Play count not increased", i, s1.getPlayCount());
		}
	}

	@Test
	public void testGetAlbum() {
		Album expectedAlbum = new Album("artist1", "albumTitle1", 1984);
		Assert.assertEquals("Album created not equal to expected album", expectedAlbum, s1.getAlbum());
	}

	@Test
	public void testHasAlbumArtist() {
		Assert.assertFalse("Album artist should be the same as the artist", s3.hasAlbumArtist());
	}
}
