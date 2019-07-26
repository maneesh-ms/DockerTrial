package framework.exceptions;

/**
 * The Custom exception to represent the expected errors in this application.
 */
public class BabbelTestException extends Exception {

    /**
     * The constructor
     * @param message, the message to be passed.
     */
    public BabbelTestException(String message){
        super(message);
        System.out.println(message);
    }
}
