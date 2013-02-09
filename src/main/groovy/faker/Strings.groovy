package faker

import java.util.List;

/**
 * String faker
 * 
 * @author peter
 */
class Strings {

    private static final List NUMERIC = '0'..'9'
    private static final List ALPHABETIC = ('a'..'z') + ('A'..'Z')
    private static final List ALPHANUMERIC = ALPHABETIC + NUMERIC

    /**
     * Generate a numeric string of the given length.
     * @param length the length
     * @return the numeric string
     */
    public static String numeric(int length) {
        return string(length, NUMERIC)
    }

    /**
     * Generate a numeric string, length will be randomly chosen from the given list.
     * @param length the length
     * @return the numeric string
     */
    public static String numeric(List<Integer> length) {
        assert length

        return numeric(length.sample())
    }

    /**
     * Generate an alphabetic string of the given length.
     * @param length the length
     * @return the alphabetic string
     */
    public static String alphabetic(int length) {
        return string(length, ALPHABETIC)
    }

    /**
     * Generate a alphabetic string, length will be randomly chosen from the given list.
     * @param length the length
     * @return the alphabetic string
     */    
    public static String alphabetic(List<Integer> length) {
        assert length

        return alphabetic(length.sample())
    }

    /**
     * Generate an alphanumeric string of the given length.
     * @param length the length
     * @return the alphanumeric string
     */
    public static String alphanumeric(int length) {
        return string(length, ALPHANUMERIC)
    }

    /**
     * Generate an alphanumeric string, length will be randomly chosen from the given list.
     * @param length the length
     * @return the alphanumeric string
     */
    public static String alphanumeric(List<Integer> length) {
        assert length

        return alphanumeric(length.sample())
    }

    /**
     * Generate a string of the given length, using a random sample from the given characters.
     * @param length the length
     * @return the string
     */
    public static String string(int length, List characters) {
        return characters.sample(length).join()
    }

    /**
     * Generate a string using a random sample from the given characters, length will be randomly chosen from the given list.
     * @param length the length
     * @return the string
     */
    public static String string(List<Integer> length, List characters) {
        return string(length.sample(), characters)
    }
    
    // TODO move regexify to here, and use automaton (http://www.brics.dk/automaton) for string generation
}
