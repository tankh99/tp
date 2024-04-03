package seedu.address.model.patientfeedbackreport;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.ReadOnlyList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.patient.Patient;

/**
 * Represents a list of PatientFeedbackReports.
 */
public class PatientFeedbackReportList implements ReadOnlyList<PatientFeedbackReport> {

    private final ObservableList<PatientFeedbackReport> internalList = FXCollections.observableArrayList();
    private final ObservableList<PatientFeedbackReport> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Constructs a PatientFeedbackReportList with the given list of patients and appointments.
     */
    public PatientFeedbackReportList(List<Patient> patients, List<Appointment> appointments) {
        requireAllNonNull(patients, appointments);
        generateReportList(patients, appointments);
    }

    /**
     * Generates the report list by iterating through the list of patients and appointments and matching the
     * various appointments to their respective patients and then calculating their overall score.
     *
     * @param patients The list of patients to iterate through
     * @param appointments The list of appointments to iterate through
     */
    public void generateReportList(List<Patient> patients, List<Appointment> appointments) {
        requireAllNonNull(patients, appointments);
        this.internalList.clear();
        for (Patient patient : patients) {
            PatientFeedbackReport report = new PatientFeedbackReport(patient, appointments);
            internalList.add(report);
        }
    }

    @Override
    public ObservableList<PatientFeedbackReport> getList() {
        return internalUnmodifiableList;
    }
}
