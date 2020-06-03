package codes.rajp.exceptions;

/**
 * Negative Number Exception
 * A extended class of Runtime Exception
 * @author rajparekh
 * @version 1
 * @since 03-06-2020
 */
public class NegativeNumberException extends RuntimeException {

    /**
     * A constructor for passing of message to superclass
     * @param message Exception Message
     */
    NegativeNumberException(String message) {
        super(message);
    }
}
