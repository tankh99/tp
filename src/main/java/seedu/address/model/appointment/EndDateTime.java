package seedu.address.model.appointment;

import java.time.LocalDateTime;

/**
 * Represents the end date and time of an appointment in the appointment book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTimeField(String)}
 */
public class EndDateTime extends DateTimeField {
    /**
     * Constructs a {@code EndDateTime}.
     * @param endDateTime A valid date
     */
    public EndDateTime(String endDateTime) {
        super(endDateTime);
    }

    /**
     * Constructs a {@code EndDateTime}.
     * @param endDateTime A valid date
     */
    public EndDateTime(LocalDateTime endDateTime) {
        super(endDateTime);
    }
}
