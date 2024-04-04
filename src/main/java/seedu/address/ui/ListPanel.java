package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ReadOnlyPatientList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.patient.Patient;
import seedu.address.model.patientfeedbackreport.PatientFeedbackReport;

/**
 * Panel containing the list of persons, appointments and case logs.
 */
public class ListPanel extends UiPart<Region> {
    private static final String FXML = "ListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ListPanel.class);

    @FXML
    private ListView<Patient> personListView;
    @FXML
    private ListView<Appointment> appointmentListView;
    @FXML
    private ListView<PatientFeedbackReport> patientFeedbackReportListView;

    /**
     * Creates a {@code ListPanel} with the given {@code ObservableLists}.
     */
    public ListPanel(
        ObservableList<Patient> patientList,
        ReadOnlyPatientList readOnlyPatientList,
        ObservableList<Appointment> appointmentList,
        ObservableList<PatientFeedbackReport> patientFeedbackReportList
    ) {
        super(FXML);
        personListView.setItems(patientList);
        appointmentListView.setItems(appointmentList);
        patientFeedbackReportListView.setItems(patientFeedbackReportList);

        personListView.setCellFactory(listView -> new PersonListViewCell());
        appointmentListView.setCellFactory(listView -> new AppointmentListViewCell(readOnlyPatientList));
        patientFeedbackReportListView.setCellFactory(listView ->
            new PatientFeedbackReportListViewCell());
    }
}
