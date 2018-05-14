package clinic.service;

import clinic.entity.Consultation;
import clinic.entity.Patient;
import clinic.entity.User;
import clinic.repository.ConsultationRepository;
import clinic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultationServiceImpl implements ConsultationService {
    private ConsultationRepository consultationRepository;
    private UserRepository userRepository;

    @Autowired
    public ConsultationServiceImpl(ConsultationRepository consultationRepository, UserRepository userRepository) {
        this.consultationRepository = consultationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Consultation> findAll() {
        return consultationRepository.findAll();
    }

    @Override
    public Consultation findById(int id) {
        return consultationRepository.findById(id);
    }

    @Override
    public List<Consultation> findAllByPatient(Patient patient) {
        return consultationRepository.findAllByPatient(patient);
    }

    @Override
    public List<Consultation> findAllByDoctor(User user) {
        return consultationRepository.findAllByUser(user);
    }

    @Override
    public List<User> findAllAvailableDoctors(Date date) {
        List<Consultation> allConsultations = consultationRepository.findAll();
        List<User> allDoctors = userRepository.findAllByRole("doctor");
        List<User> nonAvailableDoctors = allConsultations.stream()
                .filter(consultation -> consultation.getConsultationDate().compareTo(date) == 0)
                .map(consultation -> consultation.getUser())
                .distinct()
                .collect(Collectors.toList());
        allDoctors.removeAll(nonAvailableDoctors);
        return allDoctors;
    }

    @Override
    public Consultation save(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    @Override
    public Consultation update(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    @Override
    public void remove(int id) {
        consultationRepository.deleteById(id);
    }

    @Override
    public void remove(Consultation consultation) {
        consultationRepository.delete(consultation);
    }

    @Override
    public void removeAll() {
        consultationRepository.deleteAll();
    }
}
