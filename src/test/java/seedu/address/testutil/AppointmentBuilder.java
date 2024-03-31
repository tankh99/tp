package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.model.appointment.*;


/**
 * A utility class to help with building Person objects.
 */
public class AppointmentBuilder {

    public static final int DEFAULT_APPOINTMENT_ID = 1;
    public static final LocalDateTime DEFAULT_APPOINTMENT_DATE_TIME =
            LocalDateTime.of(2020, 12, 12, 12, 12);

    public static final StartDateTime DEFAULT_START_DATETIME = new StartDateTime(DEFAULT_APPOINTMENT_DATE_TIME);
    public static final EndDateTime DEFAULT_END_DATETIME = new EndDateTime(DEFAULT_APPOINTMENT_DATE_TIME.plusHours(1));
    public static final PatientId DEFAULT_PATIENT_ID = new PatientId(1);

    public static final AppointmentDescription DEFAULT_APPOINTMENT_DESCRIPTION =
            new AppointmentDescription("Appointment Description");

    public static final HasAttended DEFAULT_HAS_ATTENDED = new HasAttended(false);
    public static final FeedbackScore DEFAULT_FEEDBACK_SCORE = null;

    private int appointmentId;
    private StartDateTime startDateTime;

    private EndDateTime endDateTime;
    private PatientId patientId;

    private AppointmentDescription appointmentDescription;

    private HasAttended hasAttended;
    private FeedbackScore feedbackScore;

    /**
     * Creates a {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        appointmentId = DEFAULT_APPOINTMENT_ID;
        startDateTime = DEFAULT_START_DATETIME;
        endDateTime = DEFAULT_END_DATETIME;
        patientId = DEFAULT_PATIENT_ID;
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
        patientId = appointmentToCopy.getPatientId();
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
    public AppointmentBuilder withStudentId(int patientId) {
        this.patientId = new PatientId(patientId);
        return this;
    }

    /**
     * Sets the {@code AppointmentDescription} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = new AppointmentDescription(appointmentDescription);
        return this;
    }

    /**
     * Sets the {@code hasAttended} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withHasAttended(boolean hasAttended) {
        this.hasAttended = new HasAttended(hasAttended);
        return this;
    }

    /**
     * Sets the {@code feedbackScore} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withFeedbackScore(int feedbackScore) {
        this.feedbackScore = new FeedbackScore(feedbackScore);
        return this;
    }

    /**
     * Builds an (@code Appointment)
     */
    public Appointment build() {
        return new Appointment(appointmentId, startDateTime, endDateTime,
                patientId, appointmentDescription, hasAttended, feedbackScore);
    }

}
