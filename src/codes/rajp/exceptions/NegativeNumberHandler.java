package codes.rajp.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Negative Number Handler
 * A class to handle reporting and collecting of negative integers
 * @author rajparekh
 * @version 1
 * @since 03-06-2020
 */
public class NegativeNumberHandler {

    /**
     *  A List<Integer> to store reported negative integer
     */
    private List<Integer> negativeIntegerList;

    /**
     *  Initializing the ArrayList
     */
    public NegativeNumberHandler() {
        this.negativeIntegerList = new ArrayList<>();
    }

    /**
     * A method to report and collect negative number
     * @param number negative number
     * @return current instance of NegativeNumberHandler
     */
    public NegativeNumberHandler reportNegativeNumber(int number) {

        this.negativeIntegerList.add(number);
        return this;
    }

    /**
     * A method to collect the reported negative numbers and get exception
     * @return An instance of NegativeNumberException
     */
    public NegativeNumberException getException() {
        String negativeNumberString = this.negativeIntegerList.stream().map(String::valueOf).collect(Collectors.joining(","));

        return new NegativeNumberException("negatives not allowed : ".concat(negativeNumberString));
    }

    public boolean isEligibleForException () {
        return !this.negativeIntegerList.isEmpty();
    }
}

