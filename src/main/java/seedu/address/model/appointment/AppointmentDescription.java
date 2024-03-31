package seedu.address.model.appointment;

public class AppointmentDescription {

    public final String appointmentDescription;

    /**
     * Constructs a {@code HasAttended}.
     *
     * @param appointmentDescription A valid attend status.
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
