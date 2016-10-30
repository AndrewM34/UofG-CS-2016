public class EanChecksum {
    public static void main(String[] args) {
        // Read the barcode from the user
        java.util.Scanner stdin = new java.util.Scanner(System.in);
        System.out.println("Enter the barcode number: ");
        String barcode = stdin.next();
        stdin.close();

        int checksum = 0;

        int barcodeLen = barcode.length();
        if (barcodeLen == 7 || barcodeLen == 12) {
            // barcode length valid, calculate check digit
            // going from last char to first for easy determination of the multiplier
            int multiplier;
            for (int i = barcodeLen - 1; i >= 0; i--) {
                int c = barcode.charAt(i) - '0';

                if (c > 9) { // check if c is not a digit
                    System.out.println("Invalid barcode format");
                    return;
                }

                if ((barcodeLen - i) % 2 == 0) { // every 2nd value of i
                    multiplier = 1;
                } else {
                    multiplier = 3;
                }

                checksum += c * multiplier; // update the checksum by the formula

            }

            int sumDigit = 10 - (checksum % 10);
            if (sumDigit == 10) { // ensure for cases where sumDigit is 10 to default to 0
                sumDigit = 0;
            }
            // System.out.println(checksum);
            System.out.println("Checksum digit: " + sumDigit);

        } else {
            System.out.println("Invalid barcode length");
            return;
        }

    }

}
