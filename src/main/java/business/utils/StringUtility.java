package business.utils;

public class StringUtility {
    /**
     * Public methods
     */

    public static boolean isNullOrEmptyTrimmed(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static boolean hasLeadingOrTrailingWhitespace(String s) {
        return !s.equals(s.trim());
    }
}
