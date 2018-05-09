package clinic.mapper;

import clinic.dto.ConsultationDto;
import clinic.entity.Consultation;

import java.sql.Date;

public class ConsultationMapper {
    public Consultation toConsultation(ConsultationDto consultationDto){
        return new Consultation(Date.valueOf(consultationDto.getConsultationDate()),
                consultationDto.getUser(),
                consultationDto.getPatient());
    }

    public ConsultationDto consultationDto(Consultation consultation){
        return new ConsultationDto(consultation.getId(),
                consultation.getDiagnostic(),
                consultation.getConsultationDate().toLocalDate(),
                consultation.getUser(),
                consultation.getPatient());
    }
}
