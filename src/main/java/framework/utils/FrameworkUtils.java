package framework.utils;
/*
The class that holds certain support util functions to be used in driver and tests
 */
public class FrameworkUtils {

    /**
     * The function that gives a new email address, which has not been used so far.
     *
     * @return
     */
    public static String getANewEmailAddress() {
        return "travelenthusiast" + String.valueOf((int) (Math.random() * 5000000 + 1)) + "@outlook.com";
    }

}
