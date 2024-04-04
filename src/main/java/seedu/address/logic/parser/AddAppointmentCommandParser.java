package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTEND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEEDBACK_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATETIME;

import java.util.List;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDescription;
import seedu.address.model.appointment.EndDateTime;
import seedu.address.model.appointment.FeedbackScore;
import seedu.address.model.appointment.HasAttended;
import seedu.address.model.appointment.PatientId;
import seedu.address.model.appointment.StartDateTime;
import seedu.address.model.patient.Patient;
import seedu.address.model.util.RelationshipUtil;

/**
 * Parses input arguments and creates a new AddAppointmentCommand object
 */
public class AddAppointmentCommandParser implements Parser<AddAppointmentCommand> {
    private final List<Patient> patients;
    private final List<Appointment> appointments;

    /**
     * Takes in multiple lists so as to perform validation checks against them
     *
     * @param patients List of patients
     * @param appointments List of appointments
     */
    public AddAppointmentCommandParser(List<Patient> patients, List<Appointment> appointments) {
        this.patients = patients;
        this.appointments = appointments;
    }

    //@@author caitlyntang
    /**
     * Parses the given {@code String} of arguments in the context of the AddAppointmentCommand
     * and returns an AddAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAppointmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PATIENT_ID, PREFIX_START_DATETIME, PREFIX_END_DATETIME,
                        PREFIX_ATTEND, PREFIX_APPOINTMENT_DESCRIPTION, PREFIX_FEEDBACK_SCORE);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_PATIENT_ID, PREFIX_START_DATETIME, PREFIX_END_DATETIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(
                PREFIX_PATIENT_ID, PREFIX_START_DATETIME, PREFIX_END_DATETIME, PREFIX_ATTEND,
                PREFIX_APPOINTMENT_DESCRIPTION, PREFIX_FEEDBACK_SCORE);

        PatientId patientId = ParserUtil.parsePatientId(argMultimap.getValue(PREFIX_PATIENT_ID).get(), patients);
        StartDateTime startDateTime = ParserUtil.parseStartDateTime(argMultimap.getValue(PREFIX_START_DATETIME).get());
        EndDateTime endDateTime = ParserUtil.parseEndDateTime(argMultimap.getValue(PREFIX_END_DATETIME).get());
        HasAttended hasAttended = ParserUtil.parseHasAttended(argMultimap.getValue(PREFIX_ATTEND).orElse(""));
        AppointmentDescription appointmentDescription = ParserUtil.parseDescription(
                argMultimap.getValue(PREFIX_APPOINTMENT_DESCRIPTION).orElse(""));
        FeedbackScore feedbackScore = ParserUtil.parseFeedbackScore(
                argMultimap.getValue(PREFIX_FEEDBACK_SCORE).orElse(""));

        Appointment appointment = new Appointment(startDateTime, endDateTime, patientId, appointmentDescription,
                hasAttended, feedbackScore);

        if (RelationshipUtil.isAppointmentDateTimeAlreadyTaken(startDateTime, endDateTime, this.appointments)) {
            throw new ParseException(Appointment.MESSAGE_DATETIME_ALREADY_TAKEN);
        }
        return new AddAppointmentCommand(appointment);
    }
}
