package codes.rajp;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String Calculator
 * This class implements a method which adds n numbers separated by delimiters.
 *
 * @author  Raj Parekh
 * @version 4
 * @since   03-06-2020
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
         *  A Regular Expression for extracting out the custom delimiter
         */
        static final String CUSTOM_DELIMITER_EXTRACTOR = "((?<=\\/\\/).)";
        /**
         *  A Regular Expression for validate the custom delimiter mode
         */
        static final String CUSTOM_DELIMITER_MODE_VALIDATOR = "(\\/\\/)(.(\\\\n|\\n)((.|\\n)*))";

        /**
         *  A method returning a Regular Expression for matching input number string validity
         *  @param optionalDelimiter a final String which is extracted custom delimiter
         *  @return updated regex having custom delimiter
         */
        static String NUMBER_STRING_VALIDATOR(final String optionalDelimiter) {
            return "((\\/\\/)(.(|\\n|(\\\\)|n|\\\\n)))?(((-|\\+)?\\d)+(("+ optionalDelimiter +"|,|\\n|(\\\\)|n|\\\\n)(-|\\+)?\\d+)*)";
        }

    }

    /**
     * A method for simple addition method taking numbers as string input and returning the sum.
     * @param numbers as a string separated by a delimiter
     * @return sum of numbers passed as the argument
     */
    public int add(String numbers) {

        ArrayList<Integer> parsedNumbers = this.parseNumbers(numbers);

        return parsedNumbers.stream().mapToInt(Integer::intValue).sum();
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
     * A method to extract custom delimiter from number string
     * @param numbers a string of delimited numbers
     * @return matched custom delimiter
     */
    private String extractCustomDelimiter(String numbers) {
        Pattern customDelimiterPattern = Pattern.compile(RegexStore.CUSTOM_DELIMITER_EXTRACTOR);
        Matcher customerDelimitersMatcher = customDelimiterPattern.matcher(numbers);
        if (customerDelimitersMatcher.find()) {
            return customerDelimitersMatcher.group();
        }

        throw new RuntimeException("Expected Custom Delimiter not found");
    }

    /**
     * A private method to validate the input number string
     * @param numbers input number string
     * @return boolean based on number string validity
     */
    private boolean validateNumberString(String numbers) {
        String optionalDelimiter = "";
        if (this.hasCustomDelimiters(numbers)) {
            optionalDelimiter = extractCustomDelimiter(numbers);
        }
        return Pattern.matches(RegexStore.NUMBER_STRING_VALIDATOR(optionalDelimiter), numbers);
    }

    /**
     * A private method to validate the custom delimiter in number string
     * @param numbers input number string
     * @return boolean based on custom delimiter string validity
     */
    private boolean hasCustomDelimiters(String numbers) {
        return numbers.matches(RegexStore.CUSTOM_DELIMITER_MODE_VALIDATOR);
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
