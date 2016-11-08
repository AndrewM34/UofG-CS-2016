package playlists;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by
 * @author andrew
 * on 07/11/16.
 */
public class Playlist {

    // fields
    List<Song> playlistSongs = new ArrayList<>();

    // constructor
    public Playlist (List<Song> songs) {
        playlistSongs.addAll(songs);
    }

    // getter
    public List<Song> getSongs() {
        return playlistSongs;
    }

    // methods

    /**
     * create and return a new playlist with all the songs from the current playlist
     * with a given song appended at the end
     * @param song
     * @return playlist
     */
    public Playlist addSong (Song song) {
    	List<Song> newSongs = new ArrayList<>(this.playlistSongs);
    	newSongs.add(song); // add the new song
        Playlist playlist = new Playlist(newSongs);
        return playlist;
    }

    /**
     * creates and returns a new playlist with the same songs from before but in a random order
     * @return playlist
     */
    public Playlist shuffle() {
    	List<Song> newSongs = new ArrayList<>(this.playlistSongs);
        Collections.shuffle(newSongs); // shuffle order
        return new Playlist(newSongs);
    }

    /**
     * save the current playlists to a file at a given location
     * @param filename
     * @throws IOException
     */
    public void save (String filename) throws IOException {
        try {
            PrintWriter writer = new PrintWriter(filename, "UTF-8");

            for (Song song : playlistSongs) { // for each song
                // write string representation to the file
                writer.println(song.getArtist()+";"+song.getTitle());
            }

            writer.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * load a playlist from a given location
     * returns a playlist loaded from a file
     */
    public static Playlist load (String filename) throws IOException {
        List<Song> songs = new ArrayList<>();
        try {
            // create scanner for file, load into memory
            java.util.Scanner s = new java.util.Scanner(new java.io.File(filename));

            // for each line in filename
            while (s.hasNextLine()) {
                String line = s.nextLine(); // read the next line
                String[] songDetails = line.split(";"); // split by semicolon, as written by save()
                Song song = new Song(songDetails[0], songDetails[1]); // instantiate a new song with (artist, title)
                songs.add(song); // append to the arraylist of songs
            }

            s.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return new Playlist(songs); // will return an empty array if exception is caught
    }
}
