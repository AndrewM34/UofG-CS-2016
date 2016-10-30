import java.util.ArrayList;

/**
 * Created by andrew on 23/10/16.
 */
public class WordFinderMain {
	public static void main(String[] args) {
		// create an ArrayList of WordFinder objects
		ArrayList<WordFinder> finders = new ArrayList<>();

		// add each element in args to finders
		for (String toFind : args) {
			try {
				WordFinder wf = WordFinder.createWordFinder(toFind); // create a new WordFinder
				finders.add(wf);									 // add the WordFinder to the ArrayList
			} catch (java.io.IOException e) {						 // could not create a WordFinder
				System.out.println("Warning: couldn't create WordFinder for " + toFind + ":");
				System.out.println(e.getMessage());
				System.out.println("IOException for file: " + toFind);
			}
		}
		
		// create a scanner to get the word to search for, close scanner
		java.util.Scanner s = new java.util.Scanner(System.in);

		System.out.println("Please enter a word to search for:");
		String searchWord = s.nextLine();
		System.out.println("\n");
		
		s.close();

		// get each WordFinder, call WordFinder.toString() to print what is being tested
		// then call WordFinder.count() to get the amount of times a word is in the source
		// catches IllegalArgumentException
		for (int i = 0; i < finders.size(); i++) { // for every WordFinder
			try {
				WordFinder finder = finders.get(i); // let the current finder be the ith WordFinder

				System.out.println(finder.toString());
				System.out.println("-> Occurrences of '" + searchWord + "': " + finder.count(searchWord)+'\n');
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid input argument.");
				break;
			}
		}
	}
}
