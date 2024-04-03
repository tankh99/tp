package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTEND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEEDBACK_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATETIME;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.EndDateTime;
import seedu.address.model.appointment.StartDateTime;
import seedu.address.model.patient.Patient;
import seedu.address.model.util.RelationshipUtil;

/**
 * Parses input arguments and creates a new EditAppointmentCommand object
 */
public class EditAppointmentCommandParser {
    private final List<Patient> patients;
    private final List<Appointment> appointments;

    /**
     * Takes in multiple lists so as to perform validation checks against them
     *
     * @param patients List of patients
     */
    public EditAppointmentCommandParser(List<Patient> patients, List<Appointment> appointments) {
        this.patients = patients;
        this.appointments = appointments;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PATIENT_ID, PREFIX_START_DATETIME, PREFIX_END_DATETIME,
                        PREFIX_ATTEND, PREFIX_FEEDBACK_SCORE, PREFIX_APPOINTMENT_DESCRIPTION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAppointmentCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PATIENT_ID, PREFIX_START_DATETIME, PREFIX_END_DATETIME,
                PREFIX_ATTEND, PREFIX_FEEDBACK_SCORE, PREFIX_APPOINTMENT_DESCRIPTION);

        EditAppointmentCommand.EditAppointmentDescriptor editAppointmentDescriptor =
                new EditAppointmentCommand.EditAppointmentDescriptor();

        if (argMultimap.getValue(PREFIX_PATIENT_ID).isPresent()) {
            editAppointmentDescriptor.setPatientId(
                    ParserUtil.parsePatientId(argMultimap.getValue(PREFIX_PATIENT_ID).get(), patients));
        }
        if (argMultimap.getValue(PREFIX_START_DATETIME).isPresent()) {
            StartDateTime startDateTime =
                    ParserUtil.parseStartDateTime(argMultimap.getValue(PREFIX_START_DATETIME).get());

            if (RelationshipUtil.isAppointmentDateTimeAlreadyTaken(startDateTime, appointments)) {
                throw new ParseException(Appointment.MESSAGE_DATETIME_ALREADY_TAKEN);
            }

            editAppointmentDescriptor.setStartDateTime(startDateTime);
        }
        if (argMultimap.getValue(PREFIX_END_DATETIME).isPresent()) {
            EndDateTime endDateTime =
                    ParserUtil.parseEndDateTime(argMultimap.getValue(PREFIX_END_DATETIME).get());

            if (RelationshipUtil.isAppointmentDateTimeAlreadyTaken(endDateTime, appointments)) {
                throw new ParseException(Appointment.MESSAGE_DATETIME_ALREADY_TAKEN);
            }

            editAppointmentDescriptor.setEndDateTime(endDateTime);
        }
        if (argMultimap.getValue(PREFIX_ATTEND).isPresent()) {
            editAppointmentDescriptor.setHasAttended(
                    ParserUtil.parseHasAttended(argMultimap.getValue(PREFIX_ATTEND).get()));
        }
        if (argMultimap.getValue(PREFIX_FEEDBACK_SCORE).isPresent()) {
            editAppointmentDescriptor.setFeedbackScore(
                    ParserUtil.parseFeedbackScore(argMultimap.getValue(PREFIX_FEEDBACK_SCORE).get()));
        }
        if (argMultimap.getValue(PREFIX_APPOINTMENT_DESCRIPTION).isPresent()) {
            editAppointmentDescriptor.setAppointmentDescription(
                    ParserUtil.parseDescription(argMultimap.getValue(PREFIX_APPOINTMENT_DESCRIPTION).get()));
        }
        if (!editAppointmentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditAppointmentCommand.MESSAGE_NOT_EDITED);
        }

        return new EditAppointmentCommand(index, editAppointmentDescriptor);
    }

}
