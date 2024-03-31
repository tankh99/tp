package seedu.address.model.appointment;

import java.util.function.Predicate;

/**
 * Tests that a {@code Appointment}'s {@code studentId} and {@code appointmentId} matches the indexs given.
 */
public class FindAppointmentPredicate implements Predicate<Appointment> {
    private final int patientId;
    private final int appointmentId;

    /**
     * Finds appointment based on student and appointment id.
     * @param patientId target student id.
     * @param appointmentId target appointment id.
     */
    public FindAppointmentPredicate(int patientId, int appointmentId) {
        this.patientId = patientId;
        this.appointmentId = appointmentId;
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointmentId == appointment.getAppointmentId() && patientId == appointment.getPatientId().patientId;
    }
}
