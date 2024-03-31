package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.ATTENDED_FIRST_APPOINTMENT;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;


public class JsonAdaptedAppointmentTest {
    private static final int INVALID_APPOINTMENT_ID = -1;
    private static final int INVALID_STUDENT_ID = -1;

    private static final int VALID_APPOINTMENT_ID = ATTENDED_FIRST_APPOINTMENT.getAppointmentId();
    private static final LocalDateTime VALID_START_DATETIME =
            ATTENDED_FIRST_APPOINTMENT.getStartDateTime().getDateTimeValue();
    private static final LocalDateTime VALID_END_DATETIME =
            ATTENDED_FIRST_APPOINTMENT.getEndDateTime().getDateTimeValue();
    private static final int VALID_STUDENT_ID = ATTENDED_FIRST_APPOINTMENT.getPatientId().patientId;
    private static final String VALID_APPOINTMENT_DESCRIPTION =
            ATTENDED_FIRST_APPOINTMENT.getAppointmentDescription().appointmentDescription;

    private static final boolean VALID_ATTENDED_STATUS = ATTENDED_FIRST_APPOINTMENT.getAttendedStatus().hasAttended;
    private static final int VALID_FEEDBACK_SCORE = ATTENDED_FIRST_APPOINTMENT.getFeedbackScore().getFeedbackScore();

    @Test
    public void toModelType_validAppointmentDetails_returnsAppointment() throws Exception {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(ATTENDED_FIRST_APPOINTMENT);
        assertEquals(ATTENDED_FIRST_APPOINTMENT, appointment.toModelType());
    }

    @Test
    public void toModelType_invalidAppointmentId_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(INVALID_APPOINTMENT_ID,
                VALID_START_DATETIME, VALID_END_DATETIME, VALID_STUDENT_ID,
                VALID_APPOINTMENT_DESCRIPTION, VALID_ATTENDED_STATUS, VALID_FEEDBACK_SCORE);
        String expectedMessage = "Please only use positive index.";
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidStudentId_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_APPOINTMENT_ID,
                VALID_START_DATETIME, VALID_END_DATETIME, INVALID_STUDENT_ID, VALID_APPOINTMENT_DESCRIPTION,
                                                                        VALID_ATTENDED_STATUS, VALID_FEEDBACK_SCORE);
        String expectedMessage = "Please only use positive index.";
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    //TODO: Add null check
}
