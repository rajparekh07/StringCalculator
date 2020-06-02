package codes.rajp;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String Calculator
 * This class implements a method which adds n numbers separated by ",".
 *
 * @author  Raj Parekh
 * @version 3
 * @since   02-06-2020
 *
 */
public class StringCalculator {
    /**
     *  A Regular Expression for matching out the digits delimited with ","
     */
    private static final String DELIMITER = "(((?!,|(\\\\)|\\n|n).)\\w?)+";

    /**
     *  A Regular Expression for matching input number string validity
     */
    private static final String NUMBER_STRING_VALIDATOR = "^^((([-+])?\\d)+((,|\\n|(\\\\)|n|\\\\n)([-+])?\\d+)*)?";

    /**
     * An empty constructor
     */
    StringCalculator() {}

    /**
     * A method for simple addition method taking numbers as string input and returning the sum.
     * @param numbers as a string separated by "," as a delimiter
     * @return sum of numbers passed as the argument
     */
    public int add(String numbers) {

        ArrayList<Integer> parsedNumbers = this.parseNumbers(numbers);

        return parsedNumbers.stream().mapToInt(Integer::intValue).sum();
    }


    /**
     * parsing the String numbers to java.util.ArrayList list
     * @param numbers n numbers as a string separated by "," as a delimiter
     * @return ArrayList with all parsed numbers
     * @throws Exception when illegal string character is found instead of number
     * or when more than two
     */
    private ArrayList<Integer> parseNumbers(String numbers) {

        ArrayList<Integer> parsedNumbers = new ArrayList<>();

        if (!this.validateNumberString(numbers)) {
            throw new RuntimeException("Invalid Number String");
        }

        Pattern inputNumbersPattern = Pattern.compile(DELIMITER);
        Matcher numbersMatcher = inputNumbersPattern.matcher(numbers);

        while (numbersMatcher.find()) {
            String extractedNumber = numbersMatcher.group();
            Integer parsedNumber = new Integer(extractedNumber);

            parsedNumbers.add(parsedNumber);
        }

        return parsedNumbers;
    }

    /**
     * A private method to validate the input number string
     * @param numbers input number string
     * @return boolean based on number string validity
     */
    private boolean validateNumberString(String numbers) {
        return Pattern.matches(NUMBER_STRING_VALIDATOR, numbers);
    }

    /**
     * A main driver method for String Calculator.
     * @param args string arguments to java cli
     */
    public static void main(String[] args) {
        StringCalculator st = new StringCalculator();
        String numberString;

        // If no command line arguments are passed, then use stdinput
        if (args.length > 0) {
            numberString = args[0];
        } else {
            Scanner inputScanner = new Scanner(System.in);
            numberString = inputScanner.next();
        }

        System.out.println(st.add(numberString));
    }
}
