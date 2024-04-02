package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTEND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEEDBACK_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATETIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDescription;
import seedu.address.model.appointment.EndDateTime;
import seedu.address.model.appointment.FeedbackScore;
import seedu.address.model.appointment.HasAttended;
import seedu.address.model.appointment.PatientId;
import seedu.address.model.appointment.StartDateTime;

/**
 * Edits the details of an existing appointment in the address book.
 */
public class EditAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "edita";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the appointment identified "
            + "by the index number used in the displayed appointment list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_PATIENT_ID + "PATIENT_ID] "
            + "[" + PREFIX_START_DATETIME + "START_DATE_TIME] "
            + "[" + PREFIX_END_DATETIME + "END_DATE_TIME] "
            + "[" + PREFIX_ATTEND + "ATTEND] "
            + "[" + PREFIX_FEEDBACK_SCORE + "FEEDBACK_SCORE] "
            + "[" + PREFIX_APPOINTMENT_DESCRIPTION + "APPOINTMENT_DESCRIPTION]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ATTEND + "true "
            + PREFIX_FEEDBACK_SCORE + "5";

    public static final String MESSAGE_EDIT_APPOINTMENT_SUCCESS = "Edited Appointment: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists in the address book.";

    private final Index index;
    private final EditAppointmentCommand.EditAppointmentDescriptor editAppointmentDescriptor;

    /**
     * @param index                of the appointment in the filtered appointment list to edit
     * @param editAppointmentDescriptor details to edit the appointment with
     */
    public EditAppointmentCommand(Index index,
                                  EditAppointmentCommand.EditAppointmentDescriptor editAppointmentDescriptor) {
        requireNonNull(index);
        requireNonNull(editAppointmentDescriptor);

        this.index = index;
        this.editAppointmentDescriptor =
                new EditAppointmentCommand.EditAppointmentDescriptor(editAppointmentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        // We try to find the appointment based on the given appointmentId.
        Optional<Appointment> appointmentToEdit = lastShownList.stream()
                .filter(appointment -> appointment.getAppointmentId() == index.getOneBased())
                .findFirst();


        if (appointmentToEdit.isPresent()) {
            Appointment editedAppointment = createEditedAppointment(appointmentToEdit.get(), editAppointmentDescriptor);

            // Checks if the new appointment is the same as the unedited appointment.
            if (!appointmentToEdit.get().isSameAppointment(editedAppointment)
                    && model.hasAppointment(editedAppointment)) {
                throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
            }

            model.setAppointment(appointmentToEdit.get(), editedAppointment);
            model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
            return new CommandResult(String.format(MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                    Messages.formatAppointment(editedAppointment)));

        } else {
            // This means that the appointment was not found.
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

    }

    /**
     * Creates and returns a {@code Appointment} with the details of {@code appointmentToEdit}
     * edited with {@code editAppointmentDescriptor}.
     */
    private static Appointment createEditedAppointment(
            Appointment appointmentToEdit, EditAppointmentCommand.EditAppointmentDescriptor editAppointmentDescriptor) {
        assert appointmentToEdit != null;

        int appointmentId = appointmentToEdit.getAppointmentId();
        PatientId patientId = editAppointmentDescriptor.getPatientId().orElse(appointmentToEdit.getPatientId());
        StartDateTime startDateTime = editAppointmentDescriptor.getStartDateTime()
                .orElse(appointmentToEdit.getStartDateTime());
        EndDateTime endDateTime = editAppointmentDescriptor.getEndDateTime().orElse(appointmentToEdit.getEndDateTime());
        HasAttended hasAttended = editAppointmentDescriptor.getHasAttended()
                .orElse(appointmentToEdit.getAttendedStatus());
        FeedbackScore feedbackScore = editAppointmentDescriptor.getFeedbackScore()
                .orElse(appointmentToEdit.getFeedbackScore());
        AppointmentDescription appointmentDescription = editAppointmentDescriptor.getAppointmentDescription()
                .orElse(appointmentToEdit.getAppointmentDescription());

        return new Appointment(appointmentId, startDateTime, endDateTime, patientId, appointmentDescription,
                hasAttended, feedbackScore);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditAppointmentCommand)) {
            return false;
        }

        EditAppointmentCommand otherEditCommand = (EditAppointmentCommand) other;
        return index.equals(otherEditCommand.index)
                && editAppointmentDescriptor.equals(otherEditCommand.editAppointmentDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editAppointmentDescriptor", editAppointmentDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the appointment with. Each non-empty field value will replace the
     * corresponding field value of the appointment.
     */
    public static class EditAppointmentDescriptor {
        private int appointmentId;
        private PatientId patientId;
        private StartDateTime startDateTime;
        private EndDateTime endDateTime;
        private HasAttended hasAttended;
        private FeedbackScore feedbackScore;
        private AppointmentDescription appointmentDescription;

        public EditAppointmentDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditAppointmentDescriptor(EditAppointmentCommand.EditAppointmentDescriptor toCopy) {
            setAppointmentId(toCopy.appointmentId);
            setPatientId(toCopy.patientId);
            setStartDateTime(toCopy.startDateTime);
            setEndDateTime(toCopy.endDateTime);
            setHasAttended(toCopy.hasAttended);
            setFeedbackScore(toCopy.feedbackScore);
            setAppointmentDescription(toCopy.appointmentDescription);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(patientId, startDateTime, endDateTime, hasAttended,
                    feedbackScore, appointmentDescription);
        }

        public void setPatientId(PatientId patientId) {
            this.patientId = patientId;
        }

        public Optional<PatientId> getPatientId() {
            return Optional.ofNullable(patientId);
        }

        public void setStartDateTime(StartDateTime startDateTime) {
            this.startDateTime = startDateTime;
        }

        public Optional<StartDateTime> getStartDateTime() {
            return Optional.ofNullable(startDateTime);
        }

        public void setEndDateTime(EndDateTime endDateTime) {
            this.endDateTime = endDateTime;
        }

        public Optional<EndDateTime> getEndDateTime() {
            return Optional.ofNullable(endDateTime);
        }

        public void setHasAttended(HasAttended hasAttended) {
            this.hasAttended = hasAttended;
        }

        public Optional<HasAttended> getHasAttended() {
            return Optional.ofNullable(hasAttended);
        }

        public void setFeedbackScore(FeedbackScore feedbackScore) {
            this.feedbackScore = feedbackScore;
        }

        public Optional<FeedbackScore> getFeedbackScore() {
            return Optional.ofNullable(feedbackScore);
        }

        public void setAppointmentDescription(AppointmentDescription appointmentDescription) {
            this.appointmentDescription = appointmentDescription;
        }

        public Optional<AppointmentDescription> getAppointmentDescription() {
            return Optional.ofNullable(appointmentDescription);
        }

        public void setAppointmentId(int appointmentId) {
            this.appointmentId = appointmentId;
        }

        public Optional<Integer> getAppointmentId() {
            return Optional.of(Integer.valueOf(appointmentId));
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditAppointmentCommand.EditAppointmentDescriptor)) {
                return false;
            }

            EditAppointmentCommand.EditAppointmentDescriptor otherEditAppointmentDescriptor =
                    (EditAppointmentCommand.EditAppointmentDescriptor) other;
            return appointmentId == otherEditAppointmentDescriptor.appointmentId
                    && Objects.equals(patientId, otherEditAppointmentDescriptor.patientId)
                    && Objects.equals(startDateTime, otherEditAppointmentDescriptor.startDateTime)
                    && Objects.equals(endDateTime, otherEditAppointmentDescriptor.endDateTime)
                    && Objects.equals(hasAttended, otherEditAppointmentDescriptor.hasAttended)
                    && Objects.equals(feedbackScore, otherEditAppointmentDescriptor.feedbackScore)
                    && Objects.equals(appointmentDescription, otherEditAppointmentDescriptor.appointmentDescription);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("patientId", patientId)
                    .add("startDateTime", startDateTime)
                    .add("endDateTime", endDateTime)
                    .add("hasAttended", hasAttended)
                    .add("feedbackScore", feedbackScore)
                    .add("appointmentDescription", appointmentDescription)
                    .toString();
        }
    }
}
