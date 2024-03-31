package seedu.address.testutil;

import seedu.address.model.appointment.StartDateTime;

import java.time.LocalDateTime;

public class TypicalStartDateTime {
    public static final StartDateTime START_DATE_TIME_1 = new StartDateTime(LocalDateTime.of(2024, 3, 18, 9, 0));
    public static final StartDateTime START_DATE_TIME_2 = new StartDateTime(LocalDateTime.of(2024, 3, 18, 10, 0));
    public static final StartDateTime START_DATE_TIME_3 = new StartDateTime(LocalDateTime.of(2024, 3, 18, 11, 0));
    public static final StartDateTime START_DATE_TIME_4 = new StartDateTime(LocalDateTime.of(2024, 3, 18, 12, 0));
    public static final StartDateTime START_DATE_TIME_5 = new StartDateTime(LocalDateTime.of(2024, 3, 18, 13, 0));
    public static final StartDateTime START_DATE_TIME_6 = new StartDateTime(LocalDateTime.of(2024, 3, 18, 14, 0));
}
