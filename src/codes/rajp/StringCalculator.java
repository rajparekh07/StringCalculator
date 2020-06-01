package codes.rajp;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String Calculator
 * This class implements a method which adds zero, one, or two numbers.
 *
 * @author  Raj Parekh
 * @version 1
 * @since   01-06-2020
 *
 */
public class StringCalculator {


    /**
     *  An integer stating maximum numbers to be added.
     */
    private static final int MAX_NUMBERS_TO_BE_ADDED = 2;


    /**
     *  A Regular Expression for matching out the digits delimited with ","
     */
    public static final String DELIMITER = "(((?!,).)\\w?)+";

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
     * @param numbers as a string separated by "," as a delimiter
     * @return ArrayList with all parsed numbers
     * @throws Exception when illegal string character is found instead of number
     * or when more than two
     */
    private ArrayList<Integer> parseNumbers(String numbers) {

        int numbersCount = 1;
        ArrayList<Integer> parsedNumbers = new ArrayList<>();

        Pattern inputNumbersPattern = Pattern.compile(DELIMITER);
        Matcher numbersMatcher = inputNumbersPattern.matcher(numbers);

        while (numbersMatcher.find() && numbersCount <= MAX_NUMBERS_TO_BE_ADDED) {
            String extractedNumber = numbersMatcher.group();
            Integer parsedNumber = new Integer(extractedNumber);

            parsedNumbers.add(parsedNumber);

            numbersCount++;
        }

        return parsedNumbers;
    }

    /**
     * A main driver method for String Calculator.
     * @param args
     */
    public static void main(String[] args) {
        StringCalculator st = new StringCalculator();
        System.out.println(st.add("1,2"));
    }
}
