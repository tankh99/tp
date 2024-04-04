package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPOINTMENT;
import static seedu.address.testutil.TypicalPersons.getTypicalPatientList;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PatientList;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentList;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;

public class EditAppointmentCommandTest {
    private Model model = new ModelManager(getTypicalPatientList(), getTypicalAppointmentList(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Appointment editedAppointment = new AppointmentBuilder().build();
        EditAppointmentCommand.EditAppointmentDescriptor descriptor =
                new EditAppointmentDescriptorBuilder(editedAppointment).build();
        EditAppointmentCommand editCommand = new EditAppointmentCommand(INDEX_FIRST_APPOINTMENT, descriptor);

        String expectedMessage = String.format(
                EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS, Messages.formatAppointment(editedAppointment));

        Model expectedModel = new ModelManager(new PatientList(model.getPatientList()),
                new AppointmentList(model.getAppointmentList()), new UserPrefs());
        expectedModel.setAppointment(model.getFilteredAppointmentList().get(0), editedAppointment);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastAppointment = INDEX_FIRST_APPOINTMENT;
        Optional<Appointment> lastAppointment = model.getFilteredAppointmentList().stream()
                .filter(appointment -> appointment.getAppointmentId() == INDEX_FIRST_APPOINTMENT.getOneBased())
                .findFirst();

        AppointmentBuilder appointmentInList = new AppointmentBuilder(lastAppointment.get());
        Appointment editedAppointment = appointmentInList.withStudentId(1)
                .withStartDatetime(LocalDateTime.of(2022, 12, 12, 8, 0, 0))
                .withEndDateTime(LocalDateTime.of(2022, 12, 12, 9, 0, 0))
                .withAppointmentDescription("pre-edited appointment")
                .withFeedbackScore(1)
                .withHasAttended(false)
                .build();

        EditAppointmentCommand.EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withAppointmentDescription("pre-edited appointment")
                .withFeedbackScore(1)
                .withAttendedStatus(false).build();
        EditAppointmentCommand editCommand = new EditAppointmentCommand(indexLastAppointment, descriptor);

        String expectedMessage = String.format(
                EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS, Messages.formatAppointment(editedAppointment));

        Model expectedModel = new ModelManager(new PatientList(model.getPatientList()),
                new AppointmentList(model.getAppointmentList()), new UserPrefs());
        expectedModel.setAppointment(lastAppointment.get(), editedAppointment);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditAppointmentCommand editCommand = new EditAppointmentCommand(
                INDEX_FIRST_APPOINTMENT, new EditAppointmentCommand.EditAppointmentDescriptor());
        Appointment editedAppointment = model.getFilteredAppointmentList().get(INDEX_FIRST_APPOINTMENT.getZeroBased());

        String expectedMessage = String.format(
                EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS, Messages.formatAppointment(editedAppointment));

        Model expectedModel = new ModelManager(new PatientList(model.getPatientList()),
                new AppointmentList(model.getAppointmentList()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);
        EditAppointmentCommand.EditAppointmentDescriptor descriptor =
                new EditAppointmentDescriptorBuilder().withPatientId(100).build();
        EditAppointmentCommand editCommand = new EditAppointmentCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_duplicateAppointmentFilteredList_failure() {
        // edit person in filtered list into a duplicate in address book
        Appointment appointmentInList = model.getAppointmentList().getAppointmentList()
                .get(INDEX_SECOND_APPOINTMENT.getZeroBased());
        EditAppointmentCommand editCommand = new EditAppointmentCommand(INDEX_FIRST_APPOINTMENT,
                new EditAppointmentDescriptorBuilder(appointmentInList).build());

        assertCommandFailure(editCommand, model, EditAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT);
    }

    @Test
    public void equals() {
        EditAppointmentCommand.EditAppointmentDescriptor descFirst = new EditAppointmentDescriptorBuilder()
                .withPatientId(1)
                .withStartDateTime(LocalDateTime.of(2022, 12, 12, 8, 0, 0))
                .withEndDateTime(LocalDateTime.of(2022, 12, 12, 9, 0, 0))
                .withAppointmentDescription("First appointment")
                .withFeedbackScore(1)
                .withAttendedStatus(false)
                .build();

        final EditAppointmentCommand.EditAppointmentDescriptor descSecond = new EditAppointmentDescriptorBuilder()
                .withPatientId(1)
                .withStartDateTime(LocalDateTime.of(2022, 12, 12, 9, 0, 0))
                .withEndDateTime(LocalDateTime.of(2022, 12, 12, 10, 0, 0))
                .withAppointmentDescription("First appointment")
                .withFeedbackScore(1)
                .withAttendedStatus(false)
                .build();

        final EditAppointmentCommand standardCommand = new EditAppointmentCommand(INDEX_FIRST_APPOINTMENT, descFirst);

        // same values -> returns true
        EditAppointmentCommand.EditAppointmentDescriptor copyDescriptor =
                new EditAppointmentCommand.EditAppointmentDescriptor(descFirst);
        EditAppointmentCommand commandWithSameValues =
                new EditAppointmentCommand(INDEX_FIRST_APPOINTMENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditAppointmentCommand(INDEX_SECOND_APPOINTMENT, descFirst)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditAppointmentCommand(INDEX_FIRST_APPOINTMENT, descSecond)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditAppointmentCommand.EditAppointmentDescriptor editAppointmentDescriptor =
                new EditAppointmentCommand.EditAppointmentDescriptor();
        EditAppointmentCommand editCommand = new EditAppointmentCommand(index, editAppointmentDescriptor);
        String expected = EditAppointmentCommand.class.getCanonicalName() + "{index=" + index
                + ", editAppointmentDescriptor=" + editAppointmentDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }
}
