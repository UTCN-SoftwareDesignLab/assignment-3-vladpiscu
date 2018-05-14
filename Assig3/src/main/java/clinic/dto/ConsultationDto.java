package clinic.dto;

import clinic.entity.Patient;
import clinic.entity.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ConsultationDto {
    private int id;
    private String diagnostic;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Can't make a consultation in the past!")
    @NotNull
    private LocalDate consultationDate;

    @NotNull(message = "You must select a doctor!")
    private User user;
    @NotNull(message = "You must select a patient!")
    private Patient patient;

    public ConsultationDto() {
    }

    public ConsultationDto(int id, String diagnostic, @NotNull LocalDate consultationDate, User user, Patient patient) {
        this.id = id;
        this.diagnostic = diagnostic;
        this.consultationDate = consultationDate;
        this.user = user;
        this.patient = patient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public LocalDate getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(LocalDate consultationDate) {
        this.consultationDate = consultationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
