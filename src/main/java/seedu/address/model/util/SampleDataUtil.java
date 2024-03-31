package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.PatientList;
import seedu.address.model.ReadOnlyPatientList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentList;
import seedu.address.model.appointment.ReadOnlyAppointmentList;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code PatientList} with sample data.
 */
public class SampleDataUtil {
    public static Patient[] getSamplePersons() {
        return new Patient[]{
                new Patient(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@gmail.com"),
                        getTagSet("depression")),
                new Patient(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@gmail.com"),
                        getTagSet("jobless", "anxiety")),
                new Patient(new Name("Charlotte Tan"), new Phone("93210283"), new Email("charlotte@outlook.com"),
                        getTagSet("depression")),
                new Patient(new Name("David Li"), new Phone("91031282"), new Email("lidavid@gmail.com"),
                        getTagSet("ocd", "adhd")),
                new Patient(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                        getTagSet("ocd", "depression")),
                new Patient(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                        getTagSet("adhd")),
                new Patient(new Name("Elena Wong"), new Phone("81234567"), new Email("elenawong@gmail.com"),
                        getTagSet("stress")),
                new Patient(new Name("Fahad Mubarak"), new Phone("82345678"), new Email("fahadmubarak@example.com"),
                        getTagSet("insomnia")),
                new Patient(new Name("Grace Lim"), new Phone("83456789"), new Email("gracelim@outlook.com"),
                        getTagSet("anxiety", "stress")),
                new Patient(new Name("Henry Quek"), new Phone("84567890"), new Email("henryquek@gmail.com"),
                        getTagSet("bipolar")),
                new Patient(new Name("Isabella Tan"), new Phone("85678901"), new Email("isabellatan@example.com"),
                        getTagSet("depression", "anxiety")),
                new Patient(new Name("Jasmine Koh"), new Phone("86789012"), new Email("jasminekoh@outlook.com"),
                        getTagSet("adhd")),
                new Patient(new Name("Kumar Singh"), new Phone("87890123"), new Email("kumarsingh@gmail.com"),
                        getTagSet("ocd")),
                new Patient(new Name("Lily Chen"), new Phone("88901234"), new Email("lilychen@example.com"),
                        getTagSet("jobless", "stress")),
                new Patient(new Name("Mohamed Zulfikar"), new Phone("89012345"), new Email("mohamedz@example.com"),
                        getTagSet("depression", "insomnia")),
                new Patient(new Name("Nora Seah"), new Phone("90123456"), new Email("noraseah@outlook.com"),
                        getTagSet("anxiety"))

        };
    }

    public static Appointment[] getSampleAppointments() {
        return new Appointment[]{
                new Appointment(1, LocalDateTime.of(2021, 10, 10, 10, 10),
                        1, "First appointment", true, 1),
                new Appointment(2, LocalDateTime.of(2021, 10, 10, 10, 10),
                        2, "Second appointment", false, 3),
                new Appointment(3, LocalDateTime.of(2021, 10, 10, 10, 10),
                        3, "Third appointment", true, 5),
                new Appointment(4, LocalDateTime.of(2021, 10, 30, 10, 10),
                        1, "Fourth appointment", false, 4),
        };
    }

    public static ReadOnlyAppointmentList getSampleAppointmentList() {
        AppointmentList sampleAl = new AppointmentList();
        for (Appointment sampleAppointment : getSampleAppointments()) {
            sampleAl.addAppointment(sampleAppointment);
        }
        return sampleAl;
    }

    public static ReadOnlyPatientList getSamplePatientList() {
        PatientList sampleAb = new PatientList();
        for (Patient samplePatient : getSamplePersons()) {
            sampleAb.addPerson(samplePatient);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
