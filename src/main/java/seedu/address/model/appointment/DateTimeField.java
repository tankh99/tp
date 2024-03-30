package seedu.address.model.appointment;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

abstract class DateTimeField {
    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in the format of yyyy-MM-dd HH:mm";
    private final LocalDateTime value;

    /**
     * Constructs a {@code DateTimeField}.
     * @param dateTimeValue A valid date
     */
    public DateTimeField(String dateTimeValue) {
        requireNonNull(dateTimeValue);
        checkArgument(isValidDateTimeField(dateTimeValue), MESSAGE_CONSTRAINTS);
        this.value = LocalDateTime.parse(dateTimeValue);
    }

    /**
     * Returns true if a given string is a valid start date.
     */
    public static boolean isValidDateTimeField(String test) {
        return test.matches("([0-9]{4})-([0-9]{2})-([0-9]{2}) ([0-9]{2}):([0-9]{2})");
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DateTimeField)) {
            return false;
        }

        DateTimeField otherDateTimeField = (DateTimeField) other;
        return value.equals(otherDateTimeField.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
