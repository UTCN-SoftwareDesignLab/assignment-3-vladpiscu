package clinic.repository;

import clinic.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer>{
    Patient findById(int id);
    void deleteById(int id);
}
