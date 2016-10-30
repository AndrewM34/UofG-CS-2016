import java.util.Scanner;

/**
 * @author Andrew Mcnab, 2193329
 *
 */
public class ExamMarker2 {

    public static void main(String[] args) {
		Scanner standardInput = new Scanner(System.in);
		
		System.out.println("Enter number of marks: ");
		int n = standardInput.nextInt();

		while (n < 1){
			System.out.println("Please enter a positive value of marks: ");
			n = standardInput.nextInt();
		}

		double total = 0;
		int bestStudent = -1;
		double bestMark = 0;
		int worstStudent = -1;
		double worstMark = 100; // could cause weird results if n = 0, but that should never happen

		for (int i=1; i<(n+1); i++){
			// get mark from user
			System.out.println("Enter mark "+(i)+": ");
			double mark = standardInput.nextDouble();

			while (mark<0 || mark>100){ // don't allow marks outside the range
				System.out.println("Mark out of range!");
				mark = standardInput.nextDouble();
			}

			if (mark > bestMark){
				bestStudent = i;
				bestMark = mark;
			}

			if (mark < worstMark){
				worstStudent = i;
				worstMark = mark;
			}

			total += mark; // update total
		}

		double average = total/n; // double as per specification

		
		System.out.println("Average mark = "+ average);
		System.out.println("Best student: " + bestStudent);
		System.out.println("Worst student: " + worstStudent);
		
		standardInput.close();
    }

}
