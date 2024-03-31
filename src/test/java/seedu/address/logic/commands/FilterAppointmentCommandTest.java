package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.EndDateTime;
import seedu.address.model.appointment.FilterAppointmentPredicate;
import seedu.address.model.appointment.FindAppointmentPredicate;
import seedu.address.model.appointment.StartDateTime;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW;
import static seedu.address.logic.Messages.MESSAGE_INVALID_START_END_DATETIME;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.UNATTENDED_FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentList;
import static seedu.address.testutil.TypicalEndDateTime.END_DATE_TIME_1;
import static seedu.address.testutil.TypicalEndDateTime.END_DATE_TIME_3;
import static seedu.address.testutil.TypicalPersons.getTypicalPatientList;
import static seedu.address.testutil.TypicalStartDateTime.START_DATE_TIME_1;
import static seedu.address.testutil.TypicalStartDateTime.START_DATE_TIME_2;
import static seedu.address.testutil.TypicalStartDateTime.START_DATE_TIME_5;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterAppointmentCommand}.
 */
public class FilterAppointmentCommandTest {
    private Model model = new ModelManager(getTypicalPatientList(), getTypicalAppointmentList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalPatientList(),
            getTypicalAppointmentList(), new UserPrefs());
    @Test
    public void equals() {
        FilterAppointmentPredicate firstPredicate =
                new FilterAppointmentPredicate(START_DATE_TIME_1, END_DATE_TIME_1);
        FilterAppointmentPredicate secondPredicate =
                new FilterAppointmentPredicate(START_DATE_TIME_2, END_DATE_TIME_3);

        FilterAppointmentCommand filterFirstCommand = new FilterAppointmentCommand(firstPredicate,
                START_DATE_TIME_1, END_DATE_TIME_1);
        FilterAppointmentCommand filterSecondCommand = new FilterAppointmentCommand(secondPredicate,
                START_DATE_TIME_2, END_DATE_TIME_3);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterAppointmentCommand filterFirstCommandCopy = new FilterAppointmentCommand(firstPredicate,
                START_DATE_TIME_1, END_DATE_TIME_1);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void execute_invalidStartEndDateTime_noAppointmentFound() {
        String expectedMessage = String.format(MESSAGE_INVALID_START_END_DATETIME);
        FilterAppointmentPredicate predicate = new FilterAppointmentPredicate(START_DATE_TIME_5, END_DATE_TIME_1);
        FilterAppointmentCommand command = new FilterAppointmentCommand(predicate, START_DATE_TIME_5, END_DATE_TIME_1);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validStartEndDateTime_appointmentFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 1);
        StartDateTime startDateTime = new StartDateTime(LocalDateTime.of(2022, 12, 12, 7, 30));
        EndDateTime endDateTime = new EndDateTime(LocalDateTime.of(2022, 12, 12, 9, 30));
        FilterAppointmentPredicate predicate = new FilterAppointmentPredicate(startDateTime, endDateTime);
        FilterAppointmentCommand command = new FilterAppointmentCommand(predicate, startDateTime, endDateTime);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(UNATTENDED_FIRST_APPOINTMENT), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_validStartEndDateTime_noAppointmentFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 0);
        StartDateTime startDateTime = new StartDateTime(LocalDateTime.of(1999, 12, 12, 8, 30));
        EndDateTime endDateTime = new EndDateTime(LocalDateTime.of(1999, 12, 12, 9, 30));
        FilterAppointmentPredicate predicate = new FilterAppointmentPredicate(startDateTime, endDateTime);
        FilterAppointmentCommand command = new FilterAppointmentCommand(predicate, startDateTime, endDateTime);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }
}
