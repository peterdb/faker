package faker

import java.util.regex.Pattern

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

    /**
     * Given a regular expression, attempt to generate a string matching it.
     * <p>
     * This is a very naive implementation, so don't be shocked if it blows up on you in a spectacular fashion.
     * <p>
     * It does not handle ., *, unbounded ranges such as {1,}, extensions such as (?=), character classes, some abbreviations for character classes, and nested parentheses.
     * <p>
     * Algorithm copied from ruby faker
     */
    public static String xeger(String re) {
        assert re
        
        def result = re
        
        // Ditch the anchors
        result = result.replaceAll(~/^\/?\^?/, '').replaceAll(~/\$?\/?$/, '') 
        // All {2} become {2,2} and ? become {0,1}
        result = result.replaceAll(~/\{(\d+)\}/, '{$1,$1}').replaceAll(~/\?/, '{0,1}') 
        // [12]{1,2} becomes [12] or [12][12]
        result = result.replaceAll(~/(\[[^\]]+\])\{(\d+),(\d+)\}/) { m -> m[1] * (((m[2] as Integer)..(m[3] as Integer)).sample()) }
        // (12|34){1,2} becomes (12|34) or (12|34)(12|34)
        result = result.replaceAll(~/(\([^\)]+\))\{(\d+),(\d+)\}/) { m -> m[1] * (((m[2] as Integer)..(m[3] as Integer)).sample()) }
        // A{1,2} becomes A or AA or \d{3} becomes \d\d\d
        result = result.replaceAll(~/(\\?.)\{(\d+),(\d+)\}/) { m -> m[1] * (((m[2] as Integer)..(m[3] as Integer)).sample()) }
        // (this|that) becomes 'this' or 'that'
        result = result.replaceAll(~/\((.*?)\)/) { m -> (m[0].replaceAll(~/[\(\)]/, '').split('|') as List).sample() }
        // All A-Z inside of [] become C (or X, or whatever)
        result = result.replaceAll(~/\[([^\]]+)\]/) { m -> m[0].replaceAll(~/(\w)\-(\w)/) { range -> (range[1]..range[2]).sample() } }
        // All [ABC] become B (or A or C)
        result = result.replaceAll(~/\[([^\]]+)\]/) { m -> (m[1].toCharArray() as List).sample() }
        result = result.replaceAll(~/\\d/) { NUMERIC.sample() }
        result = result.replaceAll(~/\\w/) { ALPHABETIC.sample() }
        
        assert result ==~ re, "unable to generate string for regular expression $re"
        
        return result
    }

    public static String xeger(Pattern pattern) {
        assert pattern
        
        return xeger(pattern.toString())
    }
}
