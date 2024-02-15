import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class CheckPassword {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CheckPassword() {
    }

    /**
     * Checks whether the given String satisfies the OSU criteria for a valid
     * password. Prints an appropriate message to the given output stream.
     *
     * @param s
     *            the String to check
     * @param out
     *            the output stream
     */

    private static void checkPassword(String s, SimpleWriter out) {
        final int lengthreq = 8;
        final int checkreq = 3;
        //Variable for whether or not a check was passed
        int checksPassed = 0;

        //Runs for string 8+ characters
        if (s.length() >= lengthreq) {
            //Calling methods to see if it passes each check
            if (containsUpperCaseLetter(s)) {
                checksPassed++;
            }
            if (containsLowerCaseLetter(s)) {
                checksPassed++;
            }
            if (containsNumber(s)) {
                checksPassed++;
            }

            if (containsSC(s)) {
                checksPassed++;
            }

            if (checksPassed < checkreq) {
                System.out.println("Password does not fulfill requirements");
            }

        }
    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String s) {
        boolean cFound = false; //Setting upper case to false

        // loop to run through the whole string
        for (int i = 0; i < s.length() && !cFound; i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                //Changes found upper to true if one is there
                cFound = true;
            }
        }
        return cFound;
    }

    /**
     * Checks if the given String contains an lower case letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains an lower case letter, false otherwise
     */
    private static boolean containsLowerCaseLetter(String s) {
        boolean cFound = false; //Setting lower case to false

        // loop to run through the whole string
        for (int i = 0; i < s.length() && !cFound; i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                //Changes found lower to true if one is there
                cFound = true;
            }
        }
        return cFound;
    }

    /**
     * Checks if the given String contains a special character
     * !@#$%^&*()_-+={}[]:;,.? .
     *
     * @param s
     *            the String to check
     * @return true if s contains a special character, false otherwise
     */
    private static boolean containsSC(String s) {
        int sCheck = 0;
        boolean pass;
        //If the special character is not found, sCheck goes down by 1. Since there are 20 characters, if no characters are found by the end then sCheck should be-20
        sCheck = sCheck + s.indexOf('@');
        sCheck = sCheck + s.indexOf('#');
        sCheck = sCheck + s.indexOf('$');
        sCheck = sCheck + s.indexOf('%');
        sCheck = sCheck + s.indexOf('^');
        sCheck = sCheck + s.indexOf('&');
        sCheck = sCheck + s.indexOf('*');
        sCheck = sCheck + s.indexOf('(');
        sCheck = sCheck + s.indexOf(')');
        sCheck = sCheck + s.indexOf('_');
        sCheck = sCheck + s.indexOf('-');
        sCheck = sCheck + s.indexOf('+');
        sCheck = sCheck + s.indexOf('{');
        sCheck = sCheck + s.indexOf('}');
        sCheck = sCheck + s.indexOf('[');
        sCheck = sCheck + s.indexOf(']');
        sCheck = sCheck + s.indexOf(':');
        sCheck = sCheck + s.indexOf(';');
        sCheck = sCheck + s.indexOf(',');
        sCheck = sCheck + s.indexOf('?');
        if (sCheck > -20) {
            pass = true;
        } else {
            pass = false;
        }
        return pass;
    }

    /**
     * Checks if the given String contains a number.
     *
     * @param s
     *            the String to check
     * @return true if s contains a number, false otherwise
     */
    private static boolean containsNumber(String s) {
        boolean cFound = false; //Setting number found to false

        // loop to run through the whole string
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                //Changes digit found to true if one is there
                cFound = true;
            }
        }
        return cFound;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        //Ask for password
        out.println("Enter in your password");
        String uPass = in.nextLine();

        //loop to keep running as long as string isn't empty
        while (uPass.charAt(0) != ' ') {

            //Checks password
            checkPassword(uPass, out);

            //Ask for Password again
            out.println("Enter in another password");
            uPass = in.nextLine();
        }
        //Close input and output streams
        in.close();
        out.close();
    }

}
