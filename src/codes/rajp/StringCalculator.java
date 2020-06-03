package codes.rajp;

import codes.rajp.exceptions.NegativeNumberHandler;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String Calculator
 * This class implements a method which adds n numbers separated by delimiters.
 *
 * @author  Raj Parekh
 * @version 8
 * @since   04-06-2020
 *
 */
public class StringCalculator {

    /**
     * An empty constructor
     */
    StringCalculator() {}

    /**
     *  RegexStore
     *  A inner static class to store various Regular Expressions
     */
    static class RegexStore {
        /**
         *  A Regular Expression for matching out the digits from the number string
         */
        static final String NUMBER_EXTRACTOR = "[-+]?(\\d+)";
        /**
         *  A method returning a Regular Expression for matching input number string validity
         *  @return updated regex having custom delimiter
         */
        static final String NUMBER_STRING_VALIDATOR = "((\\/\\/)(\\[(\\D{1,})\\](\\[(\\D{1,})\\])?(|\\n|(\\\\)|n|\\\\n)))?(((-|\\+)?\\d)+((\\4|\\6|,|\\n|(\\\\)|n|\\\\n)(-|\\+)?\\d+)*)";

    }

    /**
     * A method for simple addition method taking numbers as string input and returning the sum.
     * @param numbers as a string separated by a delimiter
     * @return sum of numbers passed as the argument
     */
    public int add(String numbers) {
        ArrayList<Integer> parsedNumbers = this.parseNumbers(numbers);
        NegativeNumberHandler negativeNumberHandler = new NegativeNumberHandler();

        int sum = parsedNumbers.stream().mapToInt(Integer::intValue).filter(number -> {
            if (number < 0) {
                negativeNumberHandler.reportNegativeNumber(number);
                return false;
            }
            return number < 1000;
        }).sum();

        if (negativeNumberHandler.isEligibleForException()) {
            throw negativeNumberHandler.getException();
        }

        return sum;
    }


    /**
     * parsing the String numbers to java.util.ArrayList list
     * @param numbers n numbers as a string separated by a delimiter
     * @return ArrayList with all parsed numbers
     * @throws Exception when illegal string character is found instead of number
     * or when more than two
     */
    private ArrayList<Integer> parseNumbers(String numbers) {

        ArrayList<Integer> parsedNumbers = new ArrayList<>();

        if (!this.validateNumberString(numbers)) {
            throw new RuntimeException("Invalid Number String");
        }

        Pattern inputNumbersPattern = Pattern.compile(RegexStore.NUMBER_EXTRACTOR);
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
        return Pattern.matches(RegexStore.NUMBER_STRING_VALIDATOR, numbers);
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
