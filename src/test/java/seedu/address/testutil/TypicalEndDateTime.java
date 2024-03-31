package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.model.appointment.EndDateTime;


/**
 * A utility class containing a list of {@code EndDateTime} objects to be used in tests.
 */
public class TypicalEndDateTime {
    public static final EndDateTime END_DATE_TIME_1 = new EndDateTime(LocalDateTime.of(2024, 3, 18, 9, 0));
    public static final EndDateTime END_DATE_TIME_2 = new EndDateTime(LocalDateTime.of(2024, 3, 18, 10, 0));
    public static final EndDateTime END_DATE_TIME_3 = new EndDateTime(LocalDateTime.of(2024, 3, 18, 11, 0));
    public static final EndDateTime END_DATE_TIME_4 = new EndDateTime(LocalDateTime.of(2024, 3, 18, 12, 0));
    public static final EndDateTime END_DATE_TIME_5 = new EndDateTime(LocalDateTime.of(2024, 3, 18, 13, 0));
    public static final EndDateTime END_DATE_TIME_6 = new EndDateTime(LocalDateTime.of(2024, 3, 18, 14, 0));
}
