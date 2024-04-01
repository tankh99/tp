package seedu.address.model.appointment;

/**
 * Represents an Appointment's description in the address book.
 * Guarantees: immutable;
 */
public class AppointmentDescription {

    public final String appointmentDescription;

    /**
     * Constructs a {@code AppointmentDescription}.
     *
     * @param appointmentDescription A valid appointment description.
     */
    public AppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    @Override
    public String toString() {
        return appointmentDescription;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentDescription)) {
            return false;
        }

        AppointmentDescription otherDescription = (AppointmentDescription) other;
        return appointmentDescription.equalsIgnoreCase(otherDescription.appointmentDescription);
    }
}
