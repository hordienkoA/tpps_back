package ua.cn.stu.tpps.buyfly.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import ua.cn.stu.tpps.buyfly.util.LocalDateAttributeConverter;
import ua.cn.stu.tpps.buyfly.values.Sex;
import ua.cn.stu.tpps.buyfly.values.UserRole;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@NamedQueries({
    @NamedQuery(name = "setEnabled", query = "UPDATE User u SET u.enabled = ?1 WHERE u.id = ?2"),
    @NamedQuery(name = "getByRoleType", query = "SELECT u FROM User u WHERE u.userRole = ?1"),
    @NamedQuery(name = "getForFlight",
        query = "SELECT u FROM User u, BoardingPass b, Flight f WHERE b.customer.id = u.id AND b.flight.id = ?1 GROUP BY u.id"),
    @NamedQuery(name = "getByEmail", query = "SELECT u FROM User u WHERE u.email = ?1")
})
public class User extends DomainSuperClass implements Serializable {

    @SuppressWarnings("FieldCanBeLocal")
    @NotNull
    private String password;

    @NotNull
    @Size(max = 50)
    private String userRole;

    @NotNull
    private boolean registered; // if unregistered - role = CUSTOMER

    @NotNull
    private boolean enabled;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 50)
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 50)
    private String lastName;

    @Size(max = 30)
    private String sex;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate dateOfBirth;

    @Column(unique = true)
    @Size(max = 15)
    private String phoneNumber;

    @NotNull
    @Column(unique = true)
    @Email
    private String email;

    @Column(unique = true)
    @Size(max = 50)
    private String documentNumber;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate documentExpirationDate;

    @OneToMany(targetEntity = BoardingPass.class, mappedBy = "customer", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<BoardingPass> flightsHistory;


    public User() {
        super();
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    @SuppressWarnings("SameParameterValue")
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole.toString();
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    @SuppressWarnings("SameParameterValue")
    public void setSex(Sex sex) {
        this.sex = sex.toString();
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public LocalDate getDocumentExpirationDate() {
        return documentExpirationDate;
    }

    public void setDocumentExpirationDate(LocalDate documentExpirationDate) {
        this.documentExpirationDate = documentExpirationDate;
    }

    public List<BoardingPass> getFlightsHistory() {
        return flightsHistory;
    }

    public void setFlightsHistory(List<BoardingPass> flightsHistory) {
        this.flightsHistory = flightsHistory;
    }
}
