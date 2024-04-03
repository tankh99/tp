package seedu.address.model.util;

import java.util.List;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.EndDateTime;
import seedu.address.model.appointment.StartDateTime;
import seedu.address.model.patient.Patient;


/**
 * In charge of enforcing relationship constraints between models
 */
public class RelationshipUtil {

    /**
     * Checks if a {@code Person} with the given ID exists
     *
     * @param id       personId
     * @param patients List of {@code Person}
     */
    public static boolean personExists(int id, List<Patient> patients) {
        return patients.stream().anyMatch(patient -> patient.getSid() == id);
    }

    /**
     * Checks if the given date and time is already used by another {@code Appointment}.
     *
     * @param appointments List of {@code Appointment}s to check against
     */
    public static boolean isAppointmentDateTimeAlreadyTaken(StartDateTime startDateTime,
                                                            EndDateTime endDateTime, List<Appointment> appointments) {
        for (Appointment appointment : appointments) {
            // Check if the appointment is within the range of the existing appointment
            if (appointment.getEndDateTime().compareTo(startDateTime) > 0
                    && appointment.getStartDateTime().compareTo(endDateTime) < 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given start date and time is already used by another {@code Appointment}.
     *
     * @param appointments List of {@code Appointment}s to check against
     */
    public static boolean isAppointmentDateTimeAlreadyTaken(StartDateTime startDateTime,
                                                            List<Appointment> appointments) {
        for (Appointment appointment : appointments) {
            // Check if the appointment is within the range of the existing appointment
            if (appointment.getEndDateTime().compareTo(startDateTime) > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given end date and time is already used by another {@code Appointment}.
     *
     * @param appointments List of {@code Appointment}s to check against
     */
    public static boolean isAppointmentDateTimeAlreadyTaken(EndDateTime endDateTime,
                                                            List<Appointment> appointments) {
        for (Appointment appointment : appointments) {
            // Check if the appointment is within the range of the existing appointment
            if (appointment.getStartDateTime().compareTo(endDateTime) < 0) {
                return true;
            }
        }
        return false;
    }
}
