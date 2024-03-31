package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

/**
 * Filter the appointment in the CogniCare appointment list which happen on the specified datetimes.
 */
public class FilterAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "filterappointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filter the appointment which happens " +
            "between one datetime and another datetime. Parameters: "
            + CliSyntax.PREFIX_START_DATETIME + "START_DATETIME "
            + CliSyntax.PREFIX_END_DATETIME + "END_DATETIME\n"
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_START_DATETIME + "2024-03-18 09:00"
            + CliSyntax.PREFIX_END_DATETIME + "2024-03-18 18:00";

    private final Predicate<Appointment> predicate;

    public FilterAppointmentCommand(Predicate<Appointment> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW,
                model.getFilteredAppointmentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterAppointmentCommand)) {
            return false;
        }

        FilterAppointmentCommand otherAppointmentFindCommand = (FilterAppointmentCommand) other;
        return predicate.equals(otherAppointmentFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
