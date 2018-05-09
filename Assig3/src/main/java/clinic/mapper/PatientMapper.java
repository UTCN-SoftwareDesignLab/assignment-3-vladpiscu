package clinic.mapper;

import clinic.dto.PatientDto;
import clinic.entity.Patient;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class PatientMapper {
    public Patient toPatient(PatientDto patientDto) {
        return new Patient(patientDto.getName(),
                patientDto.getCardNb(),
                patientDto.getPnc(),
                patientDto.getAddress(),
                Date.valueOf(patientDto.getBirthDate()));
    }

    public PatientDto toPatientDto(Patient patient){
        return new PatientDto(patient.getId(),
                patient.getName(),
                patient.getCardNb(),
                patient.getPnc(),
                patient.getAddress(),
                patient.getBirthDate().toLocalDate());
    }
}
