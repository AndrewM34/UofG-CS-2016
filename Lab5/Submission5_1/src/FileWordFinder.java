/**
 * Created by andrew on 23/10/16.
 */
public class FileWordFinder extends WordFinder {
	// constructor
    public  FileWordFinder (String fileName) throws  java.io.IOException {
        super(fileName);
        java.util.Scanner s = new java.util.Scanner(new java.io.File(fileName));

        // while there are still lines, append the next line to arraylist contents
        while (s.hasNextLine()) {
            this.contents.add(s.nextLine());
        }

        s.close();
    }
}
