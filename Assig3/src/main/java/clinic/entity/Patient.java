package clinic.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String cardNb;
    private String pnc;
    private String address;
    private Date birthDate;

    public Patient() {
    }

    public Patient(String name, String cardNb, String pnc, String address, Date birthDate) {
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
