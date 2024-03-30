package seedu.address.model.appointment;

/**
 * Represents the start date and time of an appointment in the appointment book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTimeField(String)}
 */
public class StartDateTime extends DateTimeField {
    /**
     * Constructs a {@code StartDateTime}.
     * @param startDateTime A valid date
     */
    public StartDateTime(String startDateTime) {
        super(startDateTime);
    }
}
