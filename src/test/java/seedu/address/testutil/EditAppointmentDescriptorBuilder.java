package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDescription;
import seedu.address.model.appointment.EndDateTime;
import seedu.address.model.appointment.FeedbackScore;
import seedu.address.model.appointment.HasAttended;
import seedu.address.model.appointment.PatientId;
import seedu.address.model.appointment.StartDateTime;

public class EditAppointmentDescriptorBuilder {

    private EditAppointmentCommand.EditAppointmentDescriptor descriptor;

    public EditAppointmentDescriptorBuilder() {
        descriptor = new EditAppointmentCommand.EditAppointmentDescriptor();
    }

    public EditAppointmentDescriptorBuilder(EditAppointmentCommand.EditAppointmentDescriptor descriptor) {
        this.descriptor = new EditAppointmentCommand.EditAppointmentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditAppointmentDescriptorBuilder(Appointment appointment) {
        descriptor = new EditAppointmentCommand.EditAppointmentDescriptor();
        descriptor.setAppointmentId(appointment.appointmentId);
        descriptor.setPatientId(appointment.getPatientId());
        descriptor.setStartDateTime(appointment.getStartDateTime());
        descriptor.setEndDateTime(appointment.getEndDateTime());
        descriptor.setHasAttended(appointment.getAttendedStatus());
        descriptor.setFeedbackScore(appointment.getFeedbackScore());
        descriptor.setAppointmentDescription(appointment.getAppointmentDescription());
    }

    /**
     * Sets the {@code PatientId} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withPatientId(int patientId) {
        descriptor.setPatientId(new PatientId(patientId));
        return this;
    }

    /**
     * Sets the {@code StartDateTime} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withStartDateTime(LocalDateTime startDateTime) {
        descriptor.setStartDateTime(new StartDateTime(startDateTime));
        return this;
    }

    /**
     * Sets the {@code EndDateTime} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withEndDateTime(LocalDateTime endDateTime) {
        descriptor.setEndDateTime(new EndDateTime(endDateTime));
        return this;
    }

    /**
     * Sets the {@code HasAttended} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withAttendedStatus(boolean hasAttended) {
        descriptor.setHasAttended(new HasAttended(hasAttended));
        return this;
    }

    /**
     * Sets the {@code FeedbackScore} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withFeedbackScore(int feedbackScore) {
        descriptor.setFeedbackScore(new FeedbackScore(feedbackScore));
        return this;
    }

    /**
     * Sets the {@code AppointmentDescription} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withAppointmentDescription(String description) {
        descriptor.setAppointmentDescription(new AppointmentDescription(description));
        return this;
    }

    public EditAppointmentCommand.EditAppointmentDescriptor build() {
        return descriptor;
    }
}
