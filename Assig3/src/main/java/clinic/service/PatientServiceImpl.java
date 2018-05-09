package clinic.service;

import clinic.entity.Patient;
import clinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PatientServiceImpl implements PatientService {
    private PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient update(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void remove(Patient patient) {
        patientRepository.delete(patient);
    }

    @Override
    public void remove(int id) {
        patientRepository.deleteById(id);
    }

    @Override
    public void removeAll() {
        patientRepository.deleteAll();
    }
}
