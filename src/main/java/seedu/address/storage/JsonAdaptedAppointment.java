package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.*;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
public class JsonAdaptedAppointment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";
    public final int appointmentId;
    public final LocalDateTime startDateTime;
    public final LocalDateTime endDateTime;

    public final int studentId;
    public final String appointmentDescription;
    private final boolean hasAttended;
    private final int feedbackScore;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given appointment details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("appointmentId") int appointmentId,
                                  @JsonProperty("startDateTime") LocalDateTime startDateTime,
                                  @JsonProperty("endDateTime") LocalDateTime endDateTime,
                                  @JsonProperty("studentId") int studentId,
                                  @JsonProperty("appointmentDescription") String appointmentDescription,
                                  @JsonProperty("hasAttended") boolean hasAttended,
                                  @JsonProperty("feedbackScore") int feedbackScore) {
        this.appointmentId = appointmentId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.studentId = studentId;
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
        studentId = source.getStudentId();
        appointmentDescription = source.getAppointmentDescription();
        hasAttended = source.getAttendedStatus().hasAttended;
        feedbackScore = source.getFeedbackScore().getFeedbackScore();
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
        if (appointmentDescription == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "appointmentDescription"));
        }

        if (appointmentId <= 0 || studentId <= 0) {
            // TODO: Custom type for SID
            throw new IllegalValueException("Please only use positive index.");
        }

        StartDateTime modelStartDateTime = new StartDateTime(this.startDateTime);
        EndDateTime modelEndDateTime = new EndDateTime(this.endDateTime);
        FeedbackScore modelFeedbackScore = new FeedbackScore(this.feedbackScore);
        HasAttended modelHasAttended = new HasAttended(hasAttended);
        // TODO: Dummy value for ID
        return new Appointment(appointmentId, modelStartDateTime,
                modelEndDateTime, studentId, appointmentDescription, modelHasAttended, modelFeedbackScore);
    }
}
