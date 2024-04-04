package seedu.address.ui;

import javafx.scene.control.ListCell;
import seedu.address.model.ReadOnlyPatientList;
import seedu.address.model.appointment.Appointment;

/**
 * Custom {@code ListCell} that displays the graphics of a {@code Appointment} using an {@code AppointmentCard}.
 */
public class AppointmentListViewCell extends ListCell<Appointment> {

    private final ReadOnlyPatientList patients;

    AppointmentListViewCell(ReadOnlyPatientList patients) {
        this.patients = patients;
    }

    @Override
    protected void updateItem(Appointment appointment, boolean empty) {
        super.updateItem(appointment, empty);

        if (empty || appointment == null) {
            setGraphic(null);
            setText(null);
        } else {
            String name = patients.getPersonList().stream()
                    .filter(patient -> patient.getSid() == appointment.getPatientId().patientId).findFirst()
                    .get().getName().fullName;
            setGraphic(new AppointmentCard(appointment, name).getRoot());
        }
    }
}
