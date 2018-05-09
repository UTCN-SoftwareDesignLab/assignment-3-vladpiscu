package clinic.repository;

import clinic.entity.Consultation;
import clinic.entity.Patient;
import clinic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation, Integer>{
    List<Consultation> findAllByUser(User user);
    List<Patient> findAllByPatient(Patient patient);
    Consultation findById(int id);
    void deleteById(int id);

}
