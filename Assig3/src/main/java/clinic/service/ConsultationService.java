package clinic.service;

import clinic.entity.Consultation;
import clinic.entity.Patient;
import clinic.entity.User;

import java.sql.Date;
import java.util.List;

public interface ConsultationService {
    List<Consultation> findAll();
    Consultation findById(int id);
    List<Consultation> findAllByPatient(Patient patient);
    List<Consultation> findAllByDoctor(User user);
    List<User> findAllAvailableDoctors(Date date);
    Consultation save(Consultation consultation);
    Consultation update(Consultation consultation);
    void remove(int id);
    void remove(Consultation consultation);
    void removeAll();
}
