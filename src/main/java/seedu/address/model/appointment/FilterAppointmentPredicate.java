package seedu.address.model.appointment;

import java.util.function.Predicate;

/**
 * Tests that a {@code Appointment}'s {@code startDateTime} and {@code endDateTime} matches the date times given.
 */
public class FilterAppointmentPredicate implements Predicate<Appointment> {
    private final StartDateTime startDateTime;
    private final EndDateTime endDateTime;

    /**
     * Finds appointment based on start and end date times.
     * @param startDateTime target start date time.
     * @param endDateTime target end date time.
     */
    public FilterAppointmentPredicate(StartDateTime startDateTime, EndDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public boolean test(Appointment appointment) {
        return startDateTime.compareTo(appointment.getStartDateTime()) <= 0
                && endDateTime.compareTo(appointment.getEndDateTime()) >= 0;
    }
}
