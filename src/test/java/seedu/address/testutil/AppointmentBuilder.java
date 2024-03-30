package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.EndDateTime;
import seedu.address.model.appointment.StartDateTime;


/**
 * A utility class to help with building Person objects.
 */
public class AppointmentBuilder {

    public static final int DEFAULT_APPOINTMENT_ID = 1;
    public static final LocalDateTime DEFAULT_APPOINTMENT_DATE_TIME = LocalDateTime.of(2020, 12, 12, 12, 12);

    public static final StartDateTime DEFAULT_START_DATETIME = new StartDateTime(DEFAULT_APPOINTMENT_DATE_TIME);
    public static final EndDateTime DEFAULT_END_DATETIME = new EndDateTime(DEFAULT_APPOINTMENT_DATE_TIME.plusHours(1));
    public static final int DEFAULT_STUDENT_ID = 1;

    public static final String DEFAULT_APPOINTMENT_DESCRIPTION = "Appointment Description";

    public static final boolean DEFAULT_HAS_ATTENDED = false;
    public static final Integer DEFAULT_FEEDBACK_SCORE = null;

    private int appointmentId;
    private StartDateTime startDateTime;

    private EndDateTime endDateTime;
    private int studentId;

    //TODO: replace with caseLog
    private String appointmentDescription;

    private boolean hasAttended;
    private Integer feedbackScore;

    /**
     * Creates a {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        appointmentId = DEFAULT_APPOINTMENT_ID;
        startDateTime = DEFAULT_START_DATETIME;
        endDateTime = DEFAULT_END_DATETIME;
        studentId = DEFAULT_STUDENT_ID;
        appointmentDescription = DEFAULT_APPOINTMENT_DESCRIPTION;
        hasAttended = DEFAULT_HAS_ATTENDED;
        feedbackScore = DEFAULT_FEEDBACK_SCORE;
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code appointmentToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        appointmentId = appointmentToCopy.getAppointmentId();
        startDateTime = appointmentToCopy.getStartDateTime();
        endDateTime = appointmentToCopy.getEndDateTime();
        studentId = appointmentToCopy.getStudentId();
        appointmentDescription = appointmentToCopy.getAppointmentDescription();
        hasAttended = appointmentToCopy.getAttendedStatus();
        feedbackScore = appointmentToCopy.getFeedbackScore();
    }

    /**
     * Sets the {@code appointmentId} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
        return this;
    }

    /**
     * Set the {@code withStartDatetime} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withStartDatetime(LocalDateTime dateTime) {
        this.startDateTime = new StartDateTime(dateTime);
        return this;
    }
    /**
     * Set the {@code withStartDatetime} of the {@code Appointment} that we are building.
     */

    public AppointmentBuilder withEndDateTime(LocalDateTime dateTime) {
        this.endDateTime = new EndDateTime(dateTime);
        return this;
    }

    /**
     * Sets the {@code studentId} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withStudentId(int studentId) {
        this.studentId = studentId;
        return this;
    }

    /**
     * Sets the {@code AppointmentDescription} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
        return this;
    }

    /**
     * Sets the {@code hasAttended} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withHasAttended(boolean hasAttended) {
        this.hasAttended = hasAttended;
        return this;
    }

    /**
     * Sets the {@code feedbackScore} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withFeedbackScore(Integer feedbackScore) {
        this.feedbackScore = feedbackScore;
        return this;
    }

    /**
     * Builds an (@code Appointment)
     */
    public Appointment build() {
        return new Appointment(appointmentId, startDateTime, endDateTime, studentId, appointmentDescription, hasAttended,
                               feedbackScore);
    }

}
