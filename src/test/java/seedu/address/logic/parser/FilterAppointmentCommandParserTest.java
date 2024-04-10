package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentList;
import static seedu.address.testutil.TypicalEndDateTime.END_DATE_TIME_1;
import static seedu.address.testutil.TypicalEndDateTime.END_DATE_TIME_3;
import static seedu.address.testutil.TypicalPersons.getTypicalPatientList;
import static seedu.address.testutil.TypicalStartDateTime.START_DATE_TIME_1;
import static seedu.address.testutil.TypicalStartDateTime.START_DATE_TIME_2;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.EndDateTime;
import seedu.address.model.appointment.FilterAppointmentPredicate;
import seedu.address.model.appointment.StartDateTime;



public class FilterAppointmentCommandParserTest {
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
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        FilterAppointmentCommandParser parser = new FilterAppointmentCommandParser();
        assertParseFailure(parser, "asdjkja", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterAppointmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterAppointmentCommand() throws ParseException {
        StartDateTime startDateTime = new StartDateTime(LocalDateTime.of(2024, 3, 18, 10, 0));
        EndDateTime endDateTime = new EndDateTime(LocalDateTime.of(2024, 3, 18, 18, 0));
        FilterAppointmentPredicate predicate = new FilterAppointmentPredicate(startDateTime, endDateTime);
        FilterAppointmentCommand expectedFilterAppointmentCommand = new FilterAppointmentCommand(predicate,
                startDateTime, endDateTime);
        FilterAppointmentCommandParser parser = new FilterAppointmentCommandParser();

        assertEquals(parser.parse(" sd/2024-03-18 10:00 ed/2024-03-18 18:00"),
                expectedFilterAppointmentCommand);
    }

    @Test
    public void parse_oneSDarg_returnsFilterAppointmentCommand2() throws ParseException {
        StartDateTime startDateTime = new StartDateTime(LocalDateTime.of(2024, 3, 18, 10, 0));
        EndDateTime endDateTime = new EndDateTime(LocalDateTime.MAX);
        FilterAppointmentPredicate predicate = new FilterAppointmentPredicate(startDateTime, endDateTime);
        FilterAppointmentCommand expectedFilterAppointmentCommand = new FilterAppointmentCommand(predicate,
                startDateTime, endDateTime);
        FilterAppointmentCommandParser parser = new FilterAppointmentCommandParser();

        assertEquals(parser.parse(" sd/2024-03-18 10:00"),
                expectedFilterAppointmentCommand);
    }

    @Test
    public void parse_oneEDarg_returnsFilterAppointmentCommand3() throws ParseException {
        StartDateTime startDateTime = new StartDateTime(LocalDateTime.MIN);
        EndDateTime endDateTime = new EndDateTime(LocalDateTime.of(2024, 3, 18, 18, 0));
        FilterAppointmentPredicate predicate = new FilterAppointmentPredicate(startDateTime, endDateTime);
        FilterAppointmentCommand expectedFilterAppointmentCommand = new FilterAppointmentCommand(predicate,
                startDateTime, endDateTime);
        FilterAppointmentCommandParser parser = new FilterAppointmentCommandParser();

        assertEquals(parser.parse(" ed/2024-03-18 18:00"),
                expectedFilterAppointmentCommand);
    }

    @Test
    public void parse_noSDnoED_returnsFilterAppointmentCommand4() throws ParseException {
        StartDateTime startDateTime = new StartDateTime(LocalDateTime.MIN);
        EndDateTime endDateTime = new EndDateTime(LocalDateTime.MAX);
        FilterAppointmentPredicate predicate = new FilterAppointmentPredicate(startDateTime, endDateTime);
        FilterAppointmentCommand expectedFilterAppointmentCommand = new FilterAppointmentCommand(predicate,
                startDateTime, endDateTime);
        FilterAppointmentCommandParser parser = new FilterAppointmentCommandParser();

        assertEquals(parser.parse(""),
                expectedFilterAppointmentCommand);
    }

}
