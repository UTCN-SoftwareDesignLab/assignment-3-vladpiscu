package clinic.service;


import clinic.entity.Consultation;
import clinic.entity.Patient;
import clinic.entity.User;
import clinic.repository.ConsultationRepository;
import clinic.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConsultationServiceImplTest {
    private ConsultationService consultationService;

    @Before
    public void setUpClass() {
        ConsultationRepository consultationRepository = mock(ConsultationRepository.class);
        UserRepository userRepository = mock(UserRepository.class);

        ArrayList<Patient> patients = new ArrayList<>();
        Patient patient1 = new Patient("name1", "cardNb1", "pnc1", "address1", new Date(100));
        Patient patient2 = new Patient("name2", "cardNb2", "pnc2", "address2", new Date(200));
        patients.add(patient1);
        patients.add(patient2);

        List<User> users = new ArrayList<>();
        User user1 = new User("username1", "password1", "doctor");
        User user2 = new User("username2", "password2", "doctor");
        User user3 = new User("username3", "password3", "doctor");
        User user4 = new User("username4", "password4", "doctor");
        User user5 = new User("username5", "password5", "doctor");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);

        List<Consultation> consultations = new ArrayList<>();
        Consultation consultation1 = new Consultation(new Date(100), user1, patient1);
        Consultation consultation2 = new Consultation(new Date(200), user1, patient2);
        Consultation consultation3 = new Consultation(new Date(300), user2, patient1);
        Consultation consultation4 = new Consultation(new Date(100), user3, patient1);
        Consultation consultation5 = new Consultation(new Date(200), user4, patient1);
        consultations.add(consultation1);
        consultations.add(consultation2);
        consultations.add(consultation3);
        consultations.add(consultation4);
        consultations.add(consultation5);

        when(userRepository.findAllByRole("doctor")).thenReturn(users);
        when(consultationRepository.findAll()).thenReturn(consultations);

        consultationService = new ConsultationServiceImpl(consultationRepository, userRepository);

    }

    @Test
    public void findAllAvailableDoctors3() {
        List<User> availableDoctors = consultationService.findAllAvailableDoctors(new Date(200));

        assertEquals(3, availableDoctors.size());

    }

    @Test
    public void findAllAvailableDoctors4() {
        List<User> availableDoctors = consultationService.findAllAvailableDoctors(new Date(300));

        assertEquals(4, availableDoctors.size());

    }
}
