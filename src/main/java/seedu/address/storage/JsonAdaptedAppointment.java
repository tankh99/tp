package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDescription;
import seedu.address.model.appointment.EndDateTime;
import seedu.address.model.appointment.FeedbackScore;
import seedu.address.model.appointment.HasAttended;
import seedu.address.model.appointment.PatientId;
import seedu.address.model.appointment.StartDateTime;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
public class JsonAdaptedAppointment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";
    private final int appointmentId;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    private final int patientId;
    private final String appointmentDescription;
    private final Boolean hasAttended;
    private final Integer feedbackScore;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given appointment details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("appointmentId") int appointmentId,
                                  @JsonProperty("startDateTime") LocalDateTime startDateTime,
                                  @JsonProperty("endDateTime") LocalDateTime endDateTime,
                                  @JsonProperty("studentId") int patientId,
                                  @JsonProperty("appointmentDescription") String appointmentDescription,
                                  @JsonProperty("hasAttended") Boolean hasAttended,
                                  @JsonProperty("feedbackScore") int feedbackScore) {
        this.appointmentId = appointmentId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.patientId = patientId;
        this.appointmentDescription = appointmentDescription;
        this.hasAttended = hasAttended;
        this.feedbackScore = feedbackScore;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        appointmentId = source.getAppointmentId();
        startDateTime = source.getStartDateTime().getDateTimeValue();
        endDateTime = source.getEndDateTime().getDateTimeValue();
        patientId = source.getPatientId().patientId;

        if (source.getAppointmentDescription() != null) {
            appointmentDescription = source.getAppointmentDescription().appointmentDescription;
        } else {
            appointmentDescription = null;
        }

        if (source.getAttendedStatus() != null) {
            hasAttended = source.getAttendedStatus().hasAttended;
        } else {
            hasAttended = null;
        }

        if (source.getFeedbackScore() != null) {
            feedbackScore = source.getFeedbackScore().feedbackScore;
        } else {
            feedbackScore = null;
        }
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Appointment toModelType() throws IllegalValueException {
        if (startDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "appointmentDateTime"));
        }
        if (endDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "appointmentDateTime"));
        }

        if (appointmentId <= 0 || patientId <= 0) {
            throw new IllegalValueException("Please only use positive index.");
        }

        final PatientId modelPatientId = new PatientId(patientId);
        final StartDateTime modelStartDateTime = new StartDateTime(this.startDateTime);
        final EndDateTime modelEndDateTime = new EndDateTime(this.endDateTime);
        final FeedbackScore modelFeedbackScore = new FeedbackScore(this.feedbackScore);
        final HasAttended modelHasAttended = new HasAttended(hasAttended);
        final AppointmentDescription modelAppointmentDescription = new AppointmentDescription(appointmentDescription);
        // TODO: Dummy value for ID
        return new Appointment(appointmentId, modelStartDateTime,
                modelEndDateTime, modelPatientId, modelAppointmentDescription, modelHasAttended, modelFeedbackScore);
    }
}
