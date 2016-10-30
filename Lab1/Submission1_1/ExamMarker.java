import java.util.Scanner;

/**
 * Starter code for JOOSE2 Laboratory 1, Submission 2.
 *
 * @author Mary Ellen Foster <mefoster@gmail.com>
 */
public class ExamMarker{

    public static void main(String[] args){
        Scanner standardInput = new Scanner(System.in);

        int total = 0;
        int best, worst;
        int bestStudent = 1, worstStudent = 1;
        int mark;

        System.out.println("Enter mark " + 1 + ": "); // get first mark from user
        mark = standardInput.nextInt();

        best = mark; // first mark is both the best and worst
        worst = mark;
        total += mark;

        for (int i = 2; i < 4; i++) { // first mark already taken, index from 2
            System.out.println("Enter mark " + i + ": "); // get mark from user
            mark = standardInput.nextInt();

            if (mark > best){
                best = mark;
                bestStudent = i;
            }

            if (mark < worst){
                worst = mark;
                worstStudent = i;
            }

            total += mark;
        }

        int avg = total / 3; // only ever three marks given

        System.out.println("Average mark = " + avg);
        System.out.println("Best student: " + bestStudent);
        System.out.println("Worst student: " + worstStudent);

        standardInput.close();
    }

}
