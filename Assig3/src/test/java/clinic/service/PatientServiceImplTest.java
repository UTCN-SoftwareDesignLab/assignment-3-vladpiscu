package clinic.service;

import clinic.entity.Patient;
import clinic.repository.PatientRepository;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


public class PatientServiceImplTest {

    @BeforeClass
    public static void setUpClass() {
        PatientRepository patientRepository = mock(PatientRepository.class);

        ArrayList<Patient> patients = new ArrayList<>();
        Patient patient1 = new Patient("name1", "cardNb1", "pnc1", "address1", new Date(100));
        patient1.setId(1);
        Patient patient2 = new Patient("name2", "cardNb2", "pnc2", "address2", new Date(200));
        patient2.setId(2);
        patients.add(patient1);
        patients.add(patient2);

    }

}
