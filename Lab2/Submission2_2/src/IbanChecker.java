import java.util.Scanner;

public class IbanChecker {

    public static void main(String[] args) {
        // Read the IBAN, convert it to upper case, and remove all whitespace
        Scanner stdin = new Scanner(System.in);
        System.out.println("Enter the IBAN: ");
        String iban = stdin.nextLine().toUpperCase().replaceAll("\\s", "");
        stdin.close();

        String countrycode = iban.substring(0, 2);
        int ibanLen = iban.length();

        switch (countrycode) {
            case "GB":
                if (ibanLen != 22) {
                    System.out.println("Invalid IBAN length: " + ibanLen);
                    return;
                }
                break;

            case "GR":
                if (ibanLen != 27) {
                    System.out.println("Invalid IBAN length: " + ibanLen);
                    return;
                }
                break;

            case "SA":
                if (ibanLen != 24) {
                    System.out.println("Invalid IBAN length: " + ibanLen);
                    return;
                }
                break;

            case "CH":
                if (ibanLen != 21) {
                    System.out.println("Invalid IBAN length: " + ibanLen);
                    return;
                }
                break;

            case "TR":
                if (ibanLen != 26) {
                    System.out.println("Invalid IBAN length: " + ibanLen);
                    return;
                }
                break;

            default:
                System.out.println("Unknown country code: " + countrycode);
                return; // no "break" as it would break this switch sometimes - not the correct break
        } // end switch

        iban = iban.substring(4) + iban.substring(0, 4); // step 4, move first 4 chars to end

        String convertedIban = "";

        // now go through the IBAN a character at a time
        // change letters to an int in the range (10,26)
        // append to the new IBAN string
        for (int i = 0; i < ibanLen; i++) {
            int c = iban.charAt(i);

            if (c > 90 || c < 48) { // if the character is not a letter or digit by ASCII values
                System.out.println("Invalid character in IBAN: " + iban.charAt(i));
                return;
            }

            if (c >= 'A') { // n is a letter
                c = Character.getNumericValue(c); // does the same as below line abut nicer
                // c = 10 + (int) (iban.charAt(i) - 'A');
            } else {
                c = c - 48; // ASCII value of '1' is 49, hence subtract 48 if not a letter.
            }

            convertedIban += Integer.toString(c); // append the char value to the iban string
        }

        if (mod97(convertedIban) == 1) { // the calculation returns 1, meaning IBAN is valid
            System.out.println("IBAN is valid");
        } else {
            System.out.println("IBAN is invalid");
        }

    }

    /**
     * Computes the remainder when dividing the number represented by the input
     * parameter by 97. Implements the procedure described at
     * https://en.wikipedia.org/wiki/International_Bank_Account_Number#Modulo_operation_on_IBAN
     * to process very large numbers.
     *
     * @param digits The number to process, represented as a string
     * @return The result of digits mod 97
     */
    private static int mod97(String digits) {
        int n = Integer.valueOf(digits.substring(0, 9));
        int d = n % 97;
        digits = digits.substring(9);

        // Process the rest of the number
        while (digits.length() > 1) {
            int end = Math.min(7, digits.length());
            n = Integer.valueOf(d + digits.substring(0, end));
            d = n % 97;
            digits = digits.substring(end);
        }
        return d;
    }

}
