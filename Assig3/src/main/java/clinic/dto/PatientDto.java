package clinic.dto;


import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class PatientDto {
    private int id;

    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "The name should contain only letters!")
    private String name;

    private String cardNb;

    @Pattern(regexp = "[0-9]+", message = "The PNC should contain only numbers!")
    private String pnc;

    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate birthDate;

    public PatientDto() {
    }

    public PatientDto(int id, @Pattern(regexp = "^[\\p{L} .'-]+$", message = "The name should contain only letters!") String name, String cardNb, @Pattern(regexp = "[0-9]+", message = "The PNC should contain only numbers!") String pnc, String address, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.cardNb = cardNb;
        this.pnc = pnc;
        this.address = address;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNb() {
        return cardNb;
    }

    public void setCardNb(String cardNb) {
        this.cardNb = cardNb;
    }

    public String getPnc() {
        return pnc;
    }

    public void setPnc(String pnc) {
        this.pnc = pnc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
