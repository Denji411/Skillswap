package resources;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public final class ParserUtils {
    public static  Status parseStatus(String s) throws IllegalArgumentException {
        return Status.valueOf(s);
    }

    public static LocalDateTime parseLocalDate(String s) throws DateTimeException {
        return LocalDateTime.parse(s);
    }

    public static boolean parseActive(String s) {
        return Boolean.parseBoolean(s);
    }

    public static Level parseLevel(String s) throws IllegalArgumentException {
        return Level.valueOf(s);
    }

    public static Category parseCategory(String s) throws IllegalArgumentException {
        return Category.valueOf(s);
    }

    public static double parseAvgRating(String s) throws NumberFormatException {
        return Double.parseDouble(s);
    }

    public static int parseRatingcount(String s) throws NumberFormatException {
        return Integer.parseInt(s);
    }
    
    public static double parseStars(String s) throws NumberFormatException {
        return Double.parseDouble(s);
    }
}
