package seedu.address.testutil;

import seedu.address.model.appointment.EndDateTime;

import java.time.LocalDateTime;

public class TypicalEndDateTime {
    public static final EndDateTime END_DATE_TIME_1 = new EndDateTime(LocalDateTime.of(2024, 3, 18, 9, 0));
    public static final EndDateTime END_DATE_TIME_2 = new EndDateTime(LocalDateTime.of(2024, 3, 18, 10, 0));
    public static final EndDateTime END_DATE_TIME_3 = new EndDateTime(LocalDateTime.of(2024, 3, 18, 11, 0));
    public static final EndDateTime END_DATE_TIME_4 = new EndDateTime(LocalDateTime.of(2024, 3, 18, 12, 0));
    public static final EndDateTime END_DATE_TIME_5 = new EndDateTime(LocalDateTime.of(2024, 3, 18, 13, 0));
    public static final EndDateTime END_DATE_TIME_6 = new EndDateTime(LocalDateTime.of(2024, 3, 18, 14, 0));
}
