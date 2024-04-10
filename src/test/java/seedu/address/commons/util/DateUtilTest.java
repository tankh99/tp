package seedu.address.commons.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
class DateUtilTest {

    private LocalDateTime validDateTime1 = LocalDateTime.of(2024, 3, 18, 9, 0);
    @Test
    void parseDateTime_validDateTimeString_returnsParsedDateTime() {
        String validDateTimeString1 = validDateTime1.format(
                DateTimeFormatter.ofPattern(DateUtil.DATETIME_INPUT_FORMAT));
        LocalDateTime parsedDateTime1 = DateUtil.parseDateTime(validDateTimeString1);
        Assertions.assertEquals(validDateTime1, parsedDateTime1);
    }

    @Test
    void parseDateTime_validDateTimeString_returnsNull() {
        String invalidDateTimeString1 = null;
        LocalDateTime parsedDateTime1 = DateUtil.parseDateTime(invalidDateTimeString1);
        Assertions.assertNull(parsedDateTime1);

        // Invalid format: no time
        String invalidDateTimeString2 = "2024-03-18";
        LocalDateTime parsedDateTime2 = DateUtil.parseDateTime(invalidDateTimeString2);
        Assertions.assertNull(parsedDateTime2);

        // Invalid format: invalid date
        String invalidDateTimeString3 = "2024-02-30 09:00";
        LocalDateTime parsedDateTime3 = DateUtil.parseDateTime(invalidDateTimeString3);
        Assertions.assertNull(parsedDateTime3);
    }

    @Test
    void formatDateTime_validDateTimeString_returnsFormattedDateTime() {
        LocalDateTime validDateTime1 = LocalDateTime.of(2024, 3, 18, 9, 0);
        String formattedDateTime1 = DateUtil.formatDateTime(validDateTime1);
        Assertions.assertEquals("18 Mar 2024, 09:00 AM", formattedDateTime1);
    }

    @Test
    void formatDateTime_invalidDateTime_returnsNull() {
        LocalDateTime invalidDateTime1 = null;
        String formattedDateTime1 = DateUtil.formatDateTime(invalidDateTime1);
        Assertions.assertNull(formattedDateTime1);
    }
}
