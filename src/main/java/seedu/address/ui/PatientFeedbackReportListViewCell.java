package seedu.address.ui;

import javafx.scene.control.ListCell;
import seedu.address.model.patientfeedbackreport.PatientFeedbackReport;

/**
 * Custom {@code ListCell} that displays the graphics of a {@code PatientFeedbackReport}
 * using an {@code PatientFeedbackReportCard}.
 */
public class PatientFeedbackReportListViewCell extends ListCell<PatientFeedbackReport> {
    @Override
    protected void updateItem(PatientFeedbackReport pfr, boolean empty) {
        super.updateItem(pfr, empty);

        if (empty || pfr == null) {
            setGraphic(null);
            setText(null);
        } else {
            setGraphic(new PatientFeedbackReportCard(pfr).getRoot());
        }
    }
}