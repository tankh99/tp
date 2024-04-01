package seedu.address.model.patientfeedbackreport;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;

/**
 * Represents a PatientFeedbackReport object that contains a patient and a list of appointments
 */
public class PatientFeedbackReport implements Comparable<PatientFeedbackReport> {
    private Patient patient;
    private List<Appointment> appointments;

    /**
     * Creates a PatientFeedbackReport object
     */
    public PatientFeedbackReport(Patient patient, List<Appointment> appointments) {
        requireAllNonNull(patient, appointments);
        this.patient = patient;
        this.appointments = appointments;
    }

    public Name getPatientName() {
        return patient.getName();
    }

    /**
     * Calculates the avg feedback score according to the list of appointments the object has
     * @return An average feedback score or null if there are no appointments
     */
    public Double getAvgFeedbackScore() {
        Double total = Double.valueOf(0);
        int count = 0;
        for (Appointment appointment : appointments) {
            boolean apptBelongsToPatient = appointment.getStudentId() == patient.getSid();
            boolean apptHasFeedbacScore = appointment.getFeedbackScore() != null;
            if (apptBelongsToPatient && apptHasFeedbacScore) {
                total += appointment.getFeedbackScore();
                count++;
            }
        }
        if (count != 0) {
            return total / count;
        } else {
            return null;
        }
    }

    @Override
    public int compareTo(PatientFeedbackReport o) {
        return this.patient.getName().fullName.compareTo(o.patient.getName().fullName);
    }

}