package clinic.service;

import clinic.entity.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> findAll();
    Patient save(Patient patient);
    Patient update(Patient patient);
    void remove(Patient patient);
    void remove(int id);
    void removeAll();
}
