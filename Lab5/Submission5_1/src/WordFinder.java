import java.util.ArrayList;

/**
 * Created by andrew on 23/10/16.
 */
public class WordFinder {
    // Fields
    protected String source;
    protected ArrayList<String> contents;

    // Constructor
    protected WordFinder(String source) {
        this.source = source;
        this.contents = new ArrayList<String>();
    }

    /**
     * counts the number of occurrences of the given word in the file contents
     * @param word
     * @return count
     */
    public int count(String word) {
        // iterate through the list of lines, spliting at whitespace
        int count = 0;
        
        for (int i = 0; i < contents.size(); i++) { // for every line
            String line = contents.get(i); // an array of words from the line
            
            String[] lineWords = line.split("[\\s\\p{Punct}]+"); // split into a new array by whitespace, using a regex

            for (int j = 0; j < lineWords.length; j++) { // for every word in the line
                if (lineWords[j].toLowerCase().equals(word.toLowerCase())) { // word searched for matches the input word
                    count++;
                }
            }
        }
        return count;
    }

    // Factory method to check source string, then construct a subclass as appropriate
    public static WordFinder createWordFinder(String source) throws java.io.IOException {
        if (source.startsWith("http")) {
            UrlWordFinder wordFinder = new UrlWordFinder(source);
            return wordFinder;
        } else {
            FileWordFinder wordFinder = new FileWordFinder(source);
            return wordFinder;
        }
    }

    /**
     * Overrides toString() to instead return what file or url is being searched
     */
    public String toString() {
        String type;
        if (source.startsWith("http")) {
            type = "URL";
        } else {
            type = "file";
        }
        return "Testing WordFinder (" + type + " = " + source+"):";
    }
}
