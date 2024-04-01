package seedu.address.model.appointment;

/**
 * Represents an Appointment's attended status in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String)}
 */
public class HasAttended {

    public static final String MESSAGE_CONSTRAINTS = "Attended status should only be true or false.";
    public final boolean hasAttended;

    /**
     * Constructs a {@code HasAttended}.
     *
     * @param hasAttended A valid attend status.
     */
    public HasAttended(boolean hasAttended) {
        this.hasAttended = hasAttended;
    }


    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidStatus(String test) {
        return test.equalsIgnoreCase("true") || test.equalsIgnoreCase("false");
    }

    @Override
    public String toString() {
        return String.valueOf(hasAttended);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HasAttended)) {
            return false;
        }

        HasAttended otherStatus = (HasAttended) other;
        return hasAttended == otherStatus.hasAttended;
    }

}
