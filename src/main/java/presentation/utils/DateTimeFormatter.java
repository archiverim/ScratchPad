package presentation.utils;

import presentation.constants.Constants;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.FormatStyle;
import java.util.Locale;

public class DateTimeFormatter {
    // Formats a DateTime into a locale string
    public static String FormatLocalDateTime(LocalDateTime date) {
        if (date == null) {
            return "";
        }
        ZoneId sourceZone = ZoneId.of(Constants.Text.DATE_TIME_ZONE);
        ZoneId userZone = ZoneId.systemDefault();
        ZonedDateTime userDateTime = date.atZone(sourceZone).withZoneSameInstant(userZone);
        java.time.format.DateTimeFormatter formatter =
                java.time.format.DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.CANADA);
        return userDateTime.format(formatter);
    }
}
