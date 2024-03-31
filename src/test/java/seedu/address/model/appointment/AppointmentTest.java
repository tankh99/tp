package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class AppointmentTest {


    private static final int TEST_APPOINTMENT_ID = 1;
    private static final int TEST_LATER_APPOINTMENT_ID = 2;

    @Test
    public void equals() {
        StartDateTime startDateTime = new StartDateTime(LocalDateTime.now());
        EndDateTime endDateTime = new EndDateTime(LocalDateTime.now().plusHours(1));
        FeedbackScore feedbackScore = new FeedbackScore(5);

        Appointment appointment = new Appointment(TEST_APPOINTMENT_ID, startDateTime, endDateTime,
                1, "Test", false, feedbackScore);

        // same values -> returns true
        Appointment appointmentCopy = new Appointment(TEST_APPOINTMENT_ID, startDateTime, endDateTime,
                1, "Test", false, feedbackScore);
        assertEquals(appointment, appointmentCopy);

        // same object -> returns true
        assertEquals(appointment, appointment);

        // null -> returns false
        assertNotEquals(appointment, null);

        // different types -> returns false
        assertNotEquals(5, appointment);

        // different appointmentId -> returns false
        Appointment differentAppointment = new Appointment(TEST_LATER_APPOINTMENT_ID, startDateTime, endDateTime,
                1, "Test", false, feedbackScore);
        assertNotEquals(appointment, differentAppointment);

        // different startDateTime -> returns false
        differentAppointment = new Appointment(TEST_APPOINTMENT_ID,
                new StartDateTime(LocalDateTime.now().plusHours(1)), endDateTime,
                1, "Test", false, feedbackScore);
        assertNotEquals(appointment, differentAppointment);

        // different endDateTime -> returns false
        differentAppointment = new Appointment(TEST_APPOINTMENT_ID, startDateTime,
                new EndDateTime(LocalDateTime.now().plusHours(2)),
                1, "Test", false, feedbackScore);
        assertNotEquals(appointment, differentAppointment);

        // different studentId -> returns false
        differentAppointment = new Appointment(TEST_APPOINTMENT_ID, startDateTime, endDateTime,
                2, "Test", false, feedbackScore);
        assertNotEquals(appointment, differentAppointment);

        // different appointmentDescription -> returns false
        differentAppointment = new Appointment(TEST_APPOINTMENT_ID, startDateTime, endDateTime,
                1, "Different", false, feedbackScore);
        assertNotEquals(appointment, differentAppointment);

        // different hasAttended -> returns false
        differentAppointment = new Appointment(TEST_APPOINTMENT_ID, startDateTime, endDateTime,
                1, "Test", true, feedbackScore);
        assertNotEquals(appointment, differentAppointment);
    }

    @Test
    public void compareTo() {

        StartDateTime startDateTime = new StartDateTime(LocalDateTime.now());
        EndDateTime endDateTime = new EndDateTime(LocalDateTime.now().plusHours(1));


        Appointment appointment = new Appointment(TEST_APPOINTMENT_ID, startDateTime, endDateTime, 1,
                "Test", false, new FeedbackScore(5));

        // same values -> returns 0
        Appointment appointmentCopy = new Appointment(TEST_APPOINTMENT_ID, startDateTime, endDateTime, 1,
                "Test", false, new FeedbackScore(5));
        assertEquals(0, appointment.compareTo(appointmentCopy));

        // another Appointment has larger appointmentId -> returns less than 0;
        Appointment differentAppointment = new Appointment(TEST_LATER_APPOINTMENT_ID,
                new StartDateTime(LocalDateTime.now().plusDays(1)), endDateTime, 1,
                "Test", false, new FeedbackScore(5));
        assertTrue(appointment.compareTo(differentAppointment) < 0);

        // another Appointment has smaller appointmentId -> returns more than 0;
        differentAppointment = new Appointment(TEST_APPOINTMENT_ID, startDateTime, endDateTime, 2,
                "Test", false, new FeedbackScore(5));
        appointment = new Appointment(TEST_LATER_APPOINTMENT_ID, startDateTime, endDateTime, 1,
                "Test", false, new FeedbackScore(5));
        assertTrue(appointment.compareTo(differentAppointment) > 0);
    }

    @Test
    void getAttendedStatus() {

        StartDateTime startDateTime = new StartDateTime(LocalDateTime.now());
        EndDateTime endDateTime = new EndDateTime(LocalDateTime.now().plusHours(1));
        Appointment appointment = new Appointment(TEST_APPOINTMENT_ID, startDateTime, endDateTime, 1,
                "Test", false, new FeedbackScore(5));
        assertFalse(appointment.getAttendedStatus());

        appointment = new Appointment(TEST_APPOINTMENT_ID, startDateTime, endDateTime, 1, "Test",
                true, new FeedbackScore(5));
        assertTrue(appointment.getAttendedStatus());
    }

    @Test
    void setAttendedStatus() {
        StartDateTime startDateTime = new StartDateTime(LocalDateTime.now());
        EndDateTime endDateTime = new EndDateTime(LocalDateTime.now().plusHours(1));

        Appointment appointment = new Appointment(TEST_APPOINTMENT_ID, startDateTime, endDateTime, 1,
                "Test", false, new FeedbackScore(5));
        appointment.setAttendedStatus(true);
        assertTrue(appointment.getAttendedStatus());

        appointment = new Appointment(TEST_APPOINTMENT_ID, startDateTime, endDateTime, 1, "Test",
                true, new FeedbackScore(5));
        appointment.setAttendedStatus(true);
        assertTrue(appointment.getAttendedStatus());

        appointment = new Appointment(TEST_APPOINTMENT_ID, startDateTime, endDateTime, 1, "Test",
                true, new FeedbackScore(5));
        appointment.setAttendedStatus(false);
        assertFalse(appointment.getAttendedStatus());

        // default status
        appointment = new Appointment(startDateTime, endDateTime, 1, "Test");
        assertFalse(appointment.getAttendedStatus());

        appointment = new Appointment(startDateTime, endDateTime, 1, "Test", true);
        assertTrue(appointment.getAttendedStatus());
    }

    @Test
    void constructorTest() {
        StartDateTime startDateTime = new StartDateTime(LocalDateTime.now());
        EndDateTime endDateTime = new EndDateTime(LocalDateTime.now().plusHours(1));

        //increasing id
        Appointment appointment = new Appointment(startDateTime, endDateTime, 1, "Test");
        Appointment otherAppointment = new Appointment(startDateTime, endDateTime, 1, "Test");
        assertTrue(appointment.compareTo(otherAppointment) < 0);
        assertEquals(appointment.appointmentId + 1, otherAppointment.appointmentId);
    }
}
