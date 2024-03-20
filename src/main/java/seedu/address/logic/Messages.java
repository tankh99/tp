package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX =
            "The appointment index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_APPOINTMENTS_LISTED_OVERVIEW = "%1$d appointments listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_INVALID_PATIENT_ID = "No such patient with id %1$d exists";

    public static final String MESSAGE_INVALID_DATE_TIME = "Invalid date time format. "
            + "Please use the format yyyy-MM-dd HH:mm";

    public static final String MESSAGE_INVALID_BOOLEAN_VALUE = "Invalid boolean value. Please use true or false.";
    public static final String MESSAGE_DATETIME_IN_THE_PAST =
            "Date and time cannot be in the past";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Tags: ");
        person.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code appointment} for display to the user.
     */
    public static String formatAppointment(Appointment appointment) {
        final StringBuilder builder = new StringBuilder();
        builder.append(appointment.getAppointmentId())
                .append("; StudentId: ")
                .append(appointment.getStudentId())
                .append("; DateTime: ")
                .append(appointment.getAppointmentDateTime())
                .append("; Attend: ")
                .append((appointment.getAttendedStatus()))
                //TODO: remove after case log is implemented
                .append("; Description: ")
                .append((appointment.getAppointmentDescription()));
        return builder.toString();
    }
}