package seedu.address.ui;

import javafx.scene.control.ListCell;
import seedu.address.model.patient.Patient;
import seedu.address.model.patientfeedbackreport.PatientFeedbackReport;

import java.util.List;

/**
 * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
 */
public class PersonListViewCell extends ListCell<Patient> {

    private final List<PatientFeedbackReport> reports;

    PersonListViewCell(List<PatientFeedbackReport> reports) {
        this.reports = reports;
    }

    @Override
    protected void updateItem(Patient patient, boolean empty) {
        super.updateItem(patient, empty);

        if (empty || patient == null) {
            setGraphic(null);
            setText(null);
        } else {
            Double score = reports.stream()
                    .filter(report -> report.getPatientName().equals(patient.getName()))
                    .findFirst().get().getAvgFeedbackScore();
            setGraphic(new PersonCard(patient, score).getRoot());
        }
    }
}
