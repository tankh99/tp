package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.EndDateTime;
import seedu.address.model.appointment.StartDateTime;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
public class JsonAdaptedAppointment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";
    public final int appointmentId;
    public final StartDateTime startDateTime;
    public final EndDateTime endDateTime;

    public final int studentId;
    public final String appointmentDescription;
    private final boolean hasAttended;
    private final Integer feedbackScore;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given appointment details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("appointmentId") int appointmentId,
                                  @JsonProperty("startDateTime") StartDateTime startDateTime,
                                  @JsonProperty("endDateTime") EndDateTime endDateTime,
                                  @JsonProperty("studentId") int studentId,
                                  @JsonProperty("appointmentDescription") String appointmentDescription,
                                  @JsonProperty("hasAttended") boolean hasAttended,
                                  @JsonProperty("feedbackScore") Integer feedbackScore) {
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
        startDateTime = source.getStartDateTime();
        endDateTime = source.getEndDateTime();
        studentId = source.getStudentId();
        appointmentDescription = source.getAppointmentDescription();
        hasAttended = source.getAttendedStatus();
        feedbackScore = source.getFeedbackScore();
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
        // TODO: Dummy value for ID
        return new Appointment(appointmentId, startDateTime, endDateTime, studentId, appointmentDescription, hasAttended,
                               feedbackScore);
    }
}
