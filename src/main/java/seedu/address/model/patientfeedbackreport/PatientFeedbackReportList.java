package seedu.address.model.patientfeedbackreport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.ReadOnlyList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.patient.Patient;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of patient feedback reports that is read only.
 */
public class PatientFeedbackReportList implements ReadOnlyList<PatientFeedbackReport> {

    // TODO: We leave it as mutable for now
    private ObservableList<PatientFeedbackReport> patientFeedbackReportList;

    /**
     * Creates the list upon initialisation
     * @param patients
     * @param appointments
     */
    public PatientFeedbackReportList(List<Patient> patients, List<Appointment> appointments) {
        patientFeedbackReportList = FXCollections.observableArrayList();
        for (Patient p: patients) {
            double totalFeedbackScore = 0;
            int count = 0;
            for (Appointment a: appointments) {
                if (a.getStudentId() == p.getSid()) {
                    // We only count feedback scores that are given
                    if (a.getFeedbackScore() != null) {
                        totalFeedbackScore += a.getFeedbackScore();
                        count++;
                    }
                }
            }
            double avgFeedbackScore = totalFeedbackScore / count;
            PatientFeedbackReport report = new PatientFeedbackReport(p.getName(), avgFeedbackScore);

            this.patientFeedbackReportList.add(report);
        }
    }

    @Override
    public ObservableList<PatientFeedbackReport> getList() {
        return this.patientFeedbackReportList;
    }
}
